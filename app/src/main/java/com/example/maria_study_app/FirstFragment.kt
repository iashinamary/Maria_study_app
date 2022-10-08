package com.example.maria_study_app


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.maria_study_app.databinding.FirstFragmentLayoutBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


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
        setHasOptionsMenu(true)
        //todo
//        ContextCompat.startForegroundService(requireContext(), )
        binding = FirstFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        initViews()
        vm.factsLive.observe(viewLifecycleOwner){
            // что делать и как отобразить полученные факты
        }
        lifecycleScope.launchWhenStarted {
            vm.factsFlow.onEach {
                it.let {
                    adapter.setNewList(it)
                }
            }.collect()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tool_menu, menu)
        val search =  menu.findItem(R.id.search).actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("@@@", "Text changed: ")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.setNewQuery(newText)
                Log.d("@@@", "Text changed: $newText")
                return true
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Log.d("@@@", "Text changed: ")
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("@@@", "Text changed: ")
        when(item.itemId){
            R.id.exit -> {
            }
            R.id.search -> {

            }
        }
        return false
    }

    private fun initViews() {
        binding.apply {
            recycler.adapter = adapter
            button.setOnClickListener {
                try {
                    val limit = et.text.toString().toInt()
                    vm.getFacts(limit)
                } catch (e: Exception){
                    Toast.makeText(requireContext(), "Enter number", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    et.text.clear()
                }
            }
        }
    }

}
