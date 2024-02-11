package com.demo.Kinopoisk.domain

import com.demo.core.model.oneFilm.OneFilm

interface GetFilmInformationUseCase {

    suspend fun getFilmInformation(idFilm: Int): OneFilm
}