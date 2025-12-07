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

package com.cinemax.core.data.mapper

import com.cinemax.core.database.model.Genre
import com.cinemax.core.domain.model.GenreModel
import com.cinemax.core.network.model.common.NetworkGenre
import com.cinemax.core.network.model.common.NetworkGenreWithId
import kotlin.jvm.JvmName

internal fun NetworkGenre.asGenre() = id.asGenreOrNull()

@JvmName("List_NetworkGenre_asGenres")
internal fun List<NetworkGenre>.asGenres() = mapNotNull(NetworkGenre::asGenre)

internal fun List<Int>.asGenres() = mapNotNull(Int::asGenreOrNull)

@JvmName("List_Int_asGenreModels")
internal fun List<Int>.asGenreModels() = mapNotNull(Int::asGenreModelOrNull)

internal fun Genre.asGenreModel() = GenreModel[genreName]
internal fun List<Genre>.asGenreModels() = map(Genre::asGenreModel)

private fun Int.asGenreOrNull() = NetworkGenreWithId[this]?.let { Genre[it.genreName] }
private fun Int.asGenreModelOrNull() = NetworkGenreWithId[this]?.let { GenreModel[it.genreName] }
