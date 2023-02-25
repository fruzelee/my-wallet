package com.crevado.fr.mywallet.send_fund.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.crevado.fr.mywallet.R
import com.crevado.fr.mywallet.databinding.FragmentSendFundBinding
import com.crevado.fr.mywallet.utils.BundleKey
import com.crevado.fr.mywallet.shared.extensions.hideKeyboard
import com.crevado.fr.mywallet.shared.extensions.loadProfileImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendFundFragment : Fragment(), TextWatcher, View.OnClickListener {
    private lateinit var binding: FragmentSendFundBinding
    private var userId: String = ""
    private var userName: String = ""
    private var userWalletAddress: String = ""
    private var userEmail: String = ""
    private var userProfileImage: String = ""
    private var userBalance: String = ""
    private var userCurrency: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendFundBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValuesFromBundles()
        initViews()
        setupListeners()
    }

    private fun getValuesFromBundles() {
        Log.d("TAG", "getValuesFromBundles")
        userId = arguments?.getString(BundleKey.KEY_USER_ID) as String
        userName = arguments?.getString(BundleKey.KEY_USER_NAME) as String
        userWalletAddress = arguments?.getString(BundleKey.KEY_USER_WALLET) as String
        userEmail = arguments?.getString(BundleKey.KEY_USER_EMAIL) as String
        userProfileImage = arguments?.getString(BundleKey.KEY_USER_IMAGE_URL) as String
        userBalance = arguments?.getString(BundleKey.KEY_BALANCE) as String
        userCurrency = arguments?.getString(BundleKey.KEY_CURRENCY) as String
    }

    private fun initViews() {
        binding.apply {
            if (userProfileImage.isNotEmpty()) {
                ivUserImage.loadProfileImage(userProfileImage)
            }
            tvAmountCurrency.text = userCurrency
            tvUserName.text = "@".plus(userName)
            tvUserWalletAddress.text = " - ".plus(userWalletAddress.substring(0, 4)).plus("...")
                .plus(getLastNCharsOfString(userWalletAddress, 4))
            tvUserBalance.text = getString(R.string.tv_balance).plus(" ")
                .plus(userCurrency.plus(" ").plus(userBalance))
            tvUserName.isSelected = true
            isShowAddFund(false)
        }
    }

    private fun getLastNCharsOfString(str: String, n: Int): String {
        var lastNChars = str
        if (lastNChars.length > n) {
            lastNChars = lastNChars.substring(lastNChars.length - n, lastNChars.length)
        }
        return lastNChars
    }

    private fun setupListeners() {
        binding.apply {
            btnBack.setOnClickListener(this@SendFundFragment)
            btnMax.setOnClickListener(this@SendFundFragment)
            btnContinue.setOnClickListener(this@SendFundFragment)
            etAmount.addTextChangedListener(this@SendFundFragment)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.btn_back -> {
                    //Toast.makeText(context, "back button pressed!", Toast.LENGTH_SHORT).show()
                    //finishCurrent()
                    requireArguments().clear()
                    findNavController().navigate(
                        R.id.action_send_fund_fragment_to_login_fragment
                    )
                }
                R.id.btn_max -> {
                    binding.etAmount.setText(userBalance)
                    binding.etAmount.setSelection(binding.etAmount.text!!.length)
                }
                R.id.btnContinue -> {
                    activity?.hideKeyboard()
                }
                else -> {}
            }
        }
    }

    private fun isShowAddFund(isShow: Boolean) {
        binding.tvErrorInsufficientBalance.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.btnAddFund.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun beforeTextChanged(
        chatSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        if (editable.toString().isNotEmpty()) {
            processAmountInput(editable.toString())
        }
    }

    private fun processAmountInput(amount: String) {
        if (amount.toDouble() > userBalance.toDouble()) {
            isShowAddFund(true)
        } else {
            isShowAddFund(false)
        }
    }
}