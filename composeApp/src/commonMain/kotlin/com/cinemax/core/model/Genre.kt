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

package com.cinemax.core.model

enum class Genre(val id: Int, val displayName: String) {
    ACTION(28, "Action"),
    ADVENTURE(12, "Adventure"),
    ACTION_ADVENTURE(10759, "Action & Adventure"),
    ANIMATION(16, "Animation"),
    COMEDY(35, "Comedy"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentary"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    FANTASY(14, "Fantasy"),
    HISTORY(36, "History"),
    HORROR(27, "Horror"),
    KIDS(10762, "Kids"),
    MUSIC(10402, "Music"),
    MYSTERY(9648, "Mystery"),
    NEWS(10763, "News"),
    REALITY(10764, "Reality"),
    ROMANCE(10749, "Romance"),
    SCIENCE_FICTION(878, "Science Fiction"),
    SCIENCE_FICTION_FANTASY(10765, "Sci-Fi & Fantasy"),
    SOAP(10766, "Soap"),
    TALK(10767, "Talk"),
    THRILLER(53, "Thriller"),
    TV_MOVIE(10770, "TV Movie"),
    WAR(10752, "War"),
    WAR_POLITICS(10768, "War & Politics"),
    WESTERN(37, "Western"),
    UNKNOWN(-1, "Unknown");

    companion object {
        private val genresById = entries.associateBy(Genre::id)
        fun fromId(id: Int): Genre = genresById[id] ?: UNKNOWN
    }
}
