package com.cfmg.chucknorris.model.remote

import com.cfmg.chucknorris.common.Constants
import com.cfmg.chucknorris.model.QuoteResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET(Constants.END_POINT)
    fun getQuote(): Observable<Response<QuoteResponse>>


    companion object{
        fun getApi() =
            Retrofit.Builder()
                .client(createClient())
                .baseUrl(Constants.BASE_NORRIS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(Api::class.java)

        private fun createClient():
                OkHttpClient {
            return OkHttpClient.Builder().build()

        }
    }
}