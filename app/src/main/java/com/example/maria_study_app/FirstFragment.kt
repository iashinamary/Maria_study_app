package com.example.maria_study_app

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Bundle
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
    private val prefs by inject<SharedPreferences>()
    private val repo by inject<Repository>()
    private val vm by sharedViewModel<FragmentOneVm>()
    private val a by lazy { MyAdapter() }

    companion object {
        const val SHARED_PREFS_NAME = "PREFS"
        const val SAVED_TEXT_KEY = "KEY"
    }

    override fun onAttach(context: Context) {
        val a = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val b = a.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        super.onAttach(context)
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
                // что делать и как отобразить полученные факты
            }.collect()
        }
    }

    private fun initViews() {
        binding.apply {

            tv.text = prefs.getString(SAVED_TEXT_KEY, "")

            button.setOnClickListener {
                val text = et.text.toString()
                prefs.edit().putString(SAVED_TEXT_KEY, text).apply()
                tv.text = text
                generateNote()
            }
            val a = ConcurrentLinkedQueue<Int>()


        }
    }

    private fun generateNote() {
        val generator = UUID.randomUUID()
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) {
                repo.insertNote(
                    MyEntity(
                        generator.toString(),
                        generator.toString(),
                        binding.et.text.toString(),
                        System.currentTimeMillis()
                    )
                )
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_SHORT).show()
                binding.et.text.clear()
            }
        }
    }
}
