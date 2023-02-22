package com.crevado.fr.mywallet.utils.pin_view

interface PinListener {
    /**
     * Callback Fired when user starts typing in the OTP/PIN box.
     */
    fun onInteractionListener()

    /**
     * @param pin Filled PIN
     * Callback Fired when user has completed filling the OTP/PIN.
     */
    fun onOTPComplete(pin: String)
}
