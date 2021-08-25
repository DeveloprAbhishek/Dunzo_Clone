package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.ProductCategory

interface StoreCatItemClickListener {
    fun onItemClickListener(productCategory: ProductCategory ,storePosition: Int);
}