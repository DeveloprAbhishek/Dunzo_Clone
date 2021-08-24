//package com.example.dunzoclone.Adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dunzoclone.DataModels.Product_Model
//import com.example.dunzoclone.DataModels.Store_list_Model
//import com.example.dunzoclone.R
//import com.example.dunzoclone.ViewHolders.Product_ViewHolder
//import com.example.dunzoclone.ViewHolders.StoreViewHolder
//
//class ProductAdapter(private val productList: List<Product_Model>) : RecyclerView.Adapter<Product_ViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Product_ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
//        return Product_ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: Product_ViewHolder, position: Int) {
//        val product : Product_Model = productList[position]
//        holder.setProductData(product)
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//}