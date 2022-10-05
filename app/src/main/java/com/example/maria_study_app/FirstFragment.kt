package com.example.maria_study_app


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.maria_study_app.databinding.FirstFragmentLayoutBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
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
        setHasOptionsMenu(true)
        //todo
//        ContextCompat.startForegroundService(requireContext(), )
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
                    adapter.setNewList(it)
                }
            }.collect()
        }
    }

    /**
     * dz
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val search =  menu.getItem(R.id.search) as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.setNewQuery(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
