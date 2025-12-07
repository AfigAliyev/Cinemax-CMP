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

import com.cinemax.core.database.CinemaxDatabase
import com.cinemax.core.database.getRoomDatabase
import com.cinemax.core.datastore.UserPreferencesDataSource
import com.cinemax.core.datastore.UserPreferencesDataSourceImpl
import com.cinemax.core.network.api.HttpClientFactory
import com.cinemax.core.network.api.MovieApiService
import com.cinemax.core.network.api.MovieApiServiceImpl
import com.cinemax.core.network.api.TvShowApiService
import com.cinemax.core.network.api.TvShowApiServiceImpl
import com.cinemax.core.domain.usecase.GetMoviesUseCase
import com.cinemax.core.domain.usecase.GetSettingsPrivacyPolicyUrlUseCase
import com.cinemax.core.domain.usecase.GetSettingsRepoUrlUseCase
import com.cinemax.core.domain.usecase.GetSettingsVersionUseCase
import com.cinemax.core.domain.usecase.GetTvShowsUseCase
import com.cinemax.core.domain.usecase.GetWishlistMoviesUseCase
import com.cinemax.core.domain.usecase.GetWishlistTvShowsUseCase
import com.cinemax.core.domain.usecase.SearchMoviesUseCase
import com.cinemax.core.domain.usecase.SearchTvShowsUseCase
import com.cinemax.core.domain.usecase.GetMoviesPagingUseCase
import com.cinemax.core.domain.usecase.GetTvShowsPagingUseCase
import com.cinemax.core.domain.usecase.GetMovieDetailsUseCase
import com.cinemax.core.domain.usecase.GetTvShowDetailsUseCase
import com.cinemax.core.domain.usecase.AddMovieToWishlistUseCase
import com.cinemax.core.domain.usecase.AddTvShowToWishlistUseCase
import com.cinemax.core.domain.usecase.RemoveMovieFromWishlistUseCase
import com.cinemax.core.domain.usecase.RemoveTvShowFromWishlistUseCase
import com.cinemax.core.network.source.MovieDetailsNetworkDataSource
import com.cinemax.core.network.source.MovieNetworkDataSource
import com.cinemax.core.network.source.TvShowDetailsNetworkDataSource
import com.cinemax.core.network.source.TvShowNetworkDataSource
import com.cinemax.core.database.source.MovieDatabaseDataSource
import com.cinemax.core.database.source.TvShowDatabaseDataSource
import com.cinemax.core.database.source.MovieDetailsDatabaseDataSource
import com.cinemax.core.database.source.TvShowDetailsDatabaseDataSource
import com.cinemax.core.database.source.WishlistDatabaseDataSource
import com.cinemax.core.database.source.SettingsDatabaseDataSource
import com.cinemax.core.database.util.CinemaxDatabaseTransactionProvider
import com.cinemax.core.database.util.CinemaxVersionProvider
import com.cinemax.core.database.util.DefaultCinemaxVersionProvider
import com.cinemax.core.data.repository.MovieRepositoryImpl
import com.cinemax.core.data.repository.TvShowRepositoryImpl
import com.cinemax.core.data.repository.MovieDetailsRepositoryImpl
import com.cinemax.core.data.repository.TvShowDetailsRepositoryImpl
import com.cinemax.core.data.repository.WishlistRepositoryImpl
import com.cinemax.core.data.repository.SettingsRepositoryImpl
import com.cinemax.core.domain.repository.MovieRepository
import com.cinemax.core.domain.repository.TvShowRepository
import com.cinemax.core.domain.repository.MovieDetailsRepository
import com.cinemax.core.domain.repository.TvShowDetailsRepository
import com.cinemax.core.domain.repository.WishlistRepository
import com.cinemax.core.domain.repository.SettingsRepository
import com.cinemax.feature.home.HomeViewModel
import com.cinemax.feature.settings.SettingsViewModel
import com.cinemax.feature.list.ListViewModel
import com.cinemax.feature.search.SearchViewModel
import com.cinemax.feature.details.DetailsViewModel
import com.cinemax.feature.wishlist.WishlistViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(apiKey = getApiKey()) }
    singleOf(::MovieApiServiceImpl) bind MovieApiService::class
    singleOf(::TvShowApiServiceImpl) bind TvShowApiService::class
    singleOf(::MovieNetworkDataSource)
    singleOf(::TvShowNetworkDataSource)
    singleOf(::MovieDetailsNetworkDataSource)
    singleOf(::TvShowDetailsNetworkDataSource)
}

val databaseModule = module {
    single<CinemaxDatabase> { getRoomDatabase(get()) }
    single { get<CinemaxDatabase>().movieDao() }
    single { get<CinemaxDatabase>().tvShowDao() }
    single { get<CinemaxDatabase>().movieDetailsDao() }
    single { get<CinemaxDatabase>().tvShowDetailsDao() }
    single { get<CinemaxDatabase>().wishlistDao() }
    single { get<CinemaxDatabase>().movieRemoteKeyDao() }
    single { get<CinemaxDatabase>().tvShowRemoteKeyDao() }
    singleOf(::CinemaxDatabaseTransactionProvider)
    single<CinemaxVersionProvider> { DefaultCinemaxVersionProvider(getAppVersion()) }
    singleOf(::MovieDatabaseDataSource)
    singleOf(::TvShowDatabaseDataSource)
    singleOf(::MovieDetailsDatabaseDataSource)
    singleOf(::TvShowDetailsDatabaseDataSource)
    singleOf(::WishlistDatabaseDataSource)
    singleOf(::SettingsDatabaseDataSource)
}

val datastoreModule = module {
    singleOf(::UserPreferencesDataSourceImpl) bind UserPreferencesDataSource::class
}

val repositoryModule = module {
    singleOf(::MovieRepositoryImpl) bind MovieRepository::class
    singleOf(::TvShowRepositoryImpl) bind TvShowRepository::class
    singleOf(::MovieDetailsRepositoryImpl) bind MovieDetailsRepository::class
    singleOf(::TvShowDetailsRepositoryImpl) bind TvShowDetailsRepository::class
    singleOf(::WishlistRepositoryImpl) bind WishlistRepository::class
    singleOf(::SettingsRepositoryImpl) bind SettingsRepository::class
}

val useCaseModule = module {
    singleOf(::GetMoviesUseCase)
    singleOf(::GetTvShowsUseCase)
    singleOf(::GetSettingsVersionUseCase)
    singleOf(::GetSettingsRepoUrlUseCase)
    singleOf(::GetSettingsPrivacyPolicyUrlUseCase)
    singleOf(::GetWishlistMoviesUseCase)
    singleOf(::GetWishlistTvShowsUseCase)
    singleOf(::SearchMoviesUseCase)
    singleOf(::SearchTvShowsUseCase)
    singleOf(::GetMoviesPagingUseCase)
    singleOf(::GetTvShowsPagingUseCase)
    singleOf(::GetMovieDetailsUseCase)
    singleOf(::GetTvShowDetailsUseCase)
    singleOf(::AddMovieToWishlistUseCase)
    singleOf(::AddTvShowToWishlistUseCase)
    singleOf(::RemoveMovieFromWishlistUseCase)
    singleOf(::RemoveTvShowFromWishlistUseCase)
}

val presentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::WishlistViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailsViewModel)
}

expect val platformModule: Module

expect fun getApiKey(): String

expect fun getAppVersion(): String

val sharedModule = module {
    includes(
        platformModule,
        networkModule,
        databaseModule,
        datastoreModule,
        repositoryModule,
        useCaseModule,
        presentationModule
    )
}
