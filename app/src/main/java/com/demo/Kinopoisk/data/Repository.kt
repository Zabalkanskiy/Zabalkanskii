package com.demo.Kinopoisk.data

import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.oneFilm.OneFilm
import com.demo.core.model.search.Search

interface Repository {

    suspend fun getTopFilmsFromInternet(page: Int): KinoFilm

    suspend fun getFilmInfoFromInternet(idFilm: Int): OneFilm

    suspend fun findFilmInKInopoisk(keyword: String): Search

}