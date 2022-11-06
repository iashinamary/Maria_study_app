package com.example.maria_study_app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.example.maria_study_app.R
import com.example.maria_study_app.databinding.ImageLayoutBinding
import com.example.maria_study_app.ui.dialog.MyDialog
import java.io.IOException
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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val url = "https://damion.club/uploads/posts/2022-02/thumbs/1644985609_21-damion-club-p-voskhod-na-more-priroda-29.jpg"
        Glide
            .with(requireContext())
            .load(url)
            .placeholder(resources.getDrawable(R.drawable.ic_launcher_background))
            .into(binding.image)
         try {
             throw Exception()
        } catch (e : IOException){
            Log.d("@@@", e.stackTraceToString())
        } catch (e2 : IndexOutOfBoundsException){
            e2.printStackTrace()
        } catch (e : Exception){
            try {
                throw Exception()
            } catch (e : Exception){}
        } finally {
             Log.d("@@@", "FINALLY")
        }
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