package com.cfmg.chucknorris.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// this class represents an entity within
// the roomdb
@Entity(tableName = "quote_table")
data class Quote(
   @PrimaryKey(autoGenerate = false)
    val quote: String
)