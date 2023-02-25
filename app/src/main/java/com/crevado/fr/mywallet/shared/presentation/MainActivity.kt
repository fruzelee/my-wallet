package com.crevado.fr.mywallet.shared.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crevado.fr.mywallet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}