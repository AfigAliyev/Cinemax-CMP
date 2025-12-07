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

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.cinemax.core.designsystem.theme.CinemaxTheme
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun CinemaxTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    placeholderContent: @Composable (() -> Unit) = {
        Text(text = placeholder)
    },
    leadingIcon: @Composable (() -> Unit) = {
        CinemaxIcon(
            icon = icon,
            contentDescription = placeholder
        )
    },
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = true,
    shape: Shape = CinemaxTheme.shapes.extraMedium,
    colors: TextFieldColors = CinemaxTextFieldDefaults.colors()
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholderContent,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        shape = shape,
        colors = colors
    )
}

object CinemaxTextFieldDefaults {
    @Composable
    fun colors(
        focusedTextColor: Color = CinemaxTheme.colors.white,
        unfocusedTextColor: Color = CinemaxTheme.colors.white,
        cursorColor: Color = CinemaxTheme.colors.primaryBlue,
        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = CinemaxTheme.colors.primaryBlue,
            backgroundColor = CinemaxTheme.colors.primaryBlue.copy(
                alpha = TextSelectionColorsBackgroundColorAlpha
            )
        ),
        focusedContainerColor: Color = CinemaxTheme.colors.primarySoft,
        unfocusedContainerColor: Color = CinemaxTheme.colors.primarySoft,
        focusedLeadingIconColor: Color = CinemaxTheme.colors.grey,
        unfocusedLeadingIconColor: Color = CinemaxTheme.colors.grey,
        focusedTrailingIconColor: Color = CinemaxTheme.colors.grey,
        unfocusedTrailingIconColor: Color = CinemaxTheme.colors.grey,
        focusedPlaceholderColor: Color = CinemaxTheme.colors.grey,
        unfocusedPlaceholderColor: Color = CinemaxTheme.colors.grey,
        focusedIndicatorColor: Color = Color.Transparent,
        unfocusedIndicatorColor: Color = Color.Transparent
    ): TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = unfocusedTextColor,
        cursorColor = cursorColor,
        selectionColors = selectionColors,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        focusedLeadingIconColor = focusedLeadingIconColor,
        unfocusedLeadingIconColor = unfocusedLeadingIconColor,
        focusedTrailingIconColor = focusedTrailingIconColor,
        unfocusedTrailingIconColor = unfocusedTrailingIconColor,
        focusedPlaceholderColor = focusedPlaceholderColor,
        unfocusedPlaceholderColor = unfocusedPlaceholderColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor
    )
}

private const val TextSelectionColorsBackgroundColorAlpha = 0.4f
