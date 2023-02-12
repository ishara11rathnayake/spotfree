package com.ishara11rathnayake.spotfree.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://data.melbourne.vic.gov.au/api/records/1.0/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: APIService by lazy {
        retrofitBuilder.build().create(APIService::class.java)
    }
}
