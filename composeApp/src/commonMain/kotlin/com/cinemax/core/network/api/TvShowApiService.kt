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

package com.cinemax.core.network.api

import com.cinemax.core.common.result.CinemaxResult
import com.cinemax.core.network.model.response.TvShowResponseDto
import com.cinemax.core.network.model.tvshow.NetworkTvShowDetails
import com.cinemax.core.network.util.Constants
import com.cinemax.core.network.util.DEFAULT_PAGE
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface TvShowApiService {
    suspend fun getTopRated(page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
    suspend fun getPopular(page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
    suspend fun getOnTheAir(page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
    suspend fun getDiscover(page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
    suspend fun getTrending(page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
    suspend fun getDetailsById(
        id: Int,
        appendToResponse: String = Constants.Fields.DETAILS_APPEND_TO_RESPONSE
    ): CinemaxResult<NetworkTvShowDetails>
    suspend fun search(query: String, page: Int = DEFAULT_PAGE): CinemaxResult<TvShowResponseDto>
}

class TvShowApiServiceImpl(private val httpClient: HttpClient) : TvShowApiService {

    override suspend fun getTopRated(page: Int): CinemaxResult<TvShowResponseDto> = safeApiCall {
        httpClient.get(Constants.Path.TOP_RATED_TV_SHOW) {
            parameter(Constants.Fields.PAGE, page)
        }
    }

    override suspend fun getPopular(page: Int): CinemaxResult<TvShowResponseDto> = safeApiCall {
        httpClient.get(Constants.Path.POPULAR_TV_SHOW) {
            parameter(Constants.Fields.PAGE, page)
        }
    }

    override suspend fun getOnTheAir(page: Int): CinemaxResult<TvShowResponseDto> = safeApiCall {
        httpClient.get(Constants.Path.ON_THE_AIR_TV_SHOW) {
            parameter(Constants.Fields.PAGE, page)
        }
    }

    override suspend fun getDiscover(page: Int): CinemaxResult<TvShowResponseDto> = safeApiCall {
        httpClient.get(Constants.Path.DISCOVER_TV_SHOW) {
            parameter(Constants.Fields.PAGE, page)
        }
    }

    override suspend fun getTrending(page: Int): CinemaxResult<TvShowResponseDto> = safeApiCall {
        httpClient.get(Constants.Path.TRENDING_TV_SHOW) {
            parameter(Constants.Fields.PAGE, page)
        }
    }

    override suspend fun getDetailsById(
        id: Int,
        appendToResponse: String
    ): CinemaxResult<NetworkTvShowDetails> = safeApiCall {
        httpClient.get("tv/$id") {
            parameter(Constants.Fields.APPEND_TO_RESPONSE, appendToResponse)
        }
    }

    override suspend fun search(query: String, page: Int): CinemaxResult<TvShowResponseDto> =
        safeApiCall {
            httpClient.get(Constants.Path.SEARCH_TV_SHOW) {
                parameter(Constants.Fields.QUERY, query)
                parameter(Constants.Fields.PAGE, page)
            }
        }
}
