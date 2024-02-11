package com.demo.core.model.listKino

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
 data class Film (
     @SerializedName("filmId")
    @Expose
    val filmId: Int?,

     @SerializedName("nameRu")
    @Expose
    val nameRu: String?,

     @SerializedName("nameEn")
    @Expose
    val nameEn: String?,

     @SerializedName("year")
    @Expose
    val year: String?,

     @SerializedName("filmLength")
    @Expose
    val filmLength: String?,

     @SerializedName("countries")
    @Expose
    val countries: List<Country>?,

     @SerializedName("genres")
    @Expose
    val genres: List<Genre>?,

     @SerializedName("rating")
    @Expose
    val rating: String?,

     @SerializedName("ratingVoteCount")
    @Expose
    val ratingVoteCount: Int?,

     @SerializedName("posterUrl")
    @Expose
    val posterUrl: String?,

     @SerializedName("posterUrlPreview")
    @Expose
    val posterUrlPreview: String?,

     @SerializedName("ratingChange")
    @Expose
    val ratingChange: @RawValue Any?,

     @SerializedName("isRatingUp")
    @Expose
    val isRatingUp: @RawValue Any?,

     @SerializedName("isAfisha")
    @Expose
    val isAfisha: Int?,
): Parcelable
