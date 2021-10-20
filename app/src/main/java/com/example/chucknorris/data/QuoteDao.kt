package com.example.chucknorris.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// THis interface is used to allow
// front end components to interact
// with the local RoomDB
@Dao
interface QuoteDao {
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    // this function is accessed by the
    // QuoteRepository class
    suspend fun addQuote(quote: Quote)

    @Query("SELECT * FROM quote_table ORDER BY quote")
    // this function is accessed by the
    // QuoteRepository class
    fun readAllData(): LiveData<List<Quote>>
}