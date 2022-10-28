package com.example.maria_study_app.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.maria_study_app.domain.utils.Test
import com.example.maria_study_app.databinding.ThirdFragmentLayoutBinding
import org.koin.android.ext.android.inject

class ThirdFragment: Fragment() {



    private lateinit var binding: ThirdFragmentLayoutBinding
    private val prefs by inject<SharedPreferences>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ThirdFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val array = Test.readLines()
        array.forEach {
            binding.textview.text = "${binding.textview.text}\n$it"
        }

    }
}
