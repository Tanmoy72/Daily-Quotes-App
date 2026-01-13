package com.paul.dailyquoteapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.paul.dailyquoteapp.api.RetrofitInstance
import com.paul.dailyquoteapp.api.ZenQuotesApi
import com.paul.dailyquoteapp.dao.FavoriteQuoteDao
import com.paul.dailyquoteapp.database.FavoriteDatabase
import com.paul.dailyquoteapp.model.FavoriteQuoteEntity
import com.paul.dailyquoteapp.model.ZenQuote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: ZenQuotesApi,
    private val dao: FavoriteQuoteDao
)
{

    val quoteLiveData = MutableLiveData<ZenQuote>()


    fun fetchQuotes() {
        RetrofitInstance.api.getRandomQuote().enqueue(object : Callback<List<ZenQuote>> {

            override fun onResponse(call: Call<List<ZenQuote>>, response: Response<List<ZenQuote>>) {
                if (response.isSuccessful && response.body() != null) {
                    quoteLiveData.postValue(response.body()!![0])
                }
            }

            override fun onFailure(call: Call<List<ZenQuote>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    suspend fun saveFavorite(quote: FavoriteQuoteEntity) {
        dao.insertQuote(quote)
    }

    suspend fun getFavorites(): List<FavoriteQuoteEntity> {
        return dao.getAllQuotes()
    }
}
