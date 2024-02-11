package com.demo.core.model.listKino

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @SerializedName("country")
    @Expose
    val country: String?
): Parcelable
