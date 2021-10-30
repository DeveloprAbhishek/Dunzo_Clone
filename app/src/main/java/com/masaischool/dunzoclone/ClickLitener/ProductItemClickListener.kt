package com.masaischool.dunzoclone.ClickLitener

import com.masaischool.dunzoclone.DataModels.ProductCategory
import com.masaischool.dunzoclone.DataModels.ProductModel
import com.masaischool.dunzoclone.DataModels.Products

interface ProductItemClickListener {
    fun onItemClickListener(productModel: Products, storePosition: Int)
    fun onAddButtonClick(productModel: Products, storePosition: Int)
    fun onPlusButtonClick(productModel: Products, storePosition: Int)
    fun onMinusButtonClick(productModel: Products, storePosition: Int)

}