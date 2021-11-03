package com.masai.dunzoclone.DataModels

class StoreCategoryModel {
    var category_img_url : String
    val category_name : String



    constructor(
        image_url: String, product_name: String) {
        category_img_url = image_url
        category_name = product_name
    }
}