package com.example.maria_study_app.fragments

import androidx.fragment.app.Fragment
import com.example.maria_study_app.MyAdapter
import com.example.maria_study_app.Repository
import com.example.maria_study_app.databinding.SecondFragmentLayoutBinding
import org.koin.android.ext.android.inject

class SecondFragment: Fragment() {
    private lateinit var binding: SecondFragmentLayoutBinding
    private val repo by inject<Repository>()
    private val adapter = MyAdapter()

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = SecondFragmentLayoutBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initViews()
//        subscribe()
//    }

//    private fun subscribe() {
//        repo.getAllNotes().observe(viewLifecycleOwner){
//            Log.d("@@@", it.size.toString())
//            adapter.setNewList(it)
//
//        }
    }

//    private fun initViews() {
//        binding.recycler.adapter = adapter
//    }
//}