package com.example.dunzoclone

class BestSellerModel {
    var pro_image_url : String
    val pro_name : String
    var pro_quantity : String
    var pro_price : String


    constructor( image_url: String, product_name: String, quantity: String, price: String
    ) {
        pro_image_url = image_url
        pro_name = product_name
        pro_quantity = quantity
        pro_price = price
    }

}