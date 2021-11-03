package com.masai.dunzoclone.ClickLitener

import com.masai.dunzoclone.DataModels.Products

interface ProductItemClickListener {
    fun onItemClickListener(productModel: Products, storePosition: Int)
    fun onAddButtonClick(productModel: Products, storePosition: Int)
    fun onPlusButtonClick(productModel: Products, storePosition: Int)
    fun onMinusButtonClick(productModel: Products, storePosition: Int)

}