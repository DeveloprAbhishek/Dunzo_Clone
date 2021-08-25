package com.example.dunzoclone.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.CartModel
import kotlinx.android.synthetic.main.item_layout_cart_order.view.*

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setCartData(cartList: CartModel){
        itemView.apply {
            tvCartItemPrise.text = cartList.item_price
            tvCartItemWeight.text = cartList.item_weight
            tvCartitemName.text = cartList.item_name
            tvCartItemQty.text = cartList.item_quantity
        }
    }

}