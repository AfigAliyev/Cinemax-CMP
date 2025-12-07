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

package com.cinemax.core.network.model.movie

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
data class NetworkMovieDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("belongs_to_collection")
    val belongsToCollection: NetworkBelongsToCollection? = null,
    @SerialName("budget")
    val budget: Int,
    @SerialName("genres")
    val genres: List<NetworkGenre>,
    @SerialName("homepage")
    val homepage: String? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
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
    @Serializable(LocalDateSerializer::class)
    @SerialName("release_date")
    val releaseDate: LocalDate? = null,
    @SerialName("revenue")
    val revenue: Long,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<NetworkSpokenLanguage>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("credits")
    val credits: NetworkCredits
)

@Serializable
data class NetworkBelongsToCollection(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null
)
