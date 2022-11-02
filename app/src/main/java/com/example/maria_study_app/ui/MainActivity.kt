package com.example.maria_study_app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maria_study_app.R
import com.example.maria_study_app.databinding.MainActivityLayoutBinding


class MainActivity : AppCompatActivity() {

    /**
     * Coздать фрагмент с имидж вью в нутри, текствью чуть ниже имг вью. Фрагмент должен скролиться
     * Добавить переход на него в навигатион драйвере
     */

    companion object {
        const val SHARED_PREFS_NAME = "sharedPrefs"
        const val text_view_key = "tv"
    }

    private lateinit var binding: MainActivityLayoutBinding

    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)



        findNavController(R.id.container).let {
            binding.navigation.setupWithNavController(it)
            setupActionBarWithNavController(it)
        }





//        binding.navigation.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.first_fragment_button -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.container, FirstFragment()).commit()
//                }
//                R.id.second_fragment_button -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.container, SecondFragment()).commit()
//                }
//                R.id.third_fragment_button -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.container, ThirdFragment()).commit()
//                }
//            }
//            true
//        }
//        binding.navigation.selectedItemId = R.id.first_fragment_button
    }


}