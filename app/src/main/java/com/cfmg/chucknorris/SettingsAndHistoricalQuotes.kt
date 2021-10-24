package com.cfmg.chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class SettingsAndHistoricalQuotes : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var quoteArrayList: ArrayList<QuoteData>
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_and_historical_quotes)

        recyclerView = findViewById(R.id.rv_settings)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        quoteArrayList = arrayListOf()

        quoteAdapter = QuoteAdapter(quoteArrayList)

        recyclerView.adapter = quoteAdapter

        EventChangeListener()

    }
    private fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("ChuckNorris")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG)
                        return
                    }

                    for(dc : DocumentChange in value?.documentChanges!!){

                        if (dc.type == DocumentChange.Type.ADDED){
                            quoteArrayList.add(dc.document.toObject(QuoteData::class.java))
                        }
                    }

                    quoteAdapter.notifyDataSetChanged()

                }

            })


    }

}