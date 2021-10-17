package com.example.chucknorris.model.remote

import com.example.chucknorris.common.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRequest {
    private val builder: Retrofit.Builder = Retrofit.Builder()


    fun getResponse(): Api{
        builder.baseUrl(Constants.BASE_NORRIS_URL)
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        return builder.build().create(Api::class.java)
    }
}

