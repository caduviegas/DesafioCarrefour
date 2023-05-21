package com.innaval.desafiocarrefour.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innaval.desafiocarrefour.R
import com.innaval.desafiocarrefour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1500)
        setTheme(R.style.Theme_Main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}