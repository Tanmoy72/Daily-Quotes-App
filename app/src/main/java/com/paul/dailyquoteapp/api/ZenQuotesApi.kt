package com.paul.dailyquoteapp.api

import com.paul.dailyquoteapp.model.ZenQuote
import retrofit2.Call
import retrofit2.http.GET

interface ZenQuotesApi {

    @GET("quotes")
    fun getRandomQuote(): Call<List<ZenQuote>>

}