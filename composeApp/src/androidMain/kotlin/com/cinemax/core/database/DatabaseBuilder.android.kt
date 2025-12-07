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

package com.cinemax.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cinemax.core.database.util.Constants

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<CinemaxDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(Constants.DATABASE_NAME)
    return Room.databaseBuilder<CinemaxDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
