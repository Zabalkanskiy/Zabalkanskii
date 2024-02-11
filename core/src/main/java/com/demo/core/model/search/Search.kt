package com.demo.core.model.search

import com.demo.core.model.listKino.Film
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Search (
    @SerializedName("keyword")
    @Expose
    val keyword: String?,

    @SerializedName("pagesCount")
    @Expose
    var pagesCount: Int?,

    @SerializedName("films")
    @Expose
    var films: List<Film>?,

    @SerializedName("searchFilmsCountResult")
    @Expose
    var searchFilmsCountResult: Int?,
)
