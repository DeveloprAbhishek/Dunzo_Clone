package com.example.dunzoclone.ViewHolders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dunzoclone.DataModels.Product_Model;
import com.example.dunzoclone.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView product_name;
    TextView desc;
    TextView quantity;
    TextView price;
    TextView searchView;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivProductImage);
        searchView = itemView.findViewById(R.id.svSearch);
        product_name = itemView.findViewById(R.id.tvProductName);
        desc = itemView.findViewById(R.id.tvProductDesc);
        quantity = itemView.findViewById(R.id.tvQuantitySelector);
        price = itemView.findViewById(R.id.tvProductPrice);
    }


    public void setData(Product_Model product_model) {
        product_name.setText(product_model.getProduct_name());
        desc.setText(product_model.getProduct_desc());
        quantity.setText(product_model.getQuantity()+"");
        price.setText("₹ "+product_model.getPrice());
    }
}