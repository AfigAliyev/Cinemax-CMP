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

package com.cinemax.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import com.cinemax.core.designsystem.theme.CinemaxTheme

@Composable
fun CinemaxMessage(
    message: String,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    CinemaxCenteredBox(modifier = modifier.padding(horizontal = CinemaxTheme.spacing.largest)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CinemaxTheme.spacing.medium)
        ) {
            Image(
                painter = painter,
                contentDescription = message
            )
            Text(
                text = message,
                style = CinemaxTheme.typography.medium.h3,
                color = CinemaxTheme.colors.white,
                textAlign = TextAlign.Center
            )
        }
    }
}
