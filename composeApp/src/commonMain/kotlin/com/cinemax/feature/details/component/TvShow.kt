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

package com.cinemax.feature.details.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.details_runtime_text
import cinemax.composeapp.generated.resources.no_runtime
import com.cinemax.core.model.TvShowDetails
import com.cinemax.core.ui.mapper.NoTvShowRuntimeValue
import com.cinemax.core.ui.mapper.asNames
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun TvShowDetailsItem(
    tvShowDetails: TvShowDetails,
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    with(tvShowDetails) {
        DetailsItem(
            modifier = modifier,
            title = name,
            overview = overview,
            posterPath = posterPath,
            releaseDate = firstAirDate,
            runtime = if (episodeRunTime == NoTvShowRuntimeValue) {
                stringResource(Res.string.no_runtime)
            } else {
                stringResource(Res.string.details_runtime_text, episodeRunTime.toString())
            },
            genres = genres.asNames(),
            voteAverage = voteAverage,
            credits = credits,
            isWishlisted = isWishlisted,
            onBackButtonClick = onBackButtonClick,
            onWishlistButtonClick = onWishlistButtonClick
        )
    }
}

@Composable
internal fun TvShowDetailsItemPlaceholder(
    onBackButtonClick: () -> Unit,
    onWishlistButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DetailsItemPlaceholder(
        modifier = modifier,
        onBackButtonClick = onBackButtonClick,
        onWishlistButtonClick = onWishlistButtonClick
    )
}
