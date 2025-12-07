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
import com.cinemax.core.common.result.HttpException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse
): CinemaxResult<T> = try {
    val response = apiCall()
    if (response.status.isSuccess()) {
        CinemaxResult.success(
            value = response.body<T>(),
            statusCode = response.status.value,
            statusMessage = response.status.description,
            url = response.call.request.url.toString()
        )
    } else {
        CinemaxResult.failure(
            HttpException(
                statusCode = response.status.value,
                statusMessage = response.status.description,
                url = response.call.request.url.toString()
            )
        )
    }
} catch (e: ClientRequestException) {
    CinemaxResult.failure(
        HttpException(
            statusCode = e.response.status.value,
            statusMessage = e.response.status.description,
            url = e.response.call.request.url.toString(),
            cause = e
        )
    )
} catch (e: ServerResponseException) {
    CinemaxResult.failure(
        HttpException(
            statusCode = e.response.status.value,
            statusMessage = e.response.status.description,
            url = e.response.call.request.url.toString(),
            cause = e
        )
    )
} catch (e: IOException) {
    CinemaxResult.failure(e)
} catch (e: SerializationException) {
    CinemaxResult.failure(e)
}
