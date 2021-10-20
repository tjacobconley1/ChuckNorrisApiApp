package com.example.chucknorris.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// this class represents an entity within
// the roomdb
@Entity(tableName = "quote_table")
data class Quote(
   @PrimaryKey(autoGenerate = true)
    val quote: String
)