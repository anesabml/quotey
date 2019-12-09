package com.anesabml.quotey.framework.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface QuoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun addQuote(quote: QuoteEntity)

    @Update(onConflict = REPLACE)
    suspend fun updateQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes WHERE isFavorite = 1")
    suspend fun getFavorites(): List<QuoteEntity>

    @Query("SELECT * FROM quotes")
    suspend fun getAll(): List<QuoteEntity>

    @Delete
    suspend fun removeQuote(quoteEntity: QuoteEntity)

}
