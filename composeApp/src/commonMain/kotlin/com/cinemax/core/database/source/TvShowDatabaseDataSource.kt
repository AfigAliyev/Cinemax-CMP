/*
 * Copyright 2022 Afig Aliyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cinemax.core.database.source

import androidx.paging.PagingSource
import com.cinemax.core.database.dao.TvShowDao
import com.cinemax.core.database.dao.TvShowRemoteKeyDao
import com.cinemax.core.database.model.MediaType
import com.cinemax.core.database.model.TvShowEntity
import com.cinemax.core.database.model.TvShowRemoteKeyEntity
import com.cinemax.core.database.util.CinemaxDatabaseTransactionProvider
import kotlinx.coroutines.flow.Flow

class TvShowDatabaseDataSource(
    private val tvShowDao: TvShowDao,
    private val tvShowRemoteKeyDao: TvShowRemoteKeyDao,
    private val transactionProvider: CinemaxDatabaseTransactionProvider
) {
    fun getByMediaType(mediaType: MediaType.TvShow, pageSize: Int): Flow<List<TvShowEntity>> =
        tvShowDao.getByMediaType(mediaType, pageSize)

    fun getPagingByMediaType(mediaType: MediaType.TvShow): PagingSource<Int, TvShowEntity> =
        tvShowDao.getPagingByMediaType(mediaType)

    suspend fun insertAll(tvShows: List<TvShowEntity>) = tvShowDao.insertAll(tvShows)

    suspend fun deleteByMediaTypeAndInsertAll(
        mediaType: MediaType.TvShow,
        tvShows: List<TvShowEntity>
    ) = transactionProvider.runWithTransaction {
        tvShowDao.deleteByMediaType(mediaType)
        tvShowDao.insertAll(tvShows)
    }

    suspend fun getRemoteKeyByIdAndMediaType(id: Int, mediaType: MediaType.TvShow) =
        tvShowRemoteKeyDao.getByIdAndMediaType(id, mediaType)

    suspend fun handlePaging(
        mediaType: MediaType.TvShow,
        tvShows: List<TvShowEntity>,
        remoteKeys: List<TvShowRemoteKeyEntity>,
        shouldDeleteTvShowsAndRemoteKeys: Boolean
    ) = transactionProvider.runWithTransaction {
        if (shouldDeleteTvShowsAndRemoteKeys) {
            tvShowDao.deleteByMediaType(mediaType)
            tvShowRemoteKeyDao.deleteByMediaType(mediaType)
        }
        tvShowRemoteKeyDao.insertAll(remoteKeys)
        tvShowDao.insertAll(tvShows)
    }
}
