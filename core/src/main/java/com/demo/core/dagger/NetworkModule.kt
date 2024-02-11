package com.demo.core.dagger

import androidx.lifecycle.livedata.ktx.R
import com.demo.core.retrofitInterface.KinopoiskInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASEURL : String = "https://kinopoiskapiunofficial.tech/"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiRetrofit(): KinopoiskInterface = provideRetrofit().create(KinopoiskInterface::class.java)
}