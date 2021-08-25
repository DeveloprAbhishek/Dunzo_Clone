//package com.example.dunzoclone.Activities
//
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dunzoclone.DataModels.Product_Model
//import com.example.dunzoclone.R
//
//class SviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var imageView: ImageView
//    var product_name: TextView
//    var desc: TextView
//    var quantity: TextView
//    var price: TextView
//    var searchView: TextView
//    fun setData(product_model: Product_Model) {
//        product_name.text = product_model.product_name
//        desc.text = product_model.product_desc
//        quantity.text = product_model.quantity.toString() + ""
//        price.text = "₹ " + product_model.price
//    }
//
//    init {
//        imageView = itemView.findViewById(R.id.ivProductImage)
//        searchView = itemView.findViewById(R.id.svSearch)
//        product_name = itemView.findViewById(R.id.tvProductName)
//        desc = itemView.findViewById(R.id.tvProductDesc)
//        TextView = itemView.findViewById(R.id.tvQuantitySelector)
//        price = itemView.findViewById(R.id.tvProductPrice)
//    }
//}