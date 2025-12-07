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

package com.cinemax.core.ui.mapper

import androidx.compose.runtime.Composable
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.action
import cinemax.composeapp.generated.resources.action_adventure
import cinemax.composeapp.generated.resources.adventure
import cinemax.composeapp.generated.resources.animation
import cinemax.composeapp.generated.resources.comedy
import cinemax.composeapp.generated.resources.crime
import cinemax.composeapp.generated.resources.documentary
import cinemax.composeapp.generated.resources.drama
import cinemax.composeapp.generated.resources.family
import cinemax.composeapp.generated.resources.fantasy
import cinemax.composeapp.generated.resources.history
import cinemax.composeapp.generated.resources.horror
import cinemax.composeapp.generated.resources.kids
import cinemax.composeapp.generated.resources.music
import cinemax.composeapp.generated.resources.mystery
import cinemax.composeapp.generated.resources.news
import cinemax.composeapp.generated.resources.reality
import cinemax.composeapp.generated.resources.romance
import cinemax.composeapp.generated.resources.science_fiction
import cinemax.composeapp.generated.resources.science_fiction_fantasy
import cinemax.composeapp.generated.resources.soap
import cinemax.composeapp.generated.resources.talk
import cinemax.composeapp.generated.resources.thriller
import cinemax.composeapp.generated.resources.tv_movie
import cinemax.composeapp.generated.resources.unknown
import cinemax.composeapp.generated.resources.war
import cinemax.composeapp.generated.resources.war_politics
import cinemax.composeapp.generated.resources.western
import com.cinemax.core.domain.model.GenreModel
import com.cinemax.core.model.Genre
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun List<Genre>.asNames(): List<String> = map { genre -> stringResource(genre.asNameResource()) }

internal fun List<GenreModel>.asGenres(): List<Genre> = map { genre -> genre.asGenre() }

private fun GenreModel.asGenre(): Genre = genreModelToGenre.getValue(this)

private fun Genre.asNameResource(): StringResource = genreNameResources.getValue(this)

private val genreModelToGenre = mapOf(
    GenreModel.ACTION to Genre.ACTION,
    GenreModel.ADVENTURE to Genre.ADVENTURE,
    GenreModel.ACTION_ADVENTURE to Genre.ACTION_ADVENTURE,
    GenreModel.ANIMATION to Genre.ANIMATION,
    GenreModel.COMEDY to Genre.COMEDY,
    GenreModel.CRIME to Genre.CRIME,
    GenreModel.DOCUMENTARY to Genre.DOCUMENTARY,
    GenreModel.DRAMA to Genre.DRAMA,
    GenreModel.FAMILY to Genre.FAMILY,
    GenreModel.FANTASY to Genre.FANTASY,
    GenreModel.HISTORY to Genre.HISTORY,
    GenreModel.HORROR to Genre.HORROR,
    GenreModel.KIDS to Genre.KIDS,
    GenreModel.MUSIC to Genre.MUSIC,
    GenreModel.MYSTERY to Genre.MYSTERY,
    GenreModel.NEWS to Genre.NEWS,
    GenreModel.REALITY to Genre.REALITY,
    GenreModel.ROMANCE to Genre.ROMANCE,
    GenreModel.SCIENCE_FICTION to Genre.SCIENCE_FICTION,
    GenreModel.SCIENCE_FICTION_FANTASY to Genre.SCIENCE_FICTION_FANTASY,
    GenreModel.SOAP to Genre.SOAP,
    GenreModel.TALK to Genre.TALK,
    GenreModel.THRILLER to Genre.THRILLER,
    GenreModel.TV_MOVIE to Genre.TV_MOVIE,
    GenreModel.WAR to Genre.WAR,
    GenreModel.WAR_POLITICS to Genre.WAR_POLITICS,
    GenreModel.WESTERN to Genre.WESTERN
)

private val genreNameResources = mapOf(
    Genre.ACTION to Res.string.action,
    Genre.ADVENTURE to Res.string.adventure,
    Genre.ACTION_ADVENTURE to Res.string.action_adventure,
    Genre.ANIMATION to Res.string.animation,
    Genre.COMEDY to Res.string.comedy,
    Genre.CRIME to Res.string.crime,
    Genre.DOCUMENTARY to Res.string.documentary,
    Genre.DRAMA to Res.string.drama,
    Genre.FAMILY to Res.string.family,
    Genre.FANTASY to Res.string.fantasy,
    Genre.HISTORY to Res.string.history,
    Genre.HORROR to Res.string.horror,
    Genre.KIDS to Res.string.kids,
    Genre.MUSIC to Res.string.music,
    Genre.MYSTERY to Res.string.mystery,
    Genre.NEWS to Res.string.news,
    Genre.REALITY to Res.string.reality,
    Genre.ROMANCE to Res.string.romance,
    Genre.SCIENCE_FICTION to Res.string.science_fiction,
    Genre.SCIENCE_FICTION_FANTASY to Res.string.science_fiction_fantasy,
    Genre.SOAP to Res.string.soap,
    Genre.TALK to Res.string.talk,
    Genre.THRILLER to Res.string.thriller,
    Genre.TV_MOVIE to Res.string.tv_movie,
    Genre.WAR to Res.string.war,
    Genre.WAR_POLITICS to Res.string.war_politics,
    Genre.WESTERN to Res.string.western,
    Genre.UNKNOWN to Res.string.unknown
)
