package com.crevado.fr.mywallet.shared.data.repository.api


import com.crevado.fr.mywallet.login.business.UserInfoModel
import com.crevado.fr.mywallet.login.data.LoginRequestModel
import com.crevado.fr.mywallet.shared.business.WalletRepository
import com.crevado.fr.mywallet.shared.extensions.toUserInfo
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val api: WalletService
) : WalletRepository {

    override suspend fun getLoginResponse(name: String, pin: String): UserInfoModel {
        return try {
            api.getLoginResponse(
                loginRequest = LoginRequestModel(
                    user = name,
                    pin = pin
                )
            ).toUserInfo()
        } catch (e: Exception) {
            UserInfoModel(errorMessage = "Something wrong happened")
        }
    }
}