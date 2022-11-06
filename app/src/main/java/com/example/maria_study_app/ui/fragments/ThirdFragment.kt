package com.example.maria_study_app.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.maria_study_app.R
import com.example.maria_study_app.domain.utils.Test
import com.example.maria_study_app.databinding.ThirdFragmentLayoutBinding
import com.example.maria_study_app.ui.dialog.MyDialog
import org.koin.android.ext.android.inject
import kotlin.system.exitProcess

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
        setHasOptionsMenu(true)
        binding = ThirdFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        initViews()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tool_menu_without_search, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                exitProcess(0)
            }
            R.id.about -> {
                parentFragmentManager.beginTransaction().add(MyDialog(), null).commit()
            }

            R.id.back -> {
                var counter = 0
                counter++
                if(counter == 1) {
                    Toast.makeText(
                        requireContext(),
                        "This is the last fragment",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    requireActivity().onBackPressed()
                }
            }
        }
        return false
    }

    private fun initViews() {
        val array = Test.readLines()
        array.forEach {
            binding.textview.text = "${binding.textview.text}\n$it"
        }

    }
}
