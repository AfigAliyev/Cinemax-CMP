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

package com.cinemax.feature.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cinemax.core.domain.model.MovieModel
import com.cinemax.core.domain.model.TvShowModel
import com.cinemax.core.domain.usecase.GetMoviesPagingUseCase
import com.cinemax.core.domain.usecase.GetTvShowsPagingUseCase
import com.cinemax.core.navigation.Route
import com.cinemax.core.ui.mapper.asMovie
import com.cinemax.core.ui.mapper.asTvShow
import com.cinemax.core.ui.mapper.pagingMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow

class ListViewModel(
    private val getMoviesPagingUseCase: GetMoviesPagingUseCase,
    private val getTvShowsPagingUseCase: GetTvShowsPagingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val route = Route.List.from(savedStateHandle)

    private val _uiState = MutableStateFlow(getInitialUiState())
    val uiState = _uiState.asStateFlow()

    private fun getInitialUiState(): ListUiState {
        val movieMediaType = route.mediaType.toMovieMediaTypeModel()
        val tvShowMediaType = route.mediaType.toTvShowMediaTypeModel()

        val movies = movieMediaType?.let { movieType ->
            getMoviesPagingUseCase(movieType)
                .pagingMap(MovieModel::asMovie)
                .cachedIn(viewModelScope)
        } ?: emptyFlow()

        val tvShows = tvShowMediaType?.let { tvShowType ->
            getTvShowsPagingUseCase(tvShowType)
                .pagingMap(TvShowModel::asTvShow)
                .cachedIn(viewModelScope)
        } ?: emptyFlow()

        return ListUiState(
            mediaType = route.mediaType,
            movies = movies,
            tvShows = tvShows
        )
    }
}
