package com.example.chucknorris.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Marks a class as a RoomDatabase.
// The class should be an abstract
// class and extend RoomDatabase
@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    // THIS IS A SINGLETON
    companion object{
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        // either uses existing databse
        // or creates a new one if none exist
        fun getDatabase(context: Context): QuoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // everything in this block is protected from
            // concurrent execution by multiple threads
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java,
                    "quote+database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}