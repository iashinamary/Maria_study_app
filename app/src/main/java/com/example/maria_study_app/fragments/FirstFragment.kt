package com.example.maria_study_app.fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.maria_study_app.*
import com.example.maria_study_app.adapter.MyAdapter
import com.example.maria_study_app.databinding.FirstFragmentLayoutBinding
import com.example.maria_study_app.viewmodels.FragmentOneVm
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess


class FirstFragment : Fragment() {

    private lateinit var binding: FirstFragmentLayoutBinding
    private val vm by viewModel<FragmentOneVm>()
    private val adapter by lazy { MyAdapter() }
    private val permissionUtil = PermissionUtil
    private var logText = ""

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
        checkPermission()
        vm.factsLive.observe(viewLifecycleOwner) {
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

    private fun checkPermission() {
        if (!PermissionUtil.hasPermissions(requireContext())) {
            val resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    doOnPermission()
                }
            PermissionUtil.registerLauncher(resultLauncher)
            val oldResultLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                    if (result) {
                        doOnPermission()
                    }
                }
            PermissionUtil.oldRegisterLauncher(oldResultLauncher)
            PermissionUtil.requestPermissions(requireActivity())
        }

    }

    private fun doOnPermission() {
        if (PermissionUtil.hasPermissions(requireContext())) {
            Toast.makeText(
                requireContext(),
                "permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tool_menu, menu)
        val search = menu.findItem(R.id.search).actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                logText = "Text changed: $query"
                Log.d("@@@", logText)
                Test.logText(logText)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.setNewQuery(newText)
                logText = "Text changed: $newText"
                Log.d("@@@", logText)
                Test.logText(logText)
                return true
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        logText = "Menu is prepared to be displayed"
        Log.d("@@@", logText)
        Test.logText(logText)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //TODO
        /**
         *  Сделать мгновенный выход из приложения по нажатию пункта меню
         */
        when (item.itemId) {
            R.id.exit -> {
                logText = "Successful exit"
                Log.d("@@@", logText)
                Test.logText(logText)
                exitProcess(0)
            }
            R.id.about -> {
//                AlertDialog.Builder(requireContext())
//                    .setMessage("Hello")
//                    .setCancelable(false)
//                    .setPositiveButton("OK") { d, w -> }
//                    .setNegativeButton("Cancel"){d, w ->
//                        d.dismiss()
//                    }
//                    .show()
                parentFragmentManager.beginTransaction().add(MyDialog(), null).commit()
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
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Enter number", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    et.text.clear()
                }
            }
        }
    }

}
