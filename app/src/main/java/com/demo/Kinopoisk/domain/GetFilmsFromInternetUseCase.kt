package com.demo.Kinopoisk.domain

import com.demo.core.model.listKino.KinoFilm

interface GetFilmsFromInternetUseCase {

    suspend fun getFilmsFromInternet(page : Int) : KinoFilm
}