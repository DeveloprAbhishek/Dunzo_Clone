package com.example.dunzoclone.DataModels

class Product_Model {
    var image_url : String
    val product_name : String
    var product_desc : String
    var quantity : Int
    var price : Int


    constructor(
        image_url: String,
        product_name: String,
        product_desc: String,
        quantity: Int,
        price: Int
    ) {
        this.image_url = image_url
        this.product_name = product_name
        this.product_desc = product_desc
        this.quantity = quantity
        this.price = price
    }
}