package com.masaischool.dunzoclone.DataModels

import com.masaischool.dunzoclone.Activities.StoresActivity

class Store(
    var store_name: String = "",
    var store_id: String = "",
    var address: String = "",
    var store_image: String = "",
    var categories: List<Categories> = ArrayList<Categories>()
) {

}