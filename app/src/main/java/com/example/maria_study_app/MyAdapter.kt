package com.example.maria_study_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maria_study_app.databinding.ItemNewBinding
import java.util.Collections.list

class MyAdapter: RecyclerView.Adapter<NoteViewHolder>() {

    private var list = listOf<MyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.name.text = item.title
        holder.binding.textMessage.text = item.text
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(position: Int) = list[position]


    fun setNewList(newList: List<MyEntity>){
        val result = DiffUtil.calculateDiff(MyDiffUtil(list, newList))
        result.dispatchUpdatesTo(this)
        list = newList
    }
}