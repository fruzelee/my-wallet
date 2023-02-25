package com.crevado.fr.mywallet.shared.business

import com.crevado.fr.mywallet.login.business.UserInfoModel

interface WalletRepository {
    suspend fun getLoginResponse(name: String, pin: String): UserInfoModel

}