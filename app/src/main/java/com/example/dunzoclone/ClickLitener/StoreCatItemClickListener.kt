package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.Categories
import com.example.dunzoclone.DataModels.ProductCategory

interface StoreCatItemClickListener {
    fun onItemClickListener(productCategory: Categories ,storePosition: Int);
}