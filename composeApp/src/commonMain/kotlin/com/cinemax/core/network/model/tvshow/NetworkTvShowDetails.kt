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

package com.cinemax.core.network.model.tvshow

import com.cinemax.core.network.model.common.NetworkCredits
import com.cinemax.core.network.model.common.NetworkGenre
import com.cinemax.core.network.model.common.NetworkProductionCompany
import com.cinemax.core.network.model.common.NetworkProductionCountry
import com.cinemax.core.network.model.common.NetworkSpokenLanguage
import com.cinemax.core.network.util.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTvShowDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("created_by")
    val createdBy: List<NetworkCreatedBy>,
    @SerialName("credits")
    val credits: NetworkCredits,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>,
    @Serializable(LocalDateSerializer::class)
    @SerialName("first_air_date")
    val firstAirDate: LocalDate? = null,
    @SerialName("genres")
    val genres: List<NetworkGenre>,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("in_production")
    val inProduction: Boolean,
    @SerialName("languages")
    val languages: List<String>,
    @Serializable(LocalDateSerializer::class)
    @SerialName("last_air_date")
    val lastAirDate: LocalDate? = null,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: NetworkEpisode? = null,
    @SerialName("networks")
    val organizations: List<NetworkOrganization>,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: NetworkEpisode? = null,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("production_companies")
    val productionCompanies: List<NetworkProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<NetworkProductionCountry>,
    @SerialName("seasons")
    val seasons: List<NetworkSeason>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<NetworkSpokenLanguage>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("type")
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkCreatedBy(
    @SerialName("id")
    val id: Int,
    @SerialName("credit_id")
    val creditId: String,
    @SerialName("name")
    val name: String,
    @SerialName("gender")
    val gender: Int,
    @SerialName("profile_path")
    val profilePath: String? = null
)

@Serializable
data class NetworkSeason(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("overview")
    val overview: String,
    @Serializable(LocalDateSerializer::class)
    @SerialName("air_date")
    val airDate: LocalDate? = null,
    @SerialName("episode_count")
    val episodeCount: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("poster_path")
    val posterPath: String? = null
)

@Serializable
data class NetworkEpisode(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("production_code")
    val productionCode: String,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkOrganization(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String,
    @SerialName("logo_path")
    val logoPath: String? = null
)
