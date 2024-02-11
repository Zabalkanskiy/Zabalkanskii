package com.demo.Kinopoisk.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.demo.Kinopoisk.R
import com.demo.core.model.listKino.Film

class PopularFilmAdapter(private val onClickAction: (film: Film, position: Int) -> Unit) : ListAdapter<Film, PopularFilmViewHolder>(FilmDiffUtil){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFilmViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.recycler_kinopoisk, parent, false)
        val viewHolder = PopularFilmViewHolder(view)

        view.setOnClickListener {
            val item = getItem(viewHolder.adapterPosition)
            val position = viewHolder.adapterPosition
            onClickAction(item, position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PopularFilmViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)


    }
}