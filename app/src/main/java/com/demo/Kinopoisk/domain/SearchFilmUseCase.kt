package com.demo.Kinopoisk.domain

import com.demo.core.model.search.Search

interface SearchFilmUseCase {
    suspend fun searchfilm(keyword:String): Search
}