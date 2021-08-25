package com.example.dunzoclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.ClickLitener.StoreCatItemClickListener
import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolder.StoreCategoryViewHolder
import java.util.ArrayList

class StoreCategoryAdapter(
    private val categoryList: ArrayList<ProductCategory>,
    private var storeCatItemClickListener: StoreCatItemClickListener
) : RecyclerView.Adapter<StoreCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_store_cat, parent, false)
        return StoreCategoryViewHolder(view, storeCatItemClickListener)
    }

    override fun onBindViewHolder(holder: StoreCategoryViewHolder, position: Int) {
        val category: ProductCategory = categoryList[position]
        holder.setStoreCategoryData(category, position)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}