package com.demo.core.retrofitInterface

import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.oneFilm.OneFilm
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val TOP_100_POPULAR_FILMS = "TOP_100_POPULAR_FILMS"
interface KinopoiskInterface {


    @GET("api/v2.2/films/top")
    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
   suspend fun getTopFilm(@Query("type") type: String, @Query("page") page: Int): KinoFilm

    @GET("api/v2.2/films/{filmId}")
    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
   suspend fun getFilm(@Path("filmId") filmId: Int): OneFilm
}