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

package com.cinemax.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinemax.core.database.model.MediaType
import com.cinemax.core.database.model.TvShowDetailsEntity
import com.cinemax.core.database.model.TvShowEntity
import com.cinemax.core.database.model.TvShowRemoteKeyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows WHERE media_type = :mediaType LIMIT :pageSize")
    fun getByMediaType(mediaType: MediaType.TvShow, pageSize: Int): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE media_type = :mediaType")
    fun getPagingByMediaType(mediaType: MediaType.TvShow): PagingSource<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShows: List<TvShowEntity>)

    @Query("DELETE FROM tv_shows WHERE media_type = :mediaType")
    suspend fun deleteByMediaType(mediaType: MediaType.TvShow)
}

@Dao
interface TvShowRemoteKeyDao {
    @Query("SELECT * FROM tv_shows_remote_keys WHERE id = :id AND media_type = :mediaType")
    suspend fun getByIdAndMediaType(id: Int, mediaType: MediaType.TvShow): TvShowRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<TvShowRemoteKeyEntity>)

    @Query("DELETE FROM tv_shows_remote_keys WHERE media_type = :mediaType")
    suspend fun deleteByMediaType(mediaType: MediaType.TvShow)
}

@Dao
interface TvShowDetailsDao {
    @Query("SELECT * FROM tv_show_details WHERE id = :id")
    fun getById(id: Int): Flow<TvShowDetailsEntity?>

    @Query("SELECT * FROM tv_show_details WHERE id IN (:ids)")
    fun getByIds(ids: List<Int>): Flow<List<TvShowDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowDetails: TvShowDetailsEntity)

    @Query("DELETE FROM tv_show_details WHERE id = :id")
    suspend fun deleteById(id: Int)
}
