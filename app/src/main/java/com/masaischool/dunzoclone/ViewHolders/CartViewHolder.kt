package com.masaischool.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masaischool.dunzoclone.DataModels.CartModel
import com.masaischool.dunzoclone.DataModels.ProductModel
import com.masaischool.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.item_layout_cart_order.view.*

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setCartData(cartList: Products, position :Int){
        itemView.apply {
            tvCartItemPrise.text = "₹"+cartList.price
            tvCartItemWeight.text = cartList.quantity
            tvCartitemName.text = cartList.name
            tvCartItemQty.text = cartList.quantity
        }
    }

}