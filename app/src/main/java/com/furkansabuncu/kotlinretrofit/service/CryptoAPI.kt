package com.furkansabuncu.kotlinretrofit.service

import android.telecom.Call
import com.furkansabuncu.kotlinretrofit.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): retrofit2.Call<List<CryptoModel>>
}