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

package com.cinemax.feature.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.no_movie_wishlist
import cinemax.composeapp.generated.resources.no_tv_show_wishlist
import cinemax.composeapp.generated.resources.no_wishlist_results
import com.cinemax.core.designsystem.component.CinemaxMessage
import com.cinemax.core.designsystem.component.CinemaxSwipeRefresh
import com.cinemax.core.designsystem.theme.CinemaxTheme
import com.cinemax.core.model.MovieDetails
import com.cinemax.core.model.TvShowDetails
import com.cinemax.core.navigation.DetailsMediaType
import com.cinemax.core.ui.CinemaxCenteredError
import com.cinemax.core.ui.MediaTabPager
import com.cinemax.core.ui.VerticalMovieItem
import com.cinemax.core.ui.VerticalMovieItemPlaceholder
import com.cinemax.core.ui.VerticalTvShowItem
import com.cinemax.core.ui.VerticalTvShowItemPlaceholder
import com.cinemax.core.ui.mapper.asUserMessage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WishlistRoute(
    onNavigateToDetails: (DetailsMediaType, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WishlistViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistScreen(
        uiState = uiState,
        onRefreshMovies = { viewModel.onEvent(WishlistEvent.RefreshMovies) },
        onRefreshTvShows = { viewModel.onEvent(WishlistEvent.RefreshTvShows) },
        onMovieClick = { id -> onNavigateToDetails(DetailsMediaType.MOVIE, id) },
        onTvShowClick = { id -> onNavigateToDetails(DetailsMediaType.TV_SHOW, id) },
        onRetry = { viewModel.onEvent(WishlistEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(WishlistEvent.ClearError) },
        modifier = modifier
    )
}

@Composable
private fun WishlistScreen(
    uiState: WishlistUiState,
    onRefreshMovies: () -> Unit,
    onRefreshTvShows: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onTvShowClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {
        if (uiState.error != null) {
            CinemaxCenteredError(
                errorMessage = uiState.error.asUserMessage(),
                onRetry = onRetry,
                shouldShowOfflineMode = uiState.isOfflineModeAvailable,
                onOfflineModeClick = onOfflineModeClick
            )
        } else {
            MediaTabPager(
                moviesTabContent = {
                    MoviesContainer(
                        movies = uiState.movies,
                        isLoading = uiState.isMoviesLoading,
                        onRefresh = onRefreshMovies,
                        onClick = onMovieClick
                    )
                },
                tvShowsTabContent = {
                    TvShowsContainer(
                        tvShows = uiState.tvShows,
                        isLoading = uiState.isTvShowsLoading,
                        onRefresh = onRefreshTvShows,
                        onClick = onTvShowClick
                    )
                }
            )
        }
    }
}

@Composable
private fun MoviesContainer(
    movies: List<MovieDetails>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable LazyItemScope.() -> Unit = {
        CinemaxMessage(
            modifier = Modifier.fillParentMaxSize(),
            message = stringResource(Res.string.no_movie_wishlist),
            painter = painterResource(Res.drawable.no_wishlist_results)
        )
    }
) {
    CinemaxSwipeRefresh(
        modifier = modifier,
        isRefreshing = isLoading,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.medium),
            contentPadding = PaddingValues(CinemaxTheme.spacing.extraMedium)
        ) {
            when {
                movies.isNotEmpty() -> {
                    items(movies) { movie ->
                        VerticalMovieItem(movie = movie, onClick = onClick)
                    }
                }
                isLoading -> {
                    items(PlaceholderCount) { VerticalMovieItemPlaceholder() }
                }
                else -> item(content = emptyContent)
            }
        }
    }
}

@Composable
private fun TvShowsContainer(
    tvShows: List<TvShowDetails>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable LazyItemScope.() -> Unit = {
        CinemaxMessage(
            modifier = Modifier.fillParentMaxSize(),
            message = stringResource(Res.string.no_tv_show_wishlist),
            painter = painterResource(Res.drawable.no_wishlist_results)
        )
    }
) {
    CinemaxSwipeRefresh(
        modifier = modifier,
        isRefreshing = isLoading,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.medium),
            contentPadding = PaddingValues(CinemaxTheme.spacing.extraMedium)
        ) {
            when {
                tvShows.isNotEmpty() -> {
                    items(tvShows) { tvShow ->
                        VerticalTvShowItem(tvShow = tvShow, onClick = onClick)
                    }
                }
                isLoading -> {
                    items(PlaceholderCount) { VerticalTvShowItemPlaceholder() }
                }
                else -> item(content = emptyContent)
            }
        }
    }
}

private const val PlaceholderCount = 20
