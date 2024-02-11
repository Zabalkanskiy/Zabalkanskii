package com.demo.Kinopoisk.presentation.ui

import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


const val POPULARFRAGMENT = "POPULARFRAGMENT"

class PopularFragment : Fragment() {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    lateinit var popularFilmAdapter: PopularFilmAdapter

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    lateinit var popularViewModel: PopularViewModel

    private var _binding: PopularFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val searchJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + searchJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerKinopoiskComponent.factory()
            .create(KinopoiskApplication.getKinopoiskApplicationContext.appComponent).inject(this)

        popularViewModel = ViewModelProvider(this, modelFactory).get(PopularViewModel::class.java)

        popularFilmAdapter = PopularFilmAdapter { film: Film, position: Int ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_fragment_container,
                    OneFilmFragment.newInstance(film = film), ONEFILMFRAGMENT
                )
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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        requireActivity().noStatusBar(need = false)



        popularViewModel.getFilmsFromInternet()

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())




        binding.kinopoiskRecyclerView.adapter = popularFilmAdapter
        binding.kinopoiskRecyclerView.layoutManager = linearLayoutManager
        binding.kinopoiskRecyclerView.addItemDecoration(
            MarginItemDecoration(
                marginTop = 10,
                marginBottom = 16
            )
        )

        binding.kinopoiskRecyclerView.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
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
        var myJob: Job? = null
        val searchItem = binding.mainTabToolbar.menu.findItem(R.id.top_bar_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        var job: Job? = null
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }


            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {

                    if (job?.isActive == true) {
                        job?.cancel()
                    }

               // popularViewModel.clearData()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(301)
                    popularViewModel.clearData()
                    popularViewModel.getFilmsFromInternet()
                }

                return true
            }

        })





        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                popularViewModel.clearData()
                popularViewModel.getFilmsFromInternet()
                return true
            }
        })




        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var searchQuery = ""

            override fun onQueryTextSubmit(query: String): Boolean {


                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                searchQuery = newText

//

                job = CoroutineScope(Dispatchers.Main).launch {
                    flow {
                        emit(searchQuery)
                    }
                        .debounce(300)
                        .collect { query ->

                            handleSearchQuery(query)
                        }
                }

                return false
            }

        })

        popularViewModel.getListFilms().observe(viewLifecycleOwner) {
            isLoading = false
            binding.popularFragmentProgressBar.visibility = View.INVISIBLE
            binding.popularFragmantListProgressBar.visibility = View.INVISIBLE

            if (it.isLastCount) {
                isLastPage = true
            }

            popularFilmAdapter.submitList(it.films)
            binding.kinopoiskRecyclerView.visibility = View.VISIBLE

        }

        popularViewModel.getNetworkError().observe(viewLifecycleOwner) {
            parentFragmentManager.beginTransaction().replace(
                R.id.main_activity_fragment_container,
                ErrorFragment.newInstance(),
                ERRORFRAGMENT
            )
                .commit()
        }

    }

    fun handleSearchQuery(query: String) {
        popularViewModel.clearData()
        popularViewModel.findFilmFromInternet(query = query)

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

class MarginItemDecoration(private val marginTop: Int, private val marginBottom: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = marginTop
            }

            bottom = marginBottom
        }
    }
}