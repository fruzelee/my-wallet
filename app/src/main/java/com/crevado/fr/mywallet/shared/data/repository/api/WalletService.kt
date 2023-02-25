package com.crevado.fr.mywallet.shared.data.repository.api

import com.crevado.fr.mywallet.login.data.LoginRequestModel
import com.crevado.fr.mywallet.login.data.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface WalletService {

    @POST("v1/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequestModel): LoginResponseModel
}