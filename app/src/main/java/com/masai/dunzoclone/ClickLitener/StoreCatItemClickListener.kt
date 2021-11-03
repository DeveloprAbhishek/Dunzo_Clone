package com.masai.dunzoclone.ClickLitener

import com.masai.dunzoclone.DataModels.Categories

interface StoreCatItemClickListener {
    fun onItemClickListener(productCategory: Categories ,storePosition: Int);
}