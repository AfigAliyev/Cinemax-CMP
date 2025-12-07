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

package com.cinemax.core.database.model

import kotlinx.serialization.Serializable

@Serializable
enum class Genre(val genreName: String) {
    ACTION("action"),
    ADVENTURE("adventure"),
    ACTION_ADVENTURE("action_adventure"),
    ANIMATION("animation"),
    COMEDY("comedy"),
    CRIME("crime"),
    DOCUMENTARY("documentary"),
    DRAMA("drama"),
    FAMILY("family"),
    FANTASY("fantasy"),
    HISTORY("history"),
    HORROR("horror"),
    KIDS("kids"),
    MUSIC("music"),
    MYSTERY("mystery"),
    NEWS("news"),
    REALITY("reality"),
    ROMANCE("romance"),
    SCIENCE_FICTION("science_fiction"),
    SCIENCE_FICTION_FANTASY("science_fiction_fantasy"),
    SOAP("soap"),
    TALK("talk"),
    THRILLER("thriller"),
    TV_MOVIE("tv_movie"),
    WAR("war"),
    WAR_POLITICS("war_politics"),
    WESTERN("western");

    companion object {
        private val genres = entries.associateBy(Genre::genreName)
        operator fun get(genre: String) = genres[genre]
    }
}
