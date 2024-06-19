package com.example.rickandmortyguide.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        val amaniDunia: AnimationDrawable = binding.amaniduniaapps.background as AnimationDrawable
        amaniDunia.setEnterFadeDuration(8888)
        amaniDunia.setExitFadeDuration(8888)
        amaniDunia.start()
    }

}