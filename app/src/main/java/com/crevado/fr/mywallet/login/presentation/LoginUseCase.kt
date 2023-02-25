package com.crevado.fr.mywallet.login.presentation

import com.crevado.fr.mywallet.login.business.UserInfoModel
import com.crevado.fr.mywallet.shared.business.WalletRepository
import com.crevado.fr.mywallet.shared.business.Result
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase @Inject constructor(
    private val repository: WalletRepository
) {
    operator fun invoke(userName: String, pin: String): Flow<Result<UserInfoModel>> = flow {
        try {
            emit(Result.Loading())
            val data = repository.getLoginResponse(name = userName, pin = pin)
            emit(Result.Success(data))
        } catch (e: IOException) {
            emit(Result.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}