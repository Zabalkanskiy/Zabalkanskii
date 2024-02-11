package com.demo.Kinopoisk.data

import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.oneFilm.OneFilm
import com.demo.core.retrofitInterface.KinopoiskInterface
import com.demo.core.retrofitInterface.TOP_100_POPULAR_FILMS
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val kinopoiskInterface: KinopoiskInterface) : Repository {
    override suspend fun getTopFilmsFromInternet(page: Int): KinoFilm {
       return kinopoiskInterface.getTopFilm(type = TOP_100_POPULAR_FILMS, page = page)
    }

    override suspend fun getFilmInfoFromInternet(idFilm: Int): OneFilm {
        return kinopoiskInterface.getFilm(filmId = idFilm)
    }
}