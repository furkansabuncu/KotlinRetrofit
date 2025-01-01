package com.furkansabuncu.kotlinretrofit.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.furkansabuncu.kotlinretrofit.R
import com.furkansabuncu.kotlinretrofit.model.CryptoModel
import com.furkansabuncu.kotlinretrofit.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL ="https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //https://deep-index.moralis.io/api/v2.2/market-data/global/market-cap
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJub25jZSI6ImZiMTMxMzhmLWY0MzEtNGIzMC04Y2NlLTQzNTE1ZTIxMjhmNyIsIm9yZ0lkIjoiNDIzNjg2IiwidXNlcklkIjoiNDM1NzQwIiwidHlwZUlkIjoiOWEyZTZiNjgtOGQ4YS00MTFjLWEzNzUtNTRjZTA3ZjgyNTdmIiwidHlwZSI6IlBST0pFQ1QiLCJpYXQiOjE3MzU3Mjc3NDcsImV4cCI6NDg5MTQ4Nzc0N30.bs6vwjAdODOuDzPrh8XM6ZSPy1wNRxR-2Q_KG3JTzqg

        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        for(cryptomodel : CryptoModel in cryptoModels!!){
                            println(cryptomodel.currency)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}