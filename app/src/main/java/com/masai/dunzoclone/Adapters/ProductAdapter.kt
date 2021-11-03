package com.masai.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.dunzoclone.ClickLitener.ProductItemClickListener
import com.masai.dunzoclone.DataModels.Products
import com.masai.dunzoclone.R
import com.masai.dunzoclone.ViewHolders.ProductViewHolder

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