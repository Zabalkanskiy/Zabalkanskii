package com.demo.Kinopoisk.domain

import com.demo.Kinopoisk.data.Repository
import com.demo.core.model.search.Search
import javax.inject.Inject

class SearchFilmUseCaseImpl @Inject constructor(private val repository: Repository): SearchFilmUseCase {
    override suspend fun searchfilm(keyword: String): Search {
      return  repository.findFilmInKInopoisk(keyword = keyword)
    }

}