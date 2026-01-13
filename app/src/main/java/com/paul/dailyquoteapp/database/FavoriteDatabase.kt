package com.paul.dailyquoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paul.dailyquoteapp.dao.FavoriteQuoteDao
import com.paul.dailyquoteapp.model.FavoriteQuoteEntity

@Database(entities = [FavoriteQuoteEntity::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase(){

    abstract fun quoteDao(): FavoriteQuoteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}