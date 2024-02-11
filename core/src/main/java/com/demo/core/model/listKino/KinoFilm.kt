package com.demo.core.model.listKino

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KinoFilm(
    @SerializedName("pagesCount")
    @Expose
    var pagesCount: Int?,

    @SerializedName("films")
    @Expose
    var films: List<Film>?
)
