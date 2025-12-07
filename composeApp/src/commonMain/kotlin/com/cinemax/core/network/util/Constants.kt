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

package com.cinemax.core.network.util

internal object Constants {
    const val API_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY_QUERY_PARAM = "api_key"
    const val PAGE_SIZE = 20
    const val DEFAULT_PAGE = 1

    object Path {
        const val UPCOMING_MOVIE = "movie/upcoming"
        const val TOP_RATED_MOVIE = "movie/top_rated"
        const val POPULAR_MOVIE = "movie/popular"
        const val NOW_PLAYING_MOVIE = "movie/now_playing"
        const val DISCOVER_MOVIE = "discover/movie"
        const val TRENDING_MOVIE = "trending/movie/day"
        const val DETAILS_MOVIE = "movie/{id}"
        const val SEARCH_MOVIE = "search/movie"

        const val TOP_RATED_TV_SHOW = "tv/top_rated"
        const val POPULAR_TV_SHOW = "tv/popular"
        const val ON_THE_AIR_TV_SHOW = "tv/on_the_air"
        const val DISCOVER_TV_SHOW = "discover/tv"
        const val TRENDING_TV_SHOW = "trending/tv/day"
        const val DETAILS_TV_SHOW = "tv/{id}"
        const val SEARCH_TV_SHOW = "search/tv"
    }

    object Fields {
        const val ID = "id"
        const val IMDB_ID = "imdb_id"
        const val CAST_ID = "cast_id"
        const val CREDIT_ID = "credit_id"
        const val SHOW_ID = "show_id"
        const val CAST = "cast"
        const val CREW = "crew"
        const val CREDITS = "credits"
        const val CHARACTER = "character"
        const val BUDGET = "budget"
        const val BELONGS_TO_COLLECTION = "belongs_to_collection"
        const val CREATED_BY = "created_by"
        const val PRODUCTION_COMPANIES = "production_companies"
        const val PRODUCTION_COUNTRIES = "production_countries"
        const val PRODUCTION_CODE = "production_code"
        const val SPOKEN_LANGUAGES = "spoken_languages"
        const val SEASON_NUMBER = "season_number"
        const val STATUS = "status"
        const val SEASONS = "seasons"
        const val TYPE = "type"
        const val TAGLINE = "tagline"
        const val HOMEPAGE = "homepage"
        const val AIR_DATE = "air_date"
        const val EPISODE_NUMBER = "episode_number"
        const val EPISODE_COUNT = "episode_count"
        const val EPISODE_RUN_TIME = "episode_run_time"
        const val PAGE = "page"
        const val TOTAL_PAGES = "total_pages"
        const val RESULTS = "results"
        const val TOTAL_RESULTS = "total_results"
        const val DATES = "dates"
        const val DEPARTMENT = "department"
        const val TITLE = "title"
        const val NAME = "name"
        const val ENGLISH_NAME = "english_name"
        const val OVERVIEW = "overview"
        const val POPULARITY = "popularity"
        const val RELEASE_DATE = "release_date"
        const val FIRST_AIR_DATE = "first_air_date"
        const val LAST_AIR_DATE = "last_air_date"
        const val LAST_EPISODE_TO_AIR = "last_episode_to_air"
        const val NEXT_EPISODE_TO_AIR = "next_episode_to_air"
        const val ADULT = "adult"
        const val KNOWN_FOR_DEPARTMENT = "known_for_department"
        const val NETWORKS = "networks"
        const val NUMBER_OF_EPISODES = "number_of_episodes"
        const val NUMBER_OF_SEASONS = "number_of_seasons"
        const val LANGUAGES = "languages"
        const val IN_PRODUCTION = "in_production"
        const val ORDER = "order"
        const val JOB = "job"
        const val REVENUE = "revenue"
        const val RUNTIME = "runtime"
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
        const val LOGO_PATH = "logo_path"
        const val STILL_PATH = "still_path"
        const val VIDEO = "video"
        const val MAXIMUM = "maximum"
        const val MINIMUM = "minimum"
        const val ISO_3166_1 = "iso_3166_1"
        const val ISO_639_1 = "iso_639_1"
        const val APPEND_TO_RESPONSE = "append_to_response"
        const val QUERY = "query"

        val DETAILS_APPEND_TO_RESPONSE = buildAppendToResponse(CREDITS)

        private fun buildAppendToResponse(vararg fields: String) =
            fields.joinToString(separator = APPEND_TO_RESPONSE_SEPARATOR)

        private const val APPEND_TO_RESPONSE_SEPARATOR = ","
    }
}

const val PAGE_SIZE = Constants.PAGE_SIZE
const val DEFAULT_PAGE = Constants.DEFAULT_PAGE
const val MESSAGE_UNHANDLED_STATE = "Unhandled state."
