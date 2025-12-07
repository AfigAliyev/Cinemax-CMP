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

package com.cinemax.core.ui.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlin.math.roundToInt

fun LocalDate.format(): String {
    return UpcomingMovieDateFormat.format(this)
}

private val UpcomingMovieDateFormat: DateTimeFormat<LocalDate> = LocalDate.Format {
    monthName(MonthNames.ENGLISH_FULL)
    char(' ')
    day()
    chars(", ")
    year()
}

internal fun Double.roundToOneDecimal() =
    (this * ONE_DECIMAL_ARGUMENT).roundToInt() / ONE_DECIMAL_ARGUMENT

private const val ONE_DECIMAL_ARGUMENT = 10.0
