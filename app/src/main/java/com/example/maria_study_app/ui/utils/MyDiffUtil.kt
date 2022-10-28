package com.example.maria_study_app.ui.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.maria_study_app.domain.models.CatFacts

class MyDiffUtil(
    private val oldList: List<CatFacts>,
    private val newList: List<CatFacts>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].fact == newList[newItemPosition].fact
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }
}