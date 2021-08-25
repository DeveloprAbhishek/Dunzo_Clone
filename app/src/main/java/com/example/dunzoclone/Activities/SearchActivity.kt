//package com.example.dunzoclone.Activities
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.SearchView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dunzoclone.DataModels.Product_Model
//import com.example.dunzoclone.R
//import java.util.ArrayList
//
//class SearchActivity : AppCompatActivity() {
//    var recyclerView: RecyclerView? = null
//    var adapter: S_Adapter? = null
//    var searchView: SearchView? = null
//    var dataList = ArrayList<Product_Model>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search)
//        buildData()
//        setRecycler()
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                adapter!!.getFilter().filter(newText)
//                return false
//            }
//        })
//    }
//
//    private fun setRecycler() {
//        val layoutManager = LinearLayoutManager(this)
//        adapter = S_Adapter(dataList, this)
//        recyclerView!!.adapter = adapter
//        recyclerView!!.layoutManager = layoutManager
//    }
//
//    private fun buildData() {
//        val model1 = Product_Model("", "Tomato", "1 KG Tomato Zomato", 1, 100)
//        dataList.add(model1)
//        val model2 = Product_Model("", "Potato", "1 KG Potato", 2, 100)
//        dataList.add(model2)
//        val model3 = Product_Model("", "Chilli", "1 KG Chilli", 3, 100)
//        dataList.add(model3)
//    }
//}