package com.demo.Kinopoisk.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.Kinopoisk.domain.GetFilmsFromInternetUseCase
import com.demo.Kinopoisk.domain.SearchFilmUseCase
import com.demo.Kinopoisk.presentation.model.ModelKinofilm
import com.demo.core.model.listKino.Film
import com.demo.core.model.listKino.KinoFilm
import com.demo.core.model.search.Search
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor( private val getFilmsFromInternetUseCase: GetFilmsFromInternetUseCase,
    private val searchFilmUseCase: SearchFilmUseCase) :
    ViewModel() {
    var pageCount: Int = 1

    private val _listFilms = MutableLiveData<ModelKinofilm>()

    private val _networkError = MutableLiveData<String>()

    fun getListFilms(): LiveData<ModelKinofilm> = _listFilms

    fun getNetworkError(): LiveData<String> = _networkError

    fun getFilmsFromInternet() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val responce = getFilmsFromInternetUseCase.getFilmsFromInternet(page = pageCount).let {
                mapToModleKinoFilm(it)
            }
            val oldValue: List<Film> = _listFilms.value?.films ?: emptyList()
            val updateList: List<Film> = oldValue + ( responce.films ?: emptyList())
            val newResponce = ModelKinofilm(pagesCount = responce.pagesCount, isLastCount = responce.isLastCount, films = updateList)
            _listFilms.postValue(newResponce)
        }
        pageCount +=1
    }

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _networkError.postValue("error")
    }

    fun clearData(){
        pageCount = 1
        _listFilms.value = ModelKinofilm(pagesCount = pageCount, isLastCount = true, films = emptyList<Film>())
    }

    fun findFilmFromInternet(query:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
            val responce = searchFilmUseCase.searchfilm(keyword = query).let {
                mapFromSearchToModelKinoFilm(it)
            }
            _listFilms.postValue(responce)

        }

    }

    fun mapToModleKinoFilm(kinoFilm: KinoFilm): ModelKinofilm {
        var isLastPage: Boolean = false
        if (kinoFilm.pagesCount == pageCount){
            isLastPage = true
        }

        return ModelKinofilm(pagesCount = kinoFilm.pagesCount ?:0, isLastCount = isLastPage, films = kinoFilm.films ?: emptyList() )
    }

    fun mapFromSearchToModelKinoFilm(search: Search): ModelKinofilm{
        var isLastPage: Boolean = true

        return ModelKinofilm(pagesCount = 1, isLastCount = true, search.films?: emptyList())
    }


}