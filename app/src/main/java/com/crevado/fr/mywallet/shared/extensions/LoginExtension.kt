package com.crevado.fr.mywallet.shared.extensions

import com.crevado.fr.mywallet.login.business.UserInfoModel
import com.crevado.fr.mywallet.login.data.LoginResponseModel

fun LoginResponseModel.toUserInfo(): UserInfoModel {
    return UserInfoModel(
        data?.userInfo?.id.toString(),
        data?.userInfo?.email,
        data?.userInfo?.userName,
        data?.userInfo?.walletAddress,
        data?.userInfo?.profileImage,
        data?.accountInfo?.balance,
        data?.accountInfo?.currency
    )
}