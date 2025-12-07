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

package com.cinemax.feature.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.clear
import cinemax.composeapp.generated.resources.discover
import cinemax.composeapp.generated.resources.ic_search
import cinemax.composeapp.generated.resources.search_placeholder
import cinemax.composeapp.generated.resources.trending
import com.cinemax.core.designsystem.component.CinemaxIconButton
import com.cinemax.core.designsystem.component.CinemaxSwipeRefresh
import com.cinemax.core.designsystem.component.CinemaxTextField
import com.cinemax.core.designsystem.theme.CinemaxTheme
import com.cinemax.core.model.MediaType
import com.cinemax.core.model.Movie
import com.cinemax.core.model.TvShow
import com.cinemax.core.navigation.DetailsMediaType
import com.cinemax.core.navigation.ListMediaType
import com.cinemax.core.ui.CinemaxCenteredError
import com.cinemax.core.ui.MediaTabPager
import com.cinemax.core.ui.MoviesAndTvShowsContainer
import com.cinemax.core.ui.MoviesContainer
import com.cinemax.core.ui.TvShowsContainer
import com.cinemax.core.ui.mapper.asUserMessage
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoute(
    onNavigateToList: (ListMediaType) -> Unit,
    onNavigateToDetails: (DetailsMediaType, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchMovies = uiState.searchMovies.collectAsLazyPagingItems()
    val searchTvShows = uiState.searchTvShows.collectAsLazyPagingItems()

    SearchScreen(
        modifier = modifier,
        uiState = uiState,
        searchMovies = searchMovies,
        searchTvShows = searchTvShows,
        onRefresh = { viewModel.onEvent(SearchEvent.Refresh) },
        onQueryChange = { viewModel.onEvent(SearchEvent.ChangeQuery(it)) },
        onSeeAllClick = onNavigateToList,
        onMovieClick = { id -> onNavigateToDetails(DetailsMediaType.MOVIE, id) },
        onTvShowClick = { id -> onNavigateToDetails(DetailsMediaType.TV_SHOW, id) },
        onRetry = { viewModel.onEvent(SearchEvent.Retry) },
        onOfflineModeClick = { viewModel.onEvent(SearchEvent.ClearError) }
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    searchMovies: LazyPagingItems<Movie>,
    searchTvShows: LazyPagingItems<TvShow>,
    onRefresh: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSeeAllClick: (ListMediaType) -> Unit,
    onMovieClick: (Int) -> Unit,
    onTvShowClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onOfflineModeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
    ) {
        SearchTextField(
            query = uiState.query,
            onQueryChange = onQueryChange
        )

        AnimatedContent(targetState = uiState.isSearching) { isSearching ->
            if (isSearching) {
                MediaTabPager(
                    moviesTabContent = {
                        MoviesContainer(movies = searchMovies, onClick = onMovieClick)
                    },
                    tvShowsTabContent = {
                        TvShowsContainer(tvShows = searchTvShows, onClick = onTvShowClick)
                    }
                )
            } else {
                CinemaxSwipeRefresh(
                    isRefreshing = uiState.isLoading,
                    onRefresh = onRefresh
                ) {
                    if (uiState.error != null) {
                        CinemaxCenteredError(
                            errorMessage = uiState.error.asUserMessage(),
                            onRetry = onRetry,
                            shouldShowOfflineMode = uiState.isOfflineModeAvailable,
                            onOfflineModeClick = onOfflineModeClick
                        )
                    } else {
                        SuggestionsContent(
                            movies = uiState.movies,
                            tvShows = uiState.tvShows,
                            onSeeAllClick = onSeeAllClick,
                            onMovieClick = onMovieClick,
                            onTvShowClick = onTvShowClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current
) {
    CinemaxTextField(
        modifier = modifier
            .padding(
                start = CinemaxTheme.spacing.extraMedium,
                top = CinemaxTheme.spacing.small,
                end = CinemaxTheme.spacing.extraMedium,
                bottom = CinemaxTheme.spacing.extraMedium
            )
            .fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        placeholder = stringResource(Res.string.search_placeholder),
        icon = Res.drawable.ic_search,
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn() + scaleIn(),
                exit = scaleOut() + fadeOut()
            ) {
                CinemaxIconButton(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(Res.string.clear),
                    onClick = { onQueryChange("") }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
    )
}

@Composable
private fun SuggestionsContent(
    movies: Map<MediaType.Movie, List<Movie>>,
    tvShows: Map<MediaType.TvShow, List<TvShow>>,
    onSeeAllClick: (ListMediaType) -> Unit,
    onMovieClick: (Int) -> Unit,
    onTvShowClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.extraMedium),
        contentPadding = PaddingValues(bottom = CinemaxTheme.spacing.extraMedium)
    ) {
        item {
            MoviesAndTvShowsContainer(
                titleResource = Res.string.discover,
                onSeeAllClick = { onSeeAllClick(ListMediaType.MOVIE_DISCOVER) },
                movies = movies[MediaType.Movie.Discover].orEmpty(),
                tvShows = tvShows[MediaType.TvShow.Discover].orEmpty(),
                onMovieClick = onMovieClick,
                onTvShowClick = onTvShowClick
            )
        }
        item {
            MoviesAndTvShowsContainer(
                titleResource = Res.string.trending,
                onSeeAllClick = { onSeeAllClick(ListMediaType.MOVIE_TRENDING) },
                movies = movies[MediaType.Movie.Trending].orEmpty(),
                tvShows = tvShows[MediaType.TvShow.Trending].orEmpty(),
                onMovieClick = onMovieClick,
                onTvShowClick = onTvShowClick
            )
        }
    }
}
