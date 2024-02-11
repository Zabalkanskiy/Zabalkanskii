package com.demo.Kinopoisk.presentation.ui

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.Kinopoisk.R
import com.demo.Kinopoisk.dagger.DaggerKinopoiskComponent
import com.demo.Kinopoisk.databinding.PopularFragmentLayoutBinding
import com.demo.Kinopoisk.presentation.recycler.PaginationScrollListener
import com.demo.Kinopoisk.presentation.recycler.PopularFilmAdapter
import com.demo.Kinopoisk.presentation.viewModel.PopularViewModel
import com.demo.core.KinopoiskApplication
import com.demo.core.hasNetwork
import com.demo.core.model.listKino.Film
import com.demo.core.noStatusBar
import javax.inject.Inject


const val POPULARFRAGMENT = "POPULARFRAGMENT"

class PopularFragment: Fragment() {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    lateinit var popularFilmAdapter: PopularFilmAdapter

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    lateinit var popularViewModel: PopularViewModel

    private var _binding: PopularFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerKinopoiskComponent.factory()
            .create(KinopoiskApplication.getKinopoiskApplicationContext.appComponent).inject(this)

        popularViewModel = ViewModelProvider(this, modelFactory).get(PopularViewModel::class.java)

        popularFilmAdapter = PopularFilmAdapter{ film: Film, position: Int ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_activity_fragment_container,
                    OneFilmFragment.newInstance(film = film), ONEFILMFRAGMENT)
                .addToBackStack(ONEFILMFRAGMENT)
                .commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PopularFragmentLayoutBinding.inflate(inflater, container, false)
        val view =  binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            requireActivity().noStatusBar(need = false)


      //  (activity as AppCompatActivity).setSupportActionBar(binding.mainTabToolbar)

       // binding.mainTabToolbar.title = "Популярные"
        popularViewModel.getFilmsFromInternet()

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())




        binding.kinopoiskRecyclerView.adapter = popularFilmAdapter
        binding.kinopoiskRecyclerView.layoutManager = linearLayoutManager
        binding.kinopoiskRecyclerView.addItemDecoration(MarginItemDecoration(marginTop = 10, marginBottom = 16))

        binding.kinopoiskRecyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                binding.popularFragmantListProgressBar.visibility = View.VISIBLE
                isLoading = true
                val isOnline = hasNetwork(requireContext())
                //need change
                popularViewModel.getFilmsFromInternet()


            }
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        popularViewModel.getListFilms().observe(viewLifecycleOwner){
            isLoading = false
            binding.popularFragmentProgressBar.visibility = View.INVISIBLE
            binding.popularFragmantListProgressBar.visibility = View.INVISIBLE

            if (it.isLastCount){
                isLastPage  = true
            }

            popularFilmAdapter.submitList(it.films)
            binding.kinopoiskRecyclerView.visibility = View.VISIBLE

        }

        popularViewModel.getNetworkError().observe(viewLifecycleOwner){
            parentFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, ErrorFragment.newInstance(), ERRORFRAGMENT)
                .commit()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }
}

class MarginItemDecoration(private val marginTop: Int, private val marginBottom: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = marginTop
            }
           // left = marginTop
           // right = marginTop
            bottom = marginBottom
        }
    }
}