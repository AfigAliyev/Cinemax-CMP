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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.add_movie_wishlist
import cinemax.composeapp.generated.resources.add_tv_show_wishlist
import cinemax.composeapp.generated.resources.remove_movie_wishlist
import cinemax.composeapp.generated.resources.remove_tv_show_wishlist
import com.cinemax.core.common.result.handle
import com.cinemax.core.domain.usecase.AddMovieToWishlistUseCase
import com.cinemax.core.domain.usecase.AddTvShowToWishlistUseCase
import com.cinemax.core.domain.usecase.GetMovieDetailsUseCase
import com.cinemax.core.domain.usecase.GetTvShowDetailsUseCase
import com.cinemax.core.domain.usecase.RemoveMovieFromWishlistUseCase
import com.cinemax.core.domain.usecase.RemoveTvShowFromWishlistUseCase
import com.cinemax.core.model.UserMessage
import com.cinemax.core.navigation.DetailsMediaType
import com.cinemax.core.ui.common.EventHandler
import com.cinemax.core.ui.mapper.asMovieDetails
import com.cinemax.core.ui.mapper.asTvShowDetails
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
    private val addMovieToWishlistUseCase: AddMovieToWishlistUseCase,
    private val addTvShowToWishlistUseCase: AddTvShowToWishlistUseCase,
    private val removeMovieFromWishlistUseCase: RemoveMovieFromWishlistUseCase,
    private val removeTvShowFromWishlistUseCase: RemoveTvShowFromWishlistUseCase,
    mediaType: DetailsMediaType,
    id: Int
) : ViewModel(), EventHandler<DetailsEvent> {
    private val _uiState = MutableStateFlow(DetailsUiState(mediaType = mediaType, id = id))
    val uiState = _uiState.asStateFlow()

    private var contentJob = loadContent()

    override fun onEvent(event: DetailsEvent) = when (event) {
        DetailsEvent.WishlistMovie -> onWishlistMovie()
        DetailsEvent.WishlistTvShow -> onWishlistTvShow()
        DetailsEvent.Refresh -> onRefresh()
        DetailsEvent.Retry -> onRetry()
        DetailsEvent.ClearError -> onClearError()
        DetailsEvent.ClearUserMessage -> onClearUserMessage()
    }

    private fun loadContent(): Job = when (uiState.value.mediaType) {
        DetailsMediaType.MOVIE -> loadMovie(uiState.value.id)
        DetailsMediaType.TV_SHOW -> loadTvShow(uiState.value.id)
    }

    private fun onWishlistMovie() {
        _uiState.update {
            it.copy(movie = it.movie?.copy(isWishlisted = !it.movie.isWishlisted))
        }
        viewModelScope.launch {
            uiState.value.movie?.let { movie ->
                if (movie.isWishlisted) {
                    addMovieToWishlistUseCase(movie.id)
                    setUserMessage(UserMessage(resource = Res.string.add_movie_wishlist))
                } else {
                    removeMovieFromWishlistUseCase(movie.id)
                    setUserMessage(UserMessage(resource = Res.string.remove_movie_wishlist))
                }
            }
        }
    }

    private fun onWishlistTvShow() {
        _uiState.update {
            it.copy(tvShow = it.tvShow?.copy(isWishlisted = !it.tvShow.isWishlisted))
        }
        viewModelScope.launch {
            uiState.value.tvShow?.let { tvShow ->
                if (tvShow.isWishlisted) {
                    addTvShowToWishlistUseCase(tvShow.id)
                    setUserMessage(UserMessage(resource = Res.string.add_tv_show_wishlist))
                } else {
                    removeTvShowFromWishlistUseCase(tvShow.id)
                    setUserMessage(UserMessage(resource = Res.string.remove_tv_show_wishlist))
                }
            }
        }
    }

    private fun setUserMessage(userMessage: UserMessage) =
        _uiState.update { it.copy(userMessage = userMessage) }

    private fun onRefresh() {
        contentJob.cancel()
        contentJob = loadContent()
    }

    private fun onRetry() {
        onClearError()
        onRefresh()
    }

    private fun onClearError() = _uiState.update { it.copy(error = null) }
    private fun onClearUserMessage() = _uiState.update { it.copy(userMessage = null) }

    private fun loadMovie(id: Int) = viewModelScope.launch {
        getMovieDetailsUseCase(id).handle {
            onLoading { movie ->
                _uiState.update { it.copy(movie = movie?.asMovieDetails(), isLoading = true) }
            }
            onSuccess { movie ->
                _uiState.update { it.copy(movie = movie?.asMovieDetails(), isLoading = false) }
            }
            onFailure(::handleFailure)
        }
    }

    private fun loadTvShow(id: Int) = viewModelScope.launch {
        getTvShowDetailsUseCase(id).handle {
            onLoading { tvShow ->
                _uiState.update { it.copy(tvShow = tvShow?.asTvShowDetails(), isLoading = true) }
            }
            onSuccess { tvShow ->
                _uiState.update { it.copy(tvShow = tvShow?.asTvShowDetails(), isLoading = false) }
            }
            onFailure(::handleFailure)
        }
    }

    private fun handleFailure(error: Throwable) = _uiState.update {
        it.copy(
            error = error,
            isOfflineModeAvailable = it.movie != null || it.tvShow != null,
            isLoading = false
        )
    }
}
