package com.demo.core.model.oneFilm

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("country")
    @Expose
    val country: String?
)
