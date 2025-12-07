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

package com.cinemax.core.ui.mapper

import androidx.compose.runtime.Composable
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.no_internet_connection
import cinemax.composeapp.generated.resources.unknown_error
import com.cinemax.core.model.UserMessage
import kotlinx.io.IOException
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserMessage.asString(): String = stringResource(resource, *args.toTypedArray())

fun Throwable.asUserMessage() = UserMessage(
    resource = when (this) {
        is IOException -> Res.string.no_internet_connection
        else -> Res.string.unknown_error
    }
)
