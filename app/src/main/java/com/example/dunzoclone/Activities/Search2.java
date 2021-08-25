package com.example.dunzoclone.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dunzoclone.DataModels.Product_Model;
import com.example.dunzoclone.R;

import java.util.ArrayList;

public class Search2 extends AppCompatActivity {
    RecyclerView recyclerView;
    S_Adapter adapter;
    SearchView searchView;
    ArrayList<Product_Model> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.searchRecyclerView);
        searchView = findViewById(R.id.svSearch);
        buildData();
        setRecycler();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new S_Adapter(dataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void buildData() {

        Product_Model model1 = new Product_Model("", "Tomato", "1 KG Tomato Zomato", 1, 100);
        dataList.add(model1);
        Product_Model model2 = new Product_Model("", "Potato", "1 KG Potato", 2, 100);
        dataList.add(model2);
        Product_Model model3 = new Product_Model("", "Chilli", "1 KG Chilli", 3, 100);
        dataList.add(model3);
    }
}