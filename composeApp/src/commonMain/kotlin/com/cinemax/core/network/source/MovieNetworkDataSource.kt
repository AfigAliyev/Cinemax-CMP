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

package com.cinemax.core.network.source

import com.cinemax.core.common.result.CinemaxResult
import com.cinemax.core.network.api.MovieApiService
import com.cinemax.core.network.model.common.NetworkMediaType
import com.cinemax.core.network.model.response.MovieResponseDto
import com.cinemax.core.network.util.DEFAULT_PAGE

class MovieNetworkDataSource(private val movieApiService: MovieApiService) {

    suspend fun getByMediaType(
        mediaType: NetworkMediaType.Movie,
        page: Int = DEFAULT_PAGE
    ): CinemaxResult<MovieResponseDto> = when (mediaType) {
        NetworkMediaType.Movie.UPCOMING -> movieApiService.getUpcoming(page)
        NetworkMediaType.Movie.TOP_RATED -> movieApiService.getTopRated(page)
        NetworkMediaType.Movie.POPULAR -> movieApiService.getPopular(page)
        NetworkMediaType.Movie.NOW_PLAYING -> movieApiService.getNowPlaying(page)
        NetworkMediaType.Movie.DISCOVER -> movieApiService.getDiscover(page)
        NetworkMediaType.Movie.TRENDING -> movieApiService.getTrending(page)
    }

    suspend fun search(
        query: String,
        page: Int = DEFAULT_PAGE
    ): CinemaxResult<MovieResponseDto> = movieApiService.search(query, page)
}
