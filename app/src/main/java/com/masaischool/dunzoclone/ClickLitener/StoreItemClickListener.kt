package com.masaischool.dunzoclone.ClickLitener

import com.masaischool.dunzoclone.DataModels.Store

interface StoreItemClickListener {
    fun onItemClickListener(storeListModel: Store, storePosition: Int);
}