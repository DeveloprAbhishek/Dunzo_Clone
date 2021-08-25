package com.example.dunzoclone.DataModels

class StoreListModel(
    var image: String = "",
    val store_name: String ="",
    var distance: String ="6.4km",
    var time: String ="37 mins",
    var address: String ="",
    var productCategory: ArrayList<ProductCategory> = ArrayList<ProductCategory>()
) {

}

