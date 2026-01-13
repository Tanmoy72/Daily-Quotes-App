package com.paul.dailyquoteapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paul.dailyquoteapp.model.FavoriteQuoteEntity

@Dao
interface FavoriteQuoteDao {
    @Insert
    suspend fun insertQuote(quote: FavoriteQuoteEntity)

    @Query("SELECT * FROM favorite_quotes")
    suspend fun getAllQuotes(): List<FavoriteQuoteEntity>
}