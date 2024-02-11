package com.demo.core.retrofitInterface

import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.oneFilm.OneFilm
import com.demo.core.model.search.Search
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

    @GET("/api/v2.1/films/search-by-keyword")
    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
    suspend fun searchFilmByQuery(@Query("keyword") keyword: String): Search

    @GET("api/v2.2/films/{filmId}")
    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
   suspend fun getFilm(@Path("filmId") filmId: Int): OneFilm
}