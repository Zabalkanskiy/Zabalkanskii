package com.demo.Kinopoisk.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.Kinopoisk.domain.GetFilmInformationUseCase
import com.demo.core.model.oneFilm.OneFilm
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OneFilmViewModel @Inject constructor(private val getFilmInformationUseCase: GetFilmInformationUseCase) :
    ViewModel() {

    private val _infoFilm = MutableLiveData<OneFilm>()

    private val _networkError = MutableLiveData<String>()

    fun getInfoFilm(): LiveData<OneFilm> = _infoFilm

    fun getNetworkError(): LiveData<String> = _networkError

    fun getFilmInformation(filmId: Int) {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
          val responce =  getFilmInformationUseCase.getFilmInformation(idFilm = filmId)
            _infoFilm.postValue(responce)
        }
    }

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _networkError.postValue("error")
    }
}