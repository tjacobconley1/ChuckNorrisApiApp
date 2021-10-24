package com.cfmg.chucknorris.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// the view model provides data to the
// UI and also survives configuration changes
// Communicator between DB and UI
class QuoteViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Quote>>
    private val repository: QuoteRepository

    // THE INIT BLOCK IS ALWAYS EXECUTED
    // BEFORE ANYTHING ELSE & SEQUENTIALLY
    init{
        val quoteDao = QuoteDatabase.getDatabase(application).quoteDao()
        repository = QuoteRepository(quoteDao)
        readAllData = repository.readAllData
    }

    // this is an operation on the RoomDB
    // never should be executed on the
    // Main Thread
    fun addQuote(quote: Quote){
        // this launches everything inside of
        // the brackets as a coroutine
        viewModelScope.launch(Dispatchers.IO){
            repository.addQuote(quote)
        }
    }

}