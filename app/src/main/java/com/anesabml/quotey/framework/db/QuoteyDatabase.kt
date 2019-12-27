package com.anesabml.quotey.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [QuoteEntity::class],
    version = 3,
    exportSchema = false
)
abstract class QuoteyDatabase : RoomDatabase() {

  companion object {

    private const val DATABASE_NAME = "quotey.db"

    private var instance: QuoteyDatabase? = null

    private fun create(context: Context): QuoteyDatabase =
        Room.databaseBuilder(context, QuoteyDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    fun getInstance(context: Context): QuoteyDatabase =
        (instance ?: create(context)).also { instance = it }
  }


  abstract fun quoteDao(): QuoteDao

}