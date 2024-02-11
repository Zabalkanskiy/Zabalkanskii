package com.demo.Kinopoisk.domain

import com.demo.Kinopoisk.data.Repository
import com.demo.core.model.listKino.KinoFilm
import javax.inject.Inject

class GetFilmsFromInternetUseCaseImpl @Inject constructor(private val repository: Repository) : GetFilmsFromInternetUseCase {
    override suspend fun getFilmsFromInternet(page: Int): KinoFilm {

       return repository.getTopFilmsFromInternet(page = page)

    }
}