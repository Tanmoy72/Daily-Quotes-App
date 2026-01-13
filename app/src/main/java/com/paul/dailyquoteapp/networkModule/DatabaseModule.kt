package com.paul.dailyquoteapp.networkModule

import android.content.Context
import androidx.room.Room
import com.paul.dailyquoteapp.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoriteDatabase =
        Room.databaseBuilder(context, FavoriteDatabase::class.java, "favorite_db").build()

    @Provides
    fun provideQuoteDao(db: FavoriteDatabase) = db.quoteDao()
}
