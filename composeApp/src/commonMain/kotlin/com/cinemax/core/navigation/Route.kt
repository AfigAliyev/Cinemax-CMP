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

package com.cinemax.core.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    sealed interface Main : Route {
        @Serializable
        data object Home : Main

        @Serializable
        data object Search : Main

        @Serializable
        data object Wishlist : Main

        @Serializable
        data object Settings : Main
    }

    @Serializable
    data class List(val mediaType: ListMediaType) : Route {
        companion object {
            val typeMap = typeMap<ListMediaType>()

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<List>(typeMap)
        }
    }

    @Serializable
    data class Details(val id: Int, val mediaType: DetailsMediaType) : Route {
        companion object {
            val typeMap = typeMap<DetailsMediaType>()

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<Details>(typeMap)
        }
    }
}

@Serializable
data object MainGraph

@Serializable
enum class ListMediaType {
    MOVIE_UPCOMING,
    MOVIE_TOP_RATED,
    MOVIE_POPULAR,
    MOVIE_NOW_PLAYING,
    MOVIE_DISCOVER,
    MOVIE_TRENDING,
    TV_TOP_RATED,
    TV_POPULAR,
    TV_NOW_PLAYING,
    TV_DISCOVER,
    TV_TRENDING
}

@Serializable
enum class DetailsMediaType {
    MOVIE,
    TV_SHOW
}
