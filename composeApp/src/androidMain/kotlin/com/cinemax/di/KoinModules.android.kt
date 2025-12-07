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

package com.cinemax.di

import co.touchlab.kermit.Logger
import com.cinemax.BuildConfig as AndroidBuildConfig
import com.cinemax.config.BuildConfig as GeneratedBuildConfig
import com.cinemax.core.database.getDatabaseBuilder
import com.cinemax.core.datastore.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single { Logger.withTag("Cinemax") }
    single { getDatabaseBuilder(androidContext()) }
    single { createDataStore(androidContext()) }
}

actual fun getApiKey(): String = GeneratedBuildConfig.CINEMAX_API_KEY

actual fun getAppVersion(): String = AndroidBuildConfig.VERSION_NAME
