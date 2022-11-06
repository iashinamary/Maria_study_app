package com.example.maria_study_app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.maria_study_app.R
import com.example.maria_study_app.databinding.ImageLayoutBinding
import com.example.maria_study_app.ui.dialog.MyDialog
import kotlin.system.exitProcess


class Image : Fragment() {

    private lateinit var binding: ImageLayoutBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = ImageLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
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
                requireActivity().onBackPressed()
            }
        }
        return false
    }


}