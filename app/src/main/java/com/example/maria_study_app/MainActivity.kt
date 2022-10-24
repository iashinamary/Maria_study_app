package com.example.maria_study_app

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maria_study_app.databinding.MainActivityLayoutBinding
import com.example.maria_study_app.fragments.FirstFragment
import com.example.maria_study_app.fragments.SecondFragment
import com.example.maria_study_app.fragments.ThirdFragment


class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFS_NAME = "sharedPrefs"
        const val text_view_key = "tv"
    }

    private lateinit var binding: MainActivityLayoutBinding

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        binding.navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.first_fragment_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, FirstFragment()).commit()
                }
                R.id.second_fragment_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, SecondFragment()).commit()
                }
                R.id.third_fragment_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ThirdFragment()).commit()
                }
            }
            true
        }
        binding.navigation.selectedItemId = R.id.first_fragment_button
    }


}