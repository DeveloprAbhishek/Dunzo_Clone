package com.example.dunzoclone.Fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.SearchAdapter
import com.example.dunzoclone.DataModels.SearchModel
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    var dataList = ArrayList<SearchModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val model1 = SearchModel("", "Strawberry", "1 KG Strawberry rkpsx7", "1", "160")
        dataList.add(model1)
        val model2 = SearchModel("", "Potato", "1 KG Potato", "2", "100")
        dataList.add(model2)
        val model3 = SearchModel("", "Chilli", "1 KG Chilli", "3", "200")
        dataList.add(model3)
        val model4 = SearchModel("", "Banana", "1 KG Banana", "3", "200")
        dataList.add(model4)
        val model5 = SearchModel("", "Apple", "Apple", "3", "200")
        dataList.add(model5)
        val model6 = SearchModel("", "Apple", "Kashmiri Apple", "3", "200")
        dataList.add(model6)

        val adapter = context?.let { SearchAdapter(dataList, it) }
        searchRecyclerView.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = adapter


        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText)
                return true
            }

        })
    }
}