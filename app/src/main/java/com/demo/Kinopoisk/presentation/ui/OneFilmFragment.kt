package com.demo.Kinopoisk.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.Kinopoisk.R
import com.demo.Kinopoisk.dagger.DaggerKinopoiskComponent
import com.demo.Kinopoisk.databinding.FilmDescriptionBinding
import com.demo.Kinopoisk.databinding.FilmDescriptionContentBinding
import com.demo.Kinopoisk.presentation.viewModel.OneFilmViewModel
import com.demo.core.KinopoiskApplication
import com.demo.core.model.listKino.Film
import com.demo.core.noStatusBar
import javax.inject.Inject

const val FILM = "FILM"

const val ONEFILMFRAGMENT = "ONEFILMFRAGMENT"

class OneFilmFragment: Fragment() {

    lateinit var currentFilm: Film

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    lateinit var oneFilmViewModel: OneFilmViewModel

    private var _binding: FilmDescriptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var descriptionContentBinding: FilmDescriptionContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentFilm = BundleCompat.getParcelable(it, FILM, Film::class.java) as Film
        }

        DaggerKinopoiskComponent.factory()
            .create(KinopoiskApplication.getKinopoiskApplicationContext.appComponent).inject(this)

        oneFilmViewModel = ViewModelProvider(this,  modelFactory).get(OneFilmViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilmDescriptionBinding.inflate(inflater, container, false)
        val view = binding.root
        descriptionContentBinding =  FilmDescriptionContentBinding.bind(binding.root.findViewById(R.id.film_description_nested_scrool_view))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().noStatusBar(need = true)
        binding.filmDescriptionProgressBar.visibility = View.VISIBLE

        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        oneFilmViewModel.getFilmInformation(filmId = currentFilm.filmId as Int)

        oneFilmViewModel.getInfoFilm().observe(viewLifecycleOwner){
            val genres = it.genres?.map { it.genre }?.joinToString(", ") ?: ""
            val countries = it.countries?.map { it.country }?.joinToString(", ") ?: ""

            binding.filmDescriptionProgressBar.visibility = View.INVISIBLE

            binding.appBar.visibility = View.VISIBLE
            descriptionContentBinding.filmDescriptionNestedScroolView.visibility = View.VISIBLE
            descriptionContentBinding.filmDescriptionContentNameFilmTextView.text = it.nameRu
            descriptionContentBinding.filmDescriptionContentDescriptionFilmTextView.text = it.description

            descriptionContentBinding.filmDescriptionContentGanreFilmLoadingTextView.text = genres
            descriptionContentBinding.filmDescriptionContentGanreCountryLoadingTextView.text = countries

            Glide.with(this)
                .load(it.posterUrl)
                .placeholder(R.drawable.placeholder_black)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into( binding.imageView)

        }

        oneFilmViewModel.getNetworkError().observe(viewLifecycleOwner){
            parentFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, ErrorFragment.newInstance(), ERRORFRAGMENT)
                .commit()
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    
    companion object {
        fun newInstance(film: Film): OneFilmFragment {

           return OneFilmFragment().apply {
               arguments = Bundle().apply {
                   putParcelable(FILM, film)
               }
           }
        }
    }
}