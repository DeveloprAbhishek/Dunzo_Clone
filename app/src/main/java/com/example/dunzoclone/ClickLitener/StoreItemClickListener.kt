package com.example.dunzoclone.ClickLitener

import com.example.dunzoclone.DataModels.Store

interface StoreItemClickListener {
    fun onItemClickListener(storeListModel: Store, storePosition: Int);
}