package com.masaischool.dunzoclone.DataModels

class CartModel {

    var item_weight : String
    val item_name : String
    var item_quantity : String
    var item_price : String


    constructor(weight: String, name: String, quantity: String, price: String
    ) {
        item_weight = weight
        item_name = name
        item_quantity = quantity
        item_price = price
    }
}