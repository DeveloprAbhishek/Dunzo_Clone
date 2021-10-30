package com.masaischool.dunzoclone.DataModels

class Product_Model {
    var image_url : String
    val product_name : String
    var product_desc : String
    var quantity : String
    var price : String


    constructor(
        image_url: String,
        product_name: String,
        product_desc: String,
        quantity: String,
        price: String
    ) {
        this.image_url = image_url
        this.product_name = product_name
        this.product_desc = product_desc
        this.quantity = quantity
        this.price = price
    }
}