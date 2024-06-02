package com.example.rickandmortyguide

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rickandmortyguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        bg_anim_header()
    }

    private fun bg_anim_header() {
        val animHeader: AnimationDrawable = binding.clRickHeader.background as AnimationDrawable
        animHeader.setEnterFadeDuration(8)
        animHeader.setExitFadeDuration(3333)
        animHeader.start()
    }
}