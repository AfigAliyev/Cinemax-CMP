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

package com.cinemax.feature.settings

import androidx.lifecycle.ViewModel
import cinemax.composeapp.generated.resources.Res
import cinemax.composeapp.generated.resources.about
import cinemax.composeapp.generated.resources.ic_github
import cinemax.composeapp.generated.resources.ic_info
import cinemax.composeapp.generated.resources.ic_shield
import cinemax.composeapp.generated.resources.privacy_policy
import cinemax.composeapp.generated.resources.source_code_github
import cinemax.composeapp.generated.resources.version
import com.cinemax.core.domain.usecase.GetSettingsPrivacyPolicyUrlUseCase
import com.cinemax.core.domain.usecase.GetSettingsRepoUrlUseCase
import com.cinemax.core.domain.usecase.GetSettingsVersionUseCase
import com.cinemax.core.ui.common.EventHandler
import com.cinemax.feature.settings.model.Settings
import com.cinemax.feature.settings.model.SettingsGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(
    private val getSettingsRepoUrlUseCase: GetSettingsRepoUrlUseCase,
    private val getSettingsPrivacyPolicyUrlUseCase: GetSettingsPrivacyPolicyUrlUseCase,
    private val getSettingsVersionUseCase: GetSettingsVersionUseCase
) : ViewModel(), EventHandler<SettingsEvent> {
    private val _uiState = MutableStateFlow(getInitialUiState())
    val uiState = _uiState.asStateFlow()

    override fun onEvent(event: SettingsEvent) = Unit

    private fun getInitialUiState(): SettingsUiState {
        val repoUrl = getSettingsRepoUrlUseCase()
        val privacyPolicyUrl = getSettingsPrivacyPolicyUrlUseCase()
        val version = getSettingsVersionUseCase()

        val aboutSettings = listOf(
            Settings.UrlAction(
                icon = Res.drawable.ic_github,
                title = Res.string.source_code_github,
                url = repoUrl
            ),
            Settings.UrlAction(
                icon = Res.drawable.ic_shield,
                title = Res.string.privacy_policy,
                url = privacyPolicyUrl
            ),
            Settings.Info(
                icon = Res.drawable.ic_info,
                title = Res.string.version,
                value = version
            )
        )

        val aboutSettingsGroup = SettingsGroup(
            title = Res.string.about,
            settings = aboutSettings
        )

        val settingsGroups = listOf(aboutSettingsGroup)
        return SettingsUiState(settingsGroups = settingsGroups)
    }
}
