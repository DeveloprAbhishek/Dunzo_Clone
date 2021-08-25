package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.DataModels.Order_Model
import kotlinx.android.synthetic.main.order_item_layout.view.*

class OrderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    fun setOrderData(orderData: Order_Model) {
        itemView.apply {
            Glide.with(ivImageProduct).load(orderData.Order_image_url).into(ivImageProduct)

            tvCategoryNameOrder.text = orderData.product_category
            tvOrderDate.text = orderData.order_date
            tvOrderTime.text = orderData.order_time
            tvStoreNameOrder.text = orderData.store_name
            tvStoreAddressOrder.text = orderData.store_address
            tvCustomerAddressOrder.text = orderData.customer_address
            tvCustomerSubAddressOrder.text = orderData.customer_sub_address
            tvProductQuantityOrder.text = orderData.product_quantity
            tvPriceOrder.text = orderData.order_price
        }
    }
}