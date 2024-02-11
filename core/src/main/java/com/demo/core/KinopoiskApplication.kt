package com.demo.core

import android.app.Application
import com.demo.core.dagger.AppComponent
import com.demo.core.dagger.DaggerAppComponent
import com.demo.core.retrofitInterface.KinopoiskInterface
import javax.inject.Inject

public class KinopoiskApplication: Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var kinopoiskApi: KinopoiskInterface

    override fun onCreate() {
        super.onCreate()
        getKinopoiskApplicationContext = this

       appComponent = DaggerAppComponent.builder()
           .build()

        appComponent.inject(this)
    }

    companion object {
        lateinit var getKinopoiskApplicationContext: KinopoiskApplication
            private set
    }
}