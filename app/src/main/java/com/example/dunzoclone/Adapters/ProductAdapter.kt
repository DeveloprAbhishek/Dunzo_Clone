package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.ClickLitener.ProductItemClickListener
import com.example.dunzoclone.DataModels.Products
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.ProductViewHolder

class ProductAdapter(
    private val productList: List<Products>,
    var productItemClickListener: ProductItemClickListener,
    var cartProductId: HashMap<String, String>
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        return ProductViewHolder(view, productItemClickListener, cartProductId)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product : Products = productList[position]
        holder.setProductData(product, position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}