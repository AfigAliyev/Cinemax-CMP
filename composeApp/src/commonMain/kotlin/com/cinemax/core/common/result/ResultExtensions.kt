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

package com.cinemax.core.common.result

inline fun <T> CinemaxResult<T>.onSuccess(action: (T) -> Unit): CinemaxResult<T> {
    if (this is CinemaxResult.Success) {
        action(value)
    }
    return this
}

inline fun <T> CinemaxResult<T>.onFailure(action: (Throwable) -> Unit): CinemaxResult<T> {
    if (this is CinemaxResult.Failure) {
        action(error)
    }
    return this
}

inline fun <T> CinemaxResult<T>.onLoading(action: (T?) -> Unit): CinemaxResult<T> {
    if (this is CinemaxResult.Loading) {
        action(value)
    }
    return this
}

inline fun <T, R> CinemaxResult<T>.map(transform: (T) -> R): CinemaxResult<R> = when (this) {
    is CinemaxResult.Loading -> CinemaxResult.Loading(value?.let(transform))
    is CinemaxResult.Success.Value -> CinemaxResult.success(transform(value))
    is CinemaxResult.Success.HttpResponse -> CinemaxResult.success(
        transform(value),
        statusCode,
        statusMessage,
        url
    )
    is CinemaxResult.Failure.Error -> CinemaxResult.failure(error, value?.let(transform))
    is CinemaxResult.Failure.HttpError -> CinemaxResult.failure(error, value?.let(transform))
}

inline fun <T, R> CinemaxResult<T>.flatMap(
    transform: (T) -> CinemaxResult<R>
): CinemaxResult<R> = when (this) {
    is CinemaxResult.Loading -> CinemaxResult.Loading(null)
    is CinemaxResult.Success -> transform(value)
    is CinemaxResult.Failure.Error -> CinemaxResult.failure(error, null)
    is CinemaxResult.Failure.HttpError -> CinemaxResult.failure(error, null)
}

fun <T> CinemaxResult<T>.getOrNull(): T? = when (this) {
    is CinemaxResult.Success -> value
    else -> null
}

fun <T> CinemaxResult<T>.getOrThrow(): T = when (this) {
    is CinemaxResult.Success -> value
    is CinemaxResult.Failure -> throw error
    is CinemaxResult.Loading -> error("Result is still loading")
}

inline fun <T> CinemaxResult<T>.getOrElse(defaultValue: () -> T): T = when (this) {
    is CinemaxResult.Success -> value
    else -> defaultValue()
}
