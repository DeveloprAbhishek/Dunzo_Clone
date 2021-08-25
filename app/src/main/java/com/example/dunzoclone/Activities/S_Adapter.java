package com.example.dunzoclone.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dunzoclone.DataModels.Product_Model;
import com.example.dunzoclone.R;

import java.util.ArrayList;

public class S_Adapter extends RecyclerView.Adapter<SviewHolder> implements Filterable {
    ArrayList<Product_Model> dataList = new ArrayList<>();
    ArrayList<Product_Model> backupList;
    Context context;

    public S_Adapter(ArrayList<Product_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        backupList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public SviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
        return new SviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SviewHolder holder, int position) {
        Product_Model item = dataList.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Product_Model> filteredData = new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filteredData.addAll(backupList);
            } else {
                for (Product_Model obj : backupList) {
                    if (obj.getProduct_name().toString().toLowerCase().contains(keyword.toString().toLowerCase())
                            || obj.getProduct_desc().toString().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filteredData.add(obj);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((ArrayList<Product_Model>)results.values);
            notifyDataSetChanged();
        }
    };
}
