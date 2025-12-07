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

package com.cinemax.core.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.cinemax.CinemaxAppState
import com.cinemax.feature.home.HomeRoute
import com.cinemax.feature.settings.SettingsRoute
import com.cinemax.feature.list.ListRoute
import com.cinemax.feature.search.SearchRoute
import com.cinemax.feature.details.DetailsRoute
import com.cinemax.feature.wishlist.WishlistRoute
import kotlin.reflect.typeOf

@Composable
fun CinemaxNavHost(
    appState: CinemaxAppState,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = appState.navController,
        startDestination = MainGraph
    ) {
        mainGraph(appState)

        composable<Route.List>(
            typeMap = mapOf(typeOf<ListMediaType>() to serializableType<ListMediaType>())
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Route.List>()
            ListRoute(
                mediaType = route.mediaType,
                onNavigateBack = appState::navigateBack,
                onNavigateToDetails = { mediaType, id ->
                    appState.navigateTo(Route.Details(id = id, mediaType = mediaType))
                }
            )
        }

        composable<Route.Details>(
            typeMap = mapOf(typeOf<DetailsMediaType>() to serializableType<DetailsMediaType>())
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Details>()
            DetailsRoute(
                mediaType = route.mediaType,
                id = route.id,
                onNavigateBack = appState::navigateBack,
                onShowMessage = onShowMessage
            )
        }
    }
}

fun NavGraphBuilder.mainGraph(
    appState: CinemaxAppState
) {
    navigation<MainGraph>(startDestination = Route.Main.Home) {
        composable<Route.Main.Home>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            HomeRoute(
                onNavigateToList = { mediaType ->
                    appState.navigateTo(Route.List(mediaType))
                },
                onNavigateToDetails = { mediaType, id ->
                    appState.navigateTo(Route.Details(id = id, mediaType = mediaType))
                }
            )
        }

        composable<Route.Main.Search>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            SearchRoute(
                onNavigateToList = { mediaType ->
                    appState.navigateTo(Route.List(mediaType))
                },
                onNavigateToDetails = { mediaType, id ->
                    appState.navigateTo(Route.Details(id = id, mediaType = mediaType))
                }
            )
        }

        composable<Route.Main.Wishlist>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            WishlistRoute(
                onNavigateToDetails = { mediaType, id ->
                    appState.navigateTo(Route.Details(id = id, mediaType = mediaType))
                }
            )
        }

        composable<Route.Main.Settings>(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            SettingsRoute()
        }
    }
}
