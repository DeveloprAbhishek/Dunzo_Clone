package com.masai.dunzoclone.ClickLitener

import com.masai.dunzoclone.DataModels.Store

interface StoreItemClickListener {
    fun onItemClickListener(storeListModel: Store, storePosition: Int);
}