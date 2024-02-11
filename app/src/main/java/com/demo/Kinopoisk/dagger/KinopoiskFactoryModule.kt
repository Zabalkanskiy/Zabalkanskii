package com.demo.Kinopoisk.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.Kinopoisk.presentation.viewModel.OneFilmViewModel
import com.demo.Kinopoisk.presentation.viewModel.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface KinopoiskFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneFilmViewModel::class)
    fun bindOneFilmViewModel(oneFilmViewModel: OneFilmViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: KinopoiskViewModelFactory) : ViewModelProvider.Factory
}