package com.demo.Kinopoisk.dagger

import com.demo.Kinopoisk.data.Repository
import com.demo.Kinopoisk.data.RepositoryImpl
import com.demo.Kinopoisk.domain.GetFilmInformationUseCase
import com.demo.Kinopoisk.domain.GetFilmInformationUseCaseImpl
import com.demo.Kinopoisk.domain.GetFilmsFromInternetUseCase
import com.demo.Kinopoisk.domain.GetFilmsFromInternetUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface KinopoiskBindsModule {

    @KinopooskScope
    @Binds
    fun bindGetFilmsFromInternet(useCaseImpl: GetFilmsFromInternetUseCaseImpl): GetFilmsFromInternetUseCase

    @KinopooskScope
    @Binds
    fun bindGetFilmInformation(useCaseImpl: GetFilmInformationUseCaseImpl): GetFilmInformationUseCase

    @KinopooskScope
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}