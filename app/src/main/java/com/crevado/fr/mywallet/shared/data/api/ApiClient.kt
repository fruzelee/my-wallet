package com.crevado.fr.mywallet.shared.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        fun getService(): WalletService {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val build = Retrofit.Builder()
                .client(client)
                .baseUrl("https://wind-assessment-api.vercel.app/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return build.create(WalletService::class.java)
        }
    }
}