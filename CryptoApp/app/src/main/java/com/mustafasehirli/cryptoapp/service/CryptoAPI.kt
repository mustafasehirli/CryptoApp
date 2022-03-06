package com.mustafasehirli.cryptoapp.service

import com.mustafasehirli.cryptoapp.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //https://api.nomics.com/v1/prices?key=8569324409851d1e1b15d2f91f77a392fd8521a9
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CryptoModel>>
}