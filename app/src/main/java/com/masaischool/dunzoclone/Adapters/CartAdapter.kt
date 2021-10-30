package com.masaischool.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masaischool.dunzoclone.DataModels.CartModel
import com.masaischool.dunzoclone.DataModels.ProductModel
import com.masaischool.dunzoclone.DataModels.Products
import com.masaischool.dunzoclone.R
import com.masaischool.dunzoclone.ViewHolders.CartViewHolder

class CartAdapter(private val cartList: List<Products>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_cart_order, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    val cartModel : Products = cartList[position]
        holder.setCartData(cartModel, position)
    }

    override fun getItemCount(): Int {
    return cartList.size
    }

}