package com.crevado.fr.mywallet.login.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.crevado.fr.mywallet.R
import com.crevado.fr.mywallet.databinding.FragmentUserLoginBinding
import com.crevado.fr.mywallet.login.business.UserInfoModel
import com.crevado.fr.mywallet.shared.extensions.hideKeyboard
import com.crevado.fr.mywallet.shared.extensions.showErrorToast
import com.crevado.fr.mywallet.utils.BundleKey
import com.crevado.fr.pinviewlibrary.PinField
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class LoginFragment : Fragment(), TextWatcher, View.OnClickListener {
    private lateinit var binding: FragmentUserLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private var userPin = ""
    private var timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
        startObserver()
    }

    private fun initViews() {
        binding.apply {
            isBtnContinueEnable(isEnable = false)
            isPinEnable(false)
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnContinue.setOnClickListener(this@LoginFragment)
            etUserName.addTextChangedListener(this@LoginFragment)
            // pin view listener
            val listener = object : PinField.OnTextCompleteListener {
                override fun onTextComplete(enteredText: String): Boolean {
                    //Toast.makeText(this@LoginFragment,enteredText, Toast.LENGTH_SHORT).show()
                    userPin = enteredText
                    activity?.hideKeyboard()
                    isBtnContinueEnable(isEnable = true)
                    return true
                }
            }
            pinView.onTextCompleteListener = listener
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.btnContinue -> {
                    activity?.hideKeyboard()
                    val userName = binding.etUserName.text?.trim().toString().lowercase()
                    if (isUserNameValid(userName)) {
                        viewModel.login(userName, userPin)
                    }
                }
            }
        }
    }

    private fun startObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { it ->
                    isShowProgressBar(it.isLoading)

                    it.error.apply {
                        activity?.showErrorToast(this)
                    }
                    it.data?.let {
                        val userData = it as UserInfoModel
                        if (userData.errorMessage.isNullOrBlank()) {
                            val bundle = Bundle()
                            bundle.putString(BundleKey.KEY_USER_ID, userData.id)
                            bundle.putString(BundleKey.KEY_USER_NAME, userData.userName)
                            bundle.putString(BundleKey.KEY_USER_WALLET, userData.walletAddress)
                            bundle.putString(BundleKey.KEY_USER_EMAIL, userData.email)
                            bundle.putString(BundleKey.KEY_USER_IMAGE_URL, userData.profileImageUrl)
                            bundle.putString(BundleKey.KEY_BALANCE, userData.balance.toString())
                            bundle.putString(BundleKey.KEY_CURRENCY, userData.currency)
                            findNavController().navigate(
                                R.id.action_login_fragment_to_send_fund_fragment, bundle
                            )
                        } else {
                            activity?.showErrorToast(userData.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun isShowProgressBar(isShow: Boolean) {
        binding.loadingView.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun beforeTextChanged(
        chatSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        // The user is typing: reset already started timer (if existing)
        timer.cancel()
    }

    override fun afterTextChanged(editable: Editable?) {
        // The user typed: start the timer
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {

                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    isPinEnable(isUserNameValid(editable.toString().lowercase()))
                }

            }
        }, 2000) // 2000 ms delay before the timer executes the „run“ method from TimerTask
    }

    private fun isPinEnable(isEnable: Boolean) {
        if (isEnable) {
            binding.pinView.isEnabled = true
            binding.pinView.alpha = 1f
        } else {
            binding.pinView.isEnabled = false
            binding.pinView.alpha = 0.7f
        }
    }

    private fun isBtnContinueEnable(isEnable: Boolean) {
        if (isEnable) {
            binding.btnContinue.isEnabled = true
            binding.btnContinue.alpha = 1f
        } else {
            binding.btnContinue.isEnabled = false
            binding.btnContinue.alpha = 0.5f
        }
    }

    private fun isUserNameValid(userName: String): Boolean {
        var charCount = 0
        var previousSpecialChar: Char = Char.MIN_VALUE
        var isSuccessiveSpecialChar = false
        userName.forEach {
            if (it == '.' || it == '_') {
                if (it == previousSpecialChar) {
                    isSuccessiveSpecialChar = true
                } else {
                    previousSpecialChar = it
                }
            } else {
                charCount++
                previousSpecialChar = Char.MIN_VALUE
            }
        }

        if (userName.contains(" ")) {
            //activity?.showErrorToast("No Spaces allowed.")
            binding.layoutParentUserName.error = "No Spaces allowed."
            return false
        }

        if (userName.length > 33 || charCount < 3 || isSuccessiveSpecialChar) {

            if (charCount < 3) {
                //activity?.showErrorToast("Usernames must have at least 3 characters.")
                binding.layoutParentUserName.error = "Usernames must have at least 3 characters."
            }

            if (userName.length > 33) {
                //activity?.showErrorToast("Username can't exceed 32 characters.")
                binding.layoutParentUserName.error = "Username can't exceed 32 characters."
            }

            if (isSuccessiveSpecialChar) {
                //activity?.showErrorToast("This username is invalid.")
                binding.layoutParentUserName.error = "This username is invalid."
            }

            return false
        }

        binding.layoutParentUserName.error = null
        return true
    }
}