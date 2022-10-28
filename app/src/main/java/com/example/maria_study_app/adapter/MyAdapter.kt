package com.example.maria_study_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maria_study_app.MyDiffUtil
import com.example.maria_study_app.databinding.ItemNewBinding

class MyAdapter: RecyclerView.Adapter<NoteViewHolder>() {

    private var list = listOf<CatFacts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.name.text = item.length.toString()
        holder.binding.textMessage.text = item.fact
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(position: Int) = list[position]


    fun setNewList(newList: List<CatFacts>){
        val result = DiffUtil.calculateDiff(MyDiffUtil(list, newList))
        result.dispatchUpdatesTo(this)
        list = newList
    }
}