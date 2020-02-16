package com.anesabml.quotey.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val quote: String,
    @ColumnInfo val author: String,
//    @ColumnInfo val background: String,
//    @ColumnInfo val category: String,
//    @ColumnInfo val title: String,
    @ColumnInfo val length: Int,
    @ColumnInfo var isFavorite: Boolean = false
)
