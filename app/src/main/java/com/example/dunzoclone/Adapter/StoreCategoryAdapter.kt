package com.example.dunzoclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.R
import com.example.dunzoclone.StoreCategoryModel
import com.example.dunzoclone.ViewHolder.StoreCategoryViewHolder

class StoreCategoryAdapter(private val categoryList: List<StoreCategoryModel>): RecyclerView.Adapter<StoreCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_store_category,parent,false)
        return StoreCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreCategoryViewHolder, position: Int) {
    val category: StoreCategoryModel  = categoryList[position]
        holder.setStoreCategoryData(category)
    }

    override fun getItemCount(): Int {
    return categoryList.size
    }
}