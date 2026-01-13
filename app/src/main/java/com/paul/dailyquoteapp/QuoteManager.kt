package com.paul.dailyquoteapp

import com.paul.dailyquoteapp.model.QuoteModel

object QuoteManager {
    private val quotes = listOf(
        QuoteModel("Believe in yourself", "Unknown"),
        QuoteModel("Dream big", "Norman Vaughan"),
        QuoteModel("Never give up", "Winston Churchill"),
        QuoteModel("Stay hungry, stay foolish", "Steve Jobs")
    )

    fun randomQuote() = quotes.random()
}