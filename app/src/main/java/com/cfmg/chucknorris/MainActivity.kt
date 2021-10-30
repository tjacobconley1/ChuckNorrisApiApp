package com.cfmg.chucknorris

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.cfmg.chucknorris.model.remote.Api.Companion.getApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // removes the bar at the top of the activity
        try { this.supportActionBar!!.hide()
        } catch (e: NullPointerException) { }

        setContentView(R.layout.activity_main)


        // build the audio recorder
//        val AUDIO_SOURCE = MediaRecorder.AudioSource.VOICE_RECOGNITION
//        val SAMPLE_RATE = 16000
//        val CHANNEL_MASK = AudioFormat.CHANNEL_IN_MONO
//        val ENCODING = AudioFormat.ENCODING_PCM_16BIT
//        val BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_MASK, ENCODING)
//        val AUDIO_FORMAT =
//            AudioFormat.Builder().setEncoding(ENCODING)
//                .setSampleRate(SAMPLE_RATE)
//                .setChannelMask(CHANNEL_MASK)
//                .build()
        //        val mRecorder = AudioRecord.Builder().setAudioSource(AUDIO_SOURCE)
//            .setAudioFormat(AUDIO_FORMAT)
//            .setBufferSizeInBytes(BUFFER_SIZE)
//            .build()
// /     mRecorder?.startRecording()

//
//        val output = Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"
//        val mediaRecorder = MediaRecorder()
//        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
//        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
//        mediaRecorder?.setOutputFile(output)
//
//        //mediaRecorder?.start()
//



        // creates an object of the MediaPlayer Class
        // with the whip.mp3 as a sound to play
        val playWhip: MediaPlayer = MediaPlayer.create(this, R.raw.whip)
        //playWhip.start()

        // create a firestore Database
        val db = Firebase.firestore


        // what to execute when the image button is pressed
        btn_norris.setOnClickListener{

            // launch coroutine
            // 3 possible coroutine scopes
            // IO, Main, Default(heavy computational work)
            CoroutineScope(Dispatchers.IO).launch {
                // TODO -> STORE EACH RETURNED QUOTE INTO A ROOMDB
                // TODO -> DISPLAY OLD QUOTES IN A RECYCLER VIEW
                // display quote and return it's string value
                // to a variable
                val quotehash = DisplayQuote()
                // create a new quote hashmap
                val quotehashmap = hashMapOf("quote" to quotehash)
                // add a new document with a generated ID
                db.collection("ChuckNorris")
                    .add(quotehashmap)

            }

                // play whip sound for epic user experience
                playWhip.start()
            }

        // what will be executed when the menu arrow is clicked
        btn_menu.setOnClickListener{

            //TODO create a second activity
            //TODO navigate to it lol
            val intent = Intent(this, SettingsAndHistoricalQuotes::class.java)
            startActivity(intent)


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
    suspend fun DisplayQuote(): String {
        val quote = getApi().getQuote()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //ON NEXT
                tv_quote.text = it.body()!!.value
            }, {
                //ON FAILURE
            })
        return tv_quote.text.toString()
    }






}