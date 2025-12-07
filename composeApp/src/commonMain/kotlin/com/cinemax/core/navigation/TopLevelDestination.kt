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

import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.home
import cinemax.composeapp.generated.resources.ic_home
import cinemax.composeapp.generated.resources.ic_search
import cinemax.composeapp.generated.resources.ic_settings
import cinemax.composeapp.generated.resources.ic_wishlist
import cinemax.composeapp.generated.resources.search
import cinemax.composeapp.generated.resources.settings
import cinemax.composeapp.generated.resources.wishlist
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class TopLevelDestination(
    val route: Route.Main,
    val icon: DrawableResource,
    val label: StringResource
) {
    Home(
        route = Route.Main.Home,
        icon = Res.drawable.ic_home,
        label = Res.string.home
    ),
    Search(
        route = Route.Main.Search,
        icon = Res.drawable.ic_search,
        label = Res.string.search
    ),
    Wishlist(
        route = Route.Main.Wishlist,
        icon = Res.drawable.ic_wishlist,
        label = Res.string.wishlist
    ),
    Settings(
        route = Route.Main.Settings,
        icon = Res.drawable.ic_settings,
        label = Res.string.settings
    )
}
