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

package com.cinemax.core.database.converter

import androidx.room.TypeConverter
import com.cinemax.core.database.model.Credits
import com.cinemax.core.database.model.Genre
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json

internal class ListConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun intListToString(list: List<Int>): String = json.encodeToString(list)

    @TypeConverter
    fun stringToIntList(string: String): List<Int> = json.decodeFromString(string)

    @TypeConverter
    fun stringListToString(list: List<String>): String = json.encodeToString(list)

    @TypeConverter
    fun stringToStringList(string: String): List<String> = json.decodeFromString(string)

    @TypeConverter
    fun genreListToString(list: List<Genre>): String = json.encodeToString(list)

    @TypeConverter
    fun stringToGenreList(string: String): List<Genre> = json.decodeFromString(string)
}

internal class LocalDateConverter {
    @TypeConverter
    fun localDateToString(localDate: LocalDate?): String? = localDate?.toString()

    @TypeConverter
    fun stringToLocalDate(string: String?): LocalDate? = string?.let { LocalDate.parse(it) }
}

internal class CreditsConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun creditsToString(credits: Credits): String = json.encodeToString(credits)

    @TypeConverter
    fun stringToCredits(string: String): Credits = json.decodeFromString(string)
}
