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

package com.cinemax.core.network.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

enum class NetworkGenreWithId(val genreName: String, val id: Int) {
    ACTION("action", 28),
    ADVENTURE("adventure", 12),
    ACTION_ADVENTURE("action_adventure", 10759),
    ANIMATION("animation", 16),
    COMEDY("comedy", 35),
    CRIME("crime", 80),
    DOCUMENTARY("documentary", 99),
    DRAMA("drama", 18),
    FAMILY("family", 10751),
    FANTASY("fantasy", 14),
    HISTORY("history", 36),
    HORROR("horror", 27),
    KIDS("kids", 10762),
    MUSIC("music", 10402),
    MYSTERY("mystery", 9648),
    NEWS("news", 10763),
    REALITY("reality", 10764),
    ROMANCE("romance", 10749),
    SCIENCE_FICTION("science_fiction", 878),
    SCIENCE_FICTION_FANTASY("science_fiction_fantasy", 10765),
    SOAP("soap", 10766),
    TALK("talk", 10767),
    THRILLER("thriller", 53),
    TV_MOVIE("tv_movie", 10770),
    WAR("war", 10752),
    WAR_POLITICS("war_politics", 10768),
    WESTERN("western", 37);

    companion object {
        private val genres = entries.associateBy(NetworkGenreWithId::id)
        operator fun get(id: Int) = genres[id]
    }
}
