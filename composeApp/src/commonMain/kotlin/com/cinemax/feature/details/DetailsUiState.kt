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

package com.cinemax.feature.details

import com.cinemax.core.model.MovieDetails
import com.cinemax.core.model.TvShowDetails
import com.cinemax.core.model.UserMessage
import com.cinemax.core.navigation.DetailsMediaType

data class DetailsUiState(
    val mediaType: DetailsMediaType,
    val id: Int,
    val movie: MovieDetails? = null,
    val tvShow: TvShowDetails? = null,
    val isLoading: Boolean = false,
    val userMessage: UserMessage? = null,
    val error: Throwable? = null,
    val isOfflineModeAvailable: Boolean = false
)
