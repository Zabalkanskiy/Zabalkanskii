package com.demo.Kinopoisk.domain

import com.demo.Kinopoisk.data.Repository
import com.demo.core.model.oneFilm.OneFilm
import javax.inject.Inject

class GetFilmInformationUseCaseImpl @Inject constructor(private val repository: Repository) : GetFilmInformationUseCase{
    override suspend fun getFilmInformation(idFilm: Int): OneFilm {
        return repository.getFilmInfoFromInternet(idFilm = idFilm)
    }
}