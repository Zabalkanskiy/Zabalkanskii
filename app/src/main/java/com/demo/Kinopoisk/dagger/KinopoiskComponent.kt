package com.demo.Kinopoisk.dagger

import com.demo.Kinopoisk.presentation.ui.OneFilmFragment
import com.demo.Kinopoisk.presentation.ui.PopularFragment
import com.demo.core.dagger.AppComponent
import dagger.Component

@KinopooskScope
@Component(modules = [(KinopoiskFactoryModule::class), (KinopoiskBindsModule::class)],
    dependencies = [AppComponent::class])
interface KinopoiskComponent {
    fun inject(popularFragment: PopularFragment)

    fun inject(oneFilmFragment: OneFilmFragment)

    @Component.Factory
    interface Factory{
        fun create(appComponent: AppComponent) : KinopoiskComponent
    }
}