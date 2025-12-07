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
import com.cinemax.core.database.model.MovieEntity
import com.cinemax.core.database.model.MovieRemoteKeyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE media_type = :mediaType LIMIT :pageSize")
    fun getByMediaType(mediaType: MediaType.Movie, pageSize: Int): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE media_type = :mediaType")
    fun getPagingByMediaType(mediaType: MediaType.Movie): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE media_type = :mediaType")
    suspend fun deleteByMediaType(mediaType: MediaType.Movie)
}

@Dao
interface MovieRemoteKeyDao {
    @Query("SELECT * FROM movies_remote_keys WHERE id = :id AND media_type = :mediaType")
    suspend fun getByIdAndMediaType(id: Int, mediaType: MediaType.Movie): MovieRemoteKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKeyEntity>)

    @Query("DELETE FROM movies_remote_keys WHERE media_type = :mediaType")
    suspend fun deleteByMediaType(mediaType: MediaType.Movie)
}

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM movie_details WHERE id = :id")
    fun getById(id: Int): Flow<com.cinemax.core.database.model.MovieDetailsEntity?>

    @Query("SELECT * FROM movie_details WHERE id IN (:ids)")
    fun getByIds(ids: List<Int>): Flow<List<com.cinemax.core.database.model.MovieDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetails: com.cinemax.core.database.model.MovieDetailsEntity)

    @Query("DELETE FROM movie_details WHERE id = :id")
    suspend fun deleteById(id: Int)
}
