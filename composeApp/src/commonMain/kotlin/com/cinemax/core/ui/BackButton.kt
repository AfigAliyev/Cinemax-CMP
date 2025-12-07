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

package com.cinemax.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.back
import cinemax.composeapp.generated.resources.ic_arrow_back
import com.cinemax.core.designsystem.component.CinemaxIconButton
import com.cinemax.core.designsystem.theme.CinemaxTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CinemaxBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = CinemaxTheme.colors.white
) {
    CinemaxIconButton(
        modifier = modifier
            .size(CinemaxBackButtonShapeSize)
            .background(
                color = CinemaxTheme.colors.primarySoft,
                shape = CinemaxTheme.shapes.smallMedium
            ),
        painter = painterResource(Res.drawable.ic_arrow_back),
        contentDescription = stringResource(Res.string.back),
        onClick = onClick,
        tint = tint
    )
}

private val CinemaxBackButtonShapeSize = 32.dp
