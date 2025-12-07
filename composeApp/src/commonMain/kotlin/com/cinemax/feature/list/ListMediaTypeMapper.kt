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

import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.discover
import cinemax.composeapp.generated.resources.most_popular
import cinemax.composeapp.generated.resources.now_playing
import cinemax.composeapp.generated.resources.top_rated
import cinemax.composeapp.generated.resources.trending
import cinemax.composeapp.generated.resources.upcoming_movies
import com.cinemax.core.domain.model.MediaTypeModel
import com.cinemax.core.navigation.ListMediaType
import org.jetbrains.compose.resources.StringResource

fun ListMediaType.toMovieMediaTypeModel(): MediaTypeModel.Movie = when (this) {
    ListMediaType.MOVIE_UPCOMING -> MediaTypeModel.Movie.Upcoming
    ListMediaType.MOVIE_TOP_RATED -> MediaTypeModel.Movie.TopRated
    ListMediaType.MOVIE_POPULAR -> MediaTypeModel.Movie.Popular
    ListMediaType.MOVIE_NOW_PLAYING -> MediaTypeModel.Movie.NowPlaying
    ListMediaType.MOVIE_DISCOVER -> MediaTypeModel.Movie.Discover
    ListMediaType.MOVIE_TRENDING -> MediaTypeModel.Movie.Trending
    ListMediaType.TV_TOP_RATED -> MediaTypeModel.Movie.TopRated
    ListMediaType.TV_POPULAR -> MediaTypeModel.Movie.Popular
    ListMediaType.TV_NOW_PLAYING -> MediaTypeModel.Movie.NowPlaying
    ListMediaType.TV_DISCOVER -> MediaTypeModel.Movie.Discover
    ListMediaType.TV_TRENDING -> MediaTypeModel.Movie.Trending
}

fun ListMediaType.toTvShowMediaTypeModel(): MediaTypeModel.TvShow? = when (this) {
    ListMediaType.MOVIE_UPCOMING -> null
    ListMediaType.MOVIE_TOP_RATED -> MediaTypeModel.TvShow.TopRated
    ListMediaType.MOVIE_POPULAR -> MediaTypeModel.TvShow.Popular
    ListMediaType.MOVIE_NOW_PLAYING -> MediaTypeModel.TvShow.NowPlaying
    ListMediaType.MOVIE_DISCOVER -> MediaTypeModel.TvShow.Discover
    ListMediaType.MOVIE_TRENDING -> MediaTypeModel.TvShow.Trending
    ListMediaType.TV_TOP_RATED -> MediaTypeModel.TvShow.TopRated
    ListMediaType.TV_POPULAR -> MediaTypeModel.TvShow.Popular
    ListMediaType.TV_NOW_PLAYING -> MediaTypeModel.TvShow.NowPlaying
    ListMediaType.TV_DISCOVER -> MediaTypeModel.TvShow.Discover
    ListMediaType.TV_TRENDING -> MediaTypeModel.TvShow.Trending
}

fun ListMediaType.toTitleResource(): StringResource = when (this) {
    ListMediaType.MOVIE_UPCOMING -> Res.string.upcoming_movies
    ListMediaType.MOVIE_TOP_RATED, ListMediaType.TV_TOP_RATED -> Res.string.top_rated
    ListMediaType.MOVIE_POPULAR, ListMediaType.TV_POPULAR -> Res.string.most_popular
    ListMediaType.MOVIE_NOW_PLAYING, ListMediaType.TV_NOW_PLAYING -> Res.string.now_playing
    ListMediaType.MOVIE_DISCOVER, ListMediaType.TV_DISCOVER -> Res.string.discover
    ListMediaType.MOVIE_TRENDING, ListMediaType.TV_TRENDING -> Res.string.trending
}

val ListMediaType.isMovieOnly: Boolean
    get() = this == ListMediaType.MOVIE_UPCOMING
