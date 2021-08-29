package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.CartModel
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.item_layout_cart_order.view.*

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setCartData(cartList: Products, position :Int){
        itemView.apply {
            tvCartItemPrise.text = "₹"+cartList.price
            tvCartItemWeight.text = cartList.quantity
            tvCartitemName.text = cartList.name
        }
    }

}