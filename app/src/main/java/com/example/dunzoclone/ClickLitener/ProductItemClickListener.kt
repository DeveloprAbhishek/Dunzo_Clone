package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.DataModels.ProductModel

interface ProductItemClickListener {
    fun onItemClickListener(productModel: ProductModel, storePosition: Int)
    fun onAddButtonClick(productModel: ProductModel, storePosition: Int)
    fun onPlusButtonClick(productModel: ProductModel, storePosition: Int)
    fun onMinusButtonClick(productModel: ProductModel, storePosition: Int)

}