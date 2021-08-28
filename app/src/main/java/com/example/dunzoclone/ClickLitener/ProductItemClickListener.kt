package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.DataModels.Products

interface ProductItemClickListener {
    fun onItemClickListener(productModel: Products, storePosition: Int)
    fun onAddButtonClick(productModel: Products, storePosition: Int)
    fun onPlusButtonClick(productModel: Products, storePosition: Int)
    fun onMinusButtonClick(productModel: Products, storePosition: Int)

}