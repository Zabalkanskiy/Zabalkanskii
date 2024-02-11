package com.demo.core.model.oneFilm

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OneFilm (
    @SerializedName("kinopoiskId")
    @Expose
    val kinopoiskId: Int? ,

    @SerializedName("kinopoiskHDId")
    @Expose
    val kinopoiskHDId: Any?,

    @SerializedName("imdbId")
    @Expose
    val imdbId: String?,

    @SerializedName("nameRu")
    @Expose
    val nameRu: String?,

    @SerializedName("nameEn")
    @Expose
    val nameEn: Any?,

    @SerializedName("nameOriginal")
    @Expose
    val nameOriginal: String?,

    @SerializedName("posterUrl")
    @Expose
    val posterUrl: String?,

    @SerializedName("posterUrlPreview")
    @Expose
    val posterUrlPreview: String?,

    @SerializedName("coverUrl")
    @Expose
    val coverUrl: Any?,

    @SerializedName("logoUrl")
    @Expose
    val logoUrl: Any?,

    @SerializedName("reviewsCount")
    @Expose
    val reviewsCount: Int?,

    @SerializedName("ratingGoodReview")
    @Expose
    val ratingGoodReview: Any?,

    @SerializedName("ratingGoodReviewVoteCount")
    @Expose
    val ratingGoodReviewVoteCount: Int?,

    @SerializedName("ratingKinopoisk")
    @Expose
    val ratingKinopoisk: Double?,

    @SerializedName("ratingKinopoiskVoteCount")
    @Expose
    val ratingKinopoiskVoteCount: Int?,

    @SerializedName("ratingImdb")
    @Expose
    val ratingImdb: Double?,

    @SerializedName("ratingImdbVoteCount")
    @Expose
    val ratingImdbVoteCount: Int?,

    @SerializedName("ratingFilmCritics")
    @Expose
    val ratingFilmCritics: Double?,

    @SerializedName("ratingFilmCriticsVoteCount")
    @Expose
    val ratingFilmCriticsVoteCount: Int?,

    @SerializedName("ratingAwait")
    @Expose
    val ratingAwait: Double?,

    @SerializedName("ratingAwaitCount")
    @Expose
    val ratingAwaitCount: Int?,

    @SerializedName("ratingRfCritics")
    @Expose
    val ratingRfCritics: Any?,

    @SerializedName("ratingRfCriticsVoteCount")
    @Expose
    val ratingRfCriticsVoteCount: Int?,

    @SerializedName("webUrl")
    @Expose
    val webUrl: String?,

    @SerializedName("year")
    @Expose
    val year: Int?,

    @SerializedName("filmLength")
    @Expose
    val filmLength: Int?,

    @SerializedName("slogan")
    @Expose
    val slogan: Any?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("shortDescription")
    @Expose
    val shortDescription: Any?,

    @SerializedName("editorAnnotation")
    @Expose
    val editorAnnotation: Any?,

    @SerializedName("isTicketsAvailable")
    @Expose
    val isTicketsAvailable: Boolean?,

    @SerializedName("productionStatus")
    @Expose
    val productionStatus: Any?,

    @SerializedName("type")
    @Expose
    val type: String?,

    @SerializedName("ratingMpaa")
    @Expose
    val ratingMpaa: String?,

    @SerializedName("ratingAgeLimits")
    @Expose
    val ratingAgeLimits: Any?,

    @SerializedName("countries")
    @Expose
    val countries: List<Country>?,

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>?,

    @SerializedName("startYear")
    @Expose
    val startYear: Any?,

    @SerializedName("endYear")
    @Expose
    val endYear: Any?,

    @SerializedName("serial")
    @Expose
    val serial: Boolean?,

    @SerializedName("shortFilm")
    @Expose
    val shortFilm: Boolean? ,

    @SerializedName("completed")
    @Expose
    val completed: Boolean?,

    @SerializedName("hasImax")
    @Expose
    val hasImax: Boolean?,

    @SerializedName("has3D")
    @Expose
    val has3D: Boolean?,

    @SerializedName("lastSync")
    @Expose
    val lastSync: String?,
)
