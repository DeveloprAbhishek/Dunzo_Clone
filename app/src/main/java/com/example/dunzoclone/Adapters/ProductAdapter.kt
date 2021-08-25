package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.ClickLitener.ProductItemClickListener
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.Product_ViewHolder

class ProductAdapter(private val productList: List<ProductModel>, var productItemClickListener: ProductItemClickListener) : RecyclerView.Adapter<Product_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Product_ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        return Product_ViewHolder(view, productItemClickListener)
    }

    override fun onBindViewHolder(holder: Product_ViewHolder, position: Int) {
        val product : ProductModel = productList[position]
        holder.setProductData(product, position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}