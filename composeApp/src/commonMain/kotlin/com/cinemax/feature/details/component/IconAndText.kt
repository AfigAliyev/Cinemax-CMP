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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cinemax.core.designsystem.component.cinemaxPlaceholder
import com.cinemax.core.designsystem.theme.CinemaxTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun IconAndText(
    iconResource: DrawableResource,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = CinemaxTheme.colors.grey,
    isPlaceholder: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.extraSmall)
    ) {
        Icon(
            modifier = Modifier.size(IconAndTextIconSize),
            painter = painterResource(iconResource),
            contentDescription = text,
            tint = color
        )
        Text(
            modifier = if (isPlaceholder) {
                Modifier
                    .fillMaxWidth(PlaceholderTextMaxWidthFraction)
                    .height(PlaceholderTextHeight)
                    .cinemaxPlaceholder(color = color)
            } else {
                Modifier
            },
            text = text,
            style = CinemaxTheme.typography.medium.h5,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun IconAndTextPlaceholder(
    iconResource: DrawableResource,
    modifier: Modifier = Modifier
) {
    IconAndText(
        modifier = modifier,
        iconResource = iconResource,
        text = PlaceholderText,
        isPlaceholder = true
    )
}

private val IconAndTextIconSize = 18.dp
private val PlaceholderTextHeight = IconAndTextIconSize / 1.5f
private const val PlaceholderText = ""
private const val PlaceholderTextMaxWidthFraction = 0.5f
