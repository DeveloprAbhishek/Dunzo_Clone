package com.masaischool.dunzoclone.DataModels

class Order_Model {
    var Order_image_url : String
    val product_category : String
    var order_date : String
    var order_time : String
    var store_name : String
    var store_address : String
    var customer_address : String
    var customer_sub_address : String
    var product_quantity : String
    var order_price : String

    constructor(
        Order_image_url: String,
        product_category: String,
        order_date: String,
        order_time: String,
        store_name: String,
        store_address: String,
        customer_address: String,
        customer_sub_address: String,
        product_quantity: String,
        order_price: String
    ) {
        this.Order_image_url = Order_image_url
        this.product_category = product_category
        this.order_date = order_date
        this.order_time = order_time
        this.store_name = store_name
        this.store_address = store_address
        this.customer_address = customer_address
        this.customer_sub_address = customer_sub_address
        this.product_quantity = product_quantity
        this.order_price = order_price
    }
}