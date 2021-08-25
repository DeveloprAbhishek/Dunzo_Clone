package com.example.dunzoclone

class BestSellerModel {
    var item_img_url : String
    val item_name : String
    var item_quantity : String
    var item_price : String


    constructor( image_url: String, product_name: String, quantity: String, price: String
    ) {
        item_img_url = image_url
        item_name = product_name
        item_quantity = quantity
        item_price = price
    }

}