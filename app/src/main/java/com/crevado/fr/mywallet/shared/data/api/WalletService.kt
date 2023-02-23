package com.crevado.fr.mywallet.shared.data.api

import com.crevado.fr.mywallet.user_login.data.UserLoginEntity
import com.crevado.fr.mywallet.user_login.data.UserLoginRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface WalletService {

    @POST("login")
    suspend fun getUserLoginResponse(@Body userLoginRequest: UserLoginRequestModel): UserLoginEntity

}