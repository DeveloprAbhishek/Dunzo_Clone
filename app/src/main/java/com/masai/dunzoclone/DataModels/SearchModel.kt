package com.masai.dunzoclone.DataModels

data class SearchModel(
    var image_url: String,
    val product_name: String,
    var product_desc: String,
    var quantity: String,
    var price: String
) { }