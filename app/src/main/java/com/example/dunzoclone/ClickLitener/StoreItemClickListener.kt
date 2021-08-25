package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.DataModels.StoreListModel
import java.util.ArrayList

interface StoreItemClickListener {
    fun onItemClickListener(storeListModel: StoreListModel, storePosition: Int);
}