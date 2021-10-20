package com.example.chucknorris.data

import androidx.lifecycle.LiveData


class QuoteRepository(private val quoteDao: QuoteDao) {

    // reads all data from the database
    val readAllData: LiveData<List<Quote>> = quoteDao.readAllData()

    // function used to add a new quote
    // to the databse
    suspend fun addQuote(quote: Quote){
        quoteDao.addQuote(quote)
    }
}