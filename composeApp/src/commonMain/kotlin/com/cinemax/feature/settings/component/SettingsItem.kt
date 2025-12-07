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

package com.cinemax.feature.settings.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.forward
import cinemax.composeapp.generated.resources.ic_arrow_forward
import com.cinemax.core.designsystem.component.CinemaxCenteredBox
import com.cinemax.core.designsystem.component.CinemaxIcon
import com.cinemax.core.designsystem.theme.CinemaxTheme
import com.cinemax.feature.settings.model.Settings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SettingsItem(
    settings: Settings,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = modifier
            .then(
                when (settings) {
                    is Settings.Action -> Modifier.clickable(onClick = settings.onClick)
                    is Settings.UrlAction -> Modifier.clickable { uriHandler.openUri(settings.url) }
                    is Settings.Info -> Modifier
                }
            )
            .padding(CinemaxTheme.spacing.medium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.medium)
        ) {
            IconBox(
                icon = settings.icon,
                contentDescription = stringResource(settings.title)
            )
            TitleText(title = stringResource(settings.title))
        }
        when (settings) {
            is Settings.Info -> ValueText(value = settings.value)
            is Settings.Action, is Settings.UrlAction -> ForwardButton()
        }
    }
}

@Composable
private fun IconBox(
    icon: DrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    CinemaxCenteredBox(
        modifier = modifier
            .size(IconShapeSize)
            .background(color = CinemaxTheme.colors.primarySoft, shape = CircleShape)
    ) {
        CinemaxIcon(
            icon = icon,
            contentDescription = contentDescription,
            tint = CinemaxTheme.colors.grey
        )
    }
}

@Composable
private fun TitleText(
    title: String,
    modifier: Modifier = Modifier,
    style: TextStyle = CinemaxTheme.typography.medium.h5,
    color: Color = CinemaxTheme.colors.white
) {
    Text(
        modifier = modifier,
        text = title,
        style = style,
        color = color
    )
}

@Composable
private fun ValueText(
    value: String,
    modifier: Modifier = Modifier,
    style: TextStyle = CinemaxTheme.typography.medium.h5,
    color: Color = CinemaxTheme.colors.white
) {
    Text(
        modifier = modifier,
        text = value,
        style = style,
        color = color
    )
}

@Composable
private fun ForwardButton(
    modifier: Modifier = Modifier,
    color: Color = CinemaxTheme.colors.primaryBlue
) {
    CinemaxIcon(
        modifier = modifier.size(IconShapeSize),
        icon = Res.drawable.ic_arrow_forward,
        contentDescription = stringResource(Res.string.forward),
        tint = color
    )
}

private val IconShapeSize = 32.dp
