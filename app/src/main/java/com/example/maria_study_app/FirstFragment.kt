package com.example.maria_study_app

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.maria_study_app.databinding.FirstFragmentLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

class FirstFragment: Fragment() {

    private lateinit var binding: FirstFragmentLayoutBinding
    private val vm by viewModel<FragmentOneVm>()
    private val adapter by lazy { MyAdapter() }

    companion object {
        const val SHARED_PREFS_NAME = "PREFS"
        const val SAVED_TEXT_KEY = "KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        vm.factsLive.observe(viewLifecycleOwner){
            // что делать и как отобразить полученные факты
        }
        lifecycleScope.launchWhenStarted {
            vm.factsFlow.onEach {
                it?.let {
                    adapter.setNewList(it.data)
                }
            }.collect()
        }
    }

    private fun initViews() {
        binding.apply {
            recycler.adapter = adapter
            button.setOnClickListener {
                val limit = et.text.toString().toInt()
                vm.getFacts(limit)
            }
        }
    }

}
