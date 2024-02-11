package com.demo.Kinopoisk.presentation.model

import com.demo.core.model.listKino.Film
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelKinofilm(

    val pagesCount: Int,

    val isLastCount: Boolean,

    val films: List<Film>
)