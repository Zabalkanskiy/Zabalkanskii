package com.demo.Kinopoisk.data

import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.oneFilm.OneFilm

interface Repository {

    suspend fun getTopFilmsFromInternet(page: Int): KinoFilm

    suspend fun getFilmInfoFromInternet(idFilm: Int): OneFilm

}