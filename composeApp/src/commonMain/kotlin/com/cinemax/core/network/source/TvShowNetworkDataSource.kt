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
import com.cinemax.core.network.api.TvShowApiService
import com.cinemax.core.network.model.common.NetworkMediaType
import com.cinemax.core.network.model.response.TvShowResponseDto
import com.cinemax.core.network.util.DEFAULT_PAGE

class TvShowNetworkDataSource(private val tvShowApiService: TvShowApiService) {

    suspend fun getByMediaType(
        mediaType: NetworkMediaType.TvShow,
        page: Int = DEFAULT_PAGE
    ): CinemaxResult<TvShowResponseDto> = when (mediaType) {
        NetworkMediaType.TvShow.TOP_RATED -> tvShowApiService.getTopRated(page)
        NetworkMediaType.TvShow.POPULAR -> tvShowApiService.getPopular(page)
        NetworkMediaType.TvShow.NOW_PLAYING -> tvShowApiService.getOnTheAir(page)
        NetworkMediaType.TvShow.DISCOVER -> tvShowApiService.getDiscover(page)
        NetworkMediaType.TvShow.TRENDING -> tvShowApiService.getTrending(page)
    }

    suspend fun search(
        query: String,
        page: Int = DEFAULT_PAGE
    ): CinemaxResult<TvShowResponseDto> = tvShowApiService.search(query, page)
}
