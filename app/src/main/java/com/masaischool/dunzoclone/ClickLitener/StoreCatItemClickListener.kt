package com.masaischool.dunzoclone.ClickLitener

import com.masaischool.dunzoclone.DataModels.Categories
import com.masaischool.dunzoclone.DataModels.ProductCategory

interface StoreCatItemClickListener {
    fun onItemClickListener(productCategory: Categories ,storePosition: Int);
}