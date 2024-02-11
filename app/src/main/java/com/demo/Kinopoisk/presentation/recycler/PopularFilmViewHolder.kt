package com.demo.Kinopoisk.presentation.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.Kinopoisk.R
import com.demo.core.KinopoiskApplication
import com.demo.core.model.listKino.Film

class PopularFilmViewHolder(itemView: View) : ViewHolder(itemView) {

    val imageFilm: ImageView = itemView.findViewById(R.id.recycler_kinopoisk_film_image)
    val nameFilm: TextView = itemView.findViewById(R.id.recycler_kinopoisk_text_view_name_film)
    val categoryFilm: TextView = itemView.findViewById(R.id.recycler_kinopoisk_text_view_category_film)
    val starFilm: ImageView = itemView.findViewById(R.id.recycler_kinopoisk_image_star)

    fun bind(model: Film){

        nameFilm.text = model.nameRu
        val genres = model.genres?.map { it.genre }?.joinToString(", ") ?: ""
        val year = model.year ?: ""
        val result = "$genres ($year)"

        categoryFilm.text =  result

        Glide.with(KinopoiskApplication.getKinopoiskApplicationContext)
            .load(model.posterUrl)
            .placeholder(R.drawable.placeholder_black)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageFilm)

        itemView.setOnLongClickListener{
            starFilm.visibility = View.VISIBLE
            true // возвращаем false, чтобы событие долгого нажатия не перекрывалось кликом
        }
    }
}