package com.example.maria_study_app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.maria_study_app.databinding.MainActivityLayoutBinding

/**
 * Прочитать про жизненный цикл активити, прочитать про основные компоненты андроид-приложений
 * посмотреть на экране размети доступные вьюшки, добавит ТекстВью, разместить его на экране,
 * запустить приложение, изменить тему, изменить бекграунд, создать стиль для текствью
 */

class MainActivity: AppCompatActivity() {

    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root) //передаём корневой элемент биндинга
        initViews()
    }

    private fun initViews() {
       binding.textView.text = "Hello World"
    }
}