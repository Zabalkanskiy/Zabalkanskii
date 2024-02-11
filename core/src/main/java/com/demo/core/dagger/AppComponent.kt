package com.demo.core.dagger

import com.demo.core.KinopoiskApplication
import com.demo.core.retrofitInterface.KinopoiskInterface
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun getApiInterface(): KinopoiskInterface

    fun inject(kinopoiskApplication: KinopoiskApplication)
}