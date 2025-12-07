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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinemax.core.database.model.MediaType
import com.cinemax.core.database.model.WishlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    @Query("SELECT * FROM wishlist WHERE media_type = :mediaType")
    fun getByMediaType(mediaType: MediaType.Wishlist): Flow<List<WishlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wishlist: WishlistEntity)

    @Query("DELETE FROM wishlist WHERE media_type = :mediaType AND network_id = :id")
    suspend fun deleteByMediaTypeAndNetworkId(mediaType: MediaType.Wishlist, id: Int)

    @Query("SELECT EXISTS(SELECT * FROM wishlist WHERE media_type = :mediaType AND network_id = :id)")
    suspend fun isWishlisted(mediaType: MediaType.Wishlist, id: Int): Boolean
}
