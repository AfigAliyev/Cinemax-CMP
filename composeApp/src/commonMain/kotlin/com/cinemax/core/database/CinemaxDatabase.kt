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

package com.cinemax.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.cinemax.core.database.converter.CreditsConverter
import com.cinemax.core.database.converter.ListConverter
import com.cinemax.core.database.converter.LocalDateConverter
import com.cinemax.core.database.dao.MovieDao
import com.cinemax.core.database.dao.MovieDetailsDao
import com.cinemax.core.database.dao.MovieRemoteKeyDao
import com.cinemax.core.database.dao.TvShowDao
import com.cinemax.core.database.dao.TvShowDetailsDao
import com.cinemax.core.database.dao.TvShowRemoteKeyDao
import com.cinemax.core.database.dao.WishlistDao
import com.cinemax.core.database.model.MovieDetailsEntity
import com.cinemax.core.database.model.MovieEntity
import com.cinemax.core.database.model.MovieRemoteKeyEntity
import com.cinemax.core.database.model.TvShowDetailsEntity
import com.cinemax.core.database.model.TvShowEntity
import com.cinemax.core.database.model.TvShowRemoteKeyEntity
import com.cinemax.core.database.model.WishlistEntity

@Database(
    entities = [
        MovieEntity::class,
        MovieRemoteKeyEntity::class,
        TvShowEntity::class,
        TvShowRemoteKeyEntity::class,
        MovieDetailsEntity::class,
        TvShowDetailsEntity::class,
        WishlistEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    ListConverter::class,
    LocalDateConverter::class,
    CreditsConverter::class
)
@ConstructedBy(CinemaxDatabaseConstructor::class)
abstract class CinemaxDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
    abstract fun tvShowRemoteKeyDao(): TvShowRemoteKeyDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun tvShowDetailsDao(): TvShowDetailsDao
    abstract fun wishlistDao(): WishlistDao
}

expect object CinemaxDatabaseConstructor : RoomDatabaseConstructor<CinemaxDatabase> {
    override fun initialize(): CinemaxDatabase
}
