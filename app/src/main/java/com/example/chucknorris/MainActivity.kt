package com.example.chucknorris

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.chucknorris.model.QuoteResponse
import com.example.chucknorris.model.remote.Api.Companion.getApi

import com.example.chucknorris.model.remote.HttpRequest.getResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    private val API_RESPONSE = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // removes the bar at the top of the activity
        try { this.supportActionBar!!.hide()
        } catch (e: NullPointerException) { }

        setContentView(R.layout.activity_main)


        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        playWhip.start()



        // what to execute when the image button is pressed
        btn_norris.setOnClickListener(View.OnClickListener {

            //getResponse()
            println("debug " + getApi().getQuote())
            // must pull back next quote from the api and
            // assign the new quote to the variable
            // then observe the quote using the text view
            val text = getApi().getQuote()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                             //ON NEXT
                            tv_quote.text = it.body()!!.value
                            },{
                            //ON FAILURE
                            })

            // play whip sound for epic app experience
            playWhip.start()
        })

    }





    // pulls the quote returned from the Api into
    // the QUOTE variable and returns it as a string
    private suspend fun getApiResults(): String{

        val QUOTE = getApi().getQuote()
        delay(1000)
        return QUOTE.toString()
    }

}