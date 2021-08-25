package com.example.dunzoclone.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.StoreCategoryModel
import kotlinx.android.synthetic.main.item_layout_store_cat.view.*

class StoreCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setStoreCategoryData(categoryList : StoreCategoryModel){
        itemView.apply {
            tvStore_cate.text = categoryList.category_name
            Glide.with(ivStore_cate).load(categoryList.category_img_url).into(ivStore_cate)
        }
    }
}