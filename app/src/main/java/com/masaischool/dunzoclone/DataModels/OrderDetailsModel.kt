package com.masaischool.dunzoclone.DataModels

class OrderDetailsModel {
    var product_quantity: String
    var image_url: String
    var product_name : String
    var product_quantity_in_gms: String
    var price : String

    constructor(
        product_quantity: String,
        image_url: String,
        product_name: String,
        product_quantity_in_gms: String,
        price: String
    ) {
        this.product_quantity = product_quantity
        this.image_url = image_url
        this.product_name = product_name
        this.product_quantity_in_gms = product_quantity_in_gms
        this.price = price
    }
}