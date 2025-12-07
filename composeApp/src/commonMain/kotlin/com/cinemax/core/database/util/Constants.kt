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

package com.cinemax.core.database.util

internal object Constants {
    const val DATABASE_NAME = "cinemax.db"

    object Urls {
        private const val CINEMAX_PAGE_URL = "https://afigaliyev.github.io/Cinemax"
        const val CINEMAX_REPO_URL = "https://github.com/AfigAliyev/Cinemax-CMP"
        const val PRIVACY_POLICY_URL = "$CINEMAX_PAGE_URL/privacy-policy.html"
    }

    object Tables {
        const val MOVIES = "movies"
        const val TV_SHOWS = "tv_shows"
        const val MOVIES_REMOTE_KEYS = "movies_remote_keys"
        const val TV_SHOWS_REMOTE_KEYS = "tv_shows_remote_keys"
        const val MOVIE_DETAILS = "movie_details"
        const val TV_SHOW_DETAILS = "tv_show_details"
        const val WISHLIST = "wishlist"
    }

    object Fields {
        const val ID = "id"
        const val NETWORK_ID = "network_id"
        const val IMDB_ID = "imdb_id"
        const val CAST_ID = "cast_id"
        const val CREDIT_ID = "credit_id"
        const val CAST = "cast"
        const val CREW = "crew"
        const val CREDITS = "credits"
        const val CHARACTER = "character"
        const val BUDGET = "budget"
        const val STATUS = "status"
        const val TAGLINE = "tagline"
        const val HOMEPAGE = "homepage"
        const val TITLE = "title"
        const val NAME = "name"
        const val OVERVIEW = "overview"
        const val POPULARITY = "popularity"
        const val RELEASE_DATE = "release_date"
        const val FIRST_AIR_DATE = "first_air_date"
        const val LAST_AIR_DATE = "last_air_date"
        const val ADULT = "adult"
        const val KNOWN_FOR_DEPARTMENT = "known_for_department"
        const val DEPARTMENT = "department"
        const val JOB = "job"
        const val ORDER = "order"
        const val GENDER = "gender"
        const val GENRES = "genres"
        const val GENRE_IDS = "genre_ids"
        const val ORIGINAL_TITLE = "original_title"
        const val ORIGINAL_NAME = "original_name"
        const val ORIGINAL_LANGUAGE = "original_language"
        const val ORIGIN_COUNTRY = "origin_country"
        const val VOTE_AVERAGE = "vote_average"
        const val VOTE_COUNT = "vote_count"
        const val POSTER_PATH = "poster_path"
        const val BACKDROP_PATH = "backdrop_path"
        const val PROFILE_PATH = "profile_path"
        const val VIDEO = "video"
        const val MEDIA_TYPE = "media_type"
        const val PREV_PAGE = "prev_page"
        const val NEXT_PAGE = "next_page"
        const val REVENUE = "revenue"
        const val RUNTIME = "runtime"
        const val EPISODE_RUN_TIME = "episode_run_time"
        const val IN_PRODUCTION = "in_production"
        const val LANGUAGES = "languages"
        const val NUMBER_OF_EPISODES = "number_of_episodes"
        const val NUMBER_OF_SEASONS = "number_of_seasons"
        const val TYPE = "type"
    }
}
