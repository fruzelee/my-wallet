package com.crevado.fr.mywallet.di

import com.crevado.fr.mywallet.BuildConfig
import com.crevado.fr.mywallet.shared.business.WalletRepository
import com.crevado.fr.mywallet.shared.data.repository.api.WalletService
import com.crevado.fr.mywallet.shared.data.repository.api.WalletRepositoryImpl
import com.crevado.fr.mywallet.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideWalletApi(okHttpClient: OkHttpClient): WalletService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalletService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: WalletService): WalletRepository {
        return WalletRepositoryImpl(api)
    }
}