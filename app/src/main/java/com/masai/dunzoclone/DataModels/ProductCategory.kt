package com.masai.dunzoclone.DataModels

class ProductCategory(
    var image: String = "",
    val name: String ="",
    val storeName:String = "",
    var specificProductCat: ArrayList<ProductModel> = ArrayList<ProductModel>()
) {
}