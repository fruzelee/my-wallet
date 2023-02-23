package com.crevado.fr.mywallet.user_login.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("Email")
    val email: String = "",
    @SerializedName("UserName")
    val userName: String = "",
    @SerializedName("smartContactWallet")
    val smartContactWallet: String = "",
    @SerializedName("ProfileImage")
    val profileImage: String = "",
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("WalletAddress")
    val walletAddress: String = ""
)


data class Data(
    @SerializedName("accountInfo")
    val accountInfo: AccountInfo,
    @SerializedName("userInfo")
    val userInfo: UserInfo
)


data class UserLoginEntity(
    @SerializedName("data")
    val data: Data,
    @SerializedName("messages")
    val messages: List<String>?,
    @SerializedName("status")
    val status: Boolean = false
)


data class AccountInfo(
    @SerializedName("balance")
    val balance: Double = 0.0,
    @SerializedName("currency")
    val currency: String = ""
)


