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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.offline_mode
import cinemax.composeapp.generated.resources.retry
import com.cinemax.core.designsystem.component.CinemaxCenteredBox
import com.cinemax.core.designsystem.component.CinemaxOutlinedButton
import com.cinemax.core.designsystem.theme.CinemaxTheme
import com.cinemax.core.model.UserMessage
import com.cinemax.core.ui.mapper.asString
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CinemaxError(
    errorMessage: UserMessage,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CinemaxTheme.shapes.medium,
    containerColor: Color = CinemaxTheme.colors.primarySoft,
    errorTextColor: Color = CinemaxTheme.colors.secondaryRed,
    actionButtonColor: Color = CinemaxTheme.colors.primaryBlue,
    errorTextStyle: TextStyle = CinemaxTheme.typography.regular.h4,
    actionButtonTextResource: StringResource = Res.string.retry,
    shouldShowOfflineMode: Boolean = false,
    onOfflineModeClick: () -> Unit = {},
    offlineModeButtonColor: Color = CinemaxTheme.colors.secondaryGreen,
    offlineModeButtonTextResource: StringResource = Res.string.offline_mode
) {
    Column(
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.small)
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Column(
                modifier = Modifier.padding(CinemaxTheme.spacing.extraMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.small)
            ) {
                Text(
                    text = errorMessage.asString(),
                    style = errorTextStyle,
                    color = errorTextColor
                )
                CinemaxOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(actionButtonTextResource),
                    onClick = onRetry,
                    containerColor = CinemaxTheme.colors.primarySoft,
                    contentColor = actionButtonColor
                )
            }
        }
        if (shouldShowOfflineMode) {
            CinemaxOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(offlineModeButtonTextResource),
                onClick = onOfflineModeClick,
                containerColor = CinemaxTheme.colors.primaryDark,
                contentColor = offlineModeButtonColor
            )
        }
    }
}

@Composable
fun CinemaxCenteredError(
    errorMessage: UserMessage,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CinemaxTheme.shapes.medium,
    containerColor: Color = CinemaxTheme.colors.primarySoft,
    errorTextColor: Color = CinemaxTheme.colors.secondaryRed,
    actionButtonColor: Color = CinemaxTheme.colors.primaryBlue,
    actionButtonTextResource: StringResource = Res.string.retry,
    shouldShowOfflineMode: Boolean = false,
    onOfflineModeClick: () -> Unit = {},
    offlineModeButtonColor: Color = CinemaxTheme.colors.secondaryGreen,
    offlineModeButtonTextResource: StringResource = Res.string.offline_mode
) {
    CinemaxCenteredBox(
        modifier = modifier
            .padding(horizontal = CinemaxTheme.spacing.extraMedium)
            .fillMaxSize()
    ) {
        CinemaxError(
            errorMessage = errorMessage,
            onRetry = onRetry,
            shape = shape,
            containerColor = containerColor,
            errorTextColor = errorTextColor,
            actionButtonColor = actionButtonColor,
            actionButtonTextResource = actionButtonTextResource,
            shouldShowOfflineMode = shouldShowOfflineMode,
            onOfflineModeClick = onOfflineModeClick,
            offlineModeButtonColor = offlineModeButtonColor,
            offlineModeButtonTextResource = offlineModeButtonTextResource
        )
    }
}
