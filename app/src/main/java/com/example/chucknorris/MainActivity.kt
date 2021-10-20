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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

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
        btn_norris.setOnClickListener{

            // launch coroutine
            // 3 possible coroutine scopes
            // IO, Main, Default(heavy computational work)
            CoroutineScope(Dispatchers.IO).launch {
                // TODO -> STORE EACH RETURNED QUOTE INTO A ROOMDB
                // TODO -> DISPLAY OLD QUOTES IN A RECYCLER VIEW
                DisplayQuote()}

                // play whip sound for epic user experience
                playWhip.start()
            }

        // what will be executed when the menu arrow is clicked
        btn_menu.setOnClickListener{

            //TODO create a second activity
            //TODO navigate to it lol

        }

    }





    override fun onStop() {
        super.onStop()
        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        playWhip.start()
    }
    override fun onPause() {
        super.onPause()
        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        playWhip.start()
    }
    override fun onResume(){
        super.onResume()
        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        playWhip.start()
    }
    override fun onDestroy(){
        super.onDestroy()
        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        playWhip.start()
    }


    // This function is marked as suspend because it will
    // be executed within a coroutine on a separate worker
    // thread in order to reduce UI latency
    suspend fun DisplayQuote() {
        val quote = getApi().getQuote()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //ON NEXT
                tv_quote.text = it.body()!!.value
            }, {
                //ON FAILURE
            })
    }






}