package com.demo.Kinopoisk.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.demo.core.model.listKino.Film

object FilmDiffUtil: DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.equals(newItem)
    }
}