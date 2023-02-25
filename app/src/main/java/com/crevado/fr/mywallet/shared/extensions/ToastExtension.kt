package com.crevado.fr.mywallet.shared.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.showErrorToast(message: String?) {
    this.apply {
        if (!message.isNullOrEmpty()) {
            Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}