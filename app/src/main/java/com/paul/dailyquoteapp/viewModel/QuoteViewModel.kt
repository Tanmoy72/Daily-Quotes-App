package com.paul.dailyquoteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.dailyquoteapp.model.FavoriteQuoteEntity
import com.paul.dailyquoteapp.model.ZenQuote
import com.paul.dailyquoteapp.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel(){

    val quote = repository.quoteLiveData

    fun loadQuote() {
        repository.fetchQuotes()
    }

    val quoteLiveData = MutableLiveData<ZenQuote>()
    val favoritesLiveData = MutableLiveData<List<FavoriteQuoteEntity>>()

    fun saveFavorite(q: String, a: String) = viewModelScope.launch {
        repository.saveFavorite(FavoriteQuoteEntity(quote = q, author = a))
    }

    fun loadFavorites() = viewModelScope.launch {
        favoritesLiveData.postValue(repository.getFavorites())
    }
}


