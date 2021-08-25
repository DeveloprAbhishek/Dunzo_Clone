package com.example.dunzoclone.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

import com.example.dunzoclone.Fragments.HomeFragment
import com.example.dunzoclone.R
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.dunzoclone.Fragments.DunzoCashFragment
import com.example.dunzoclone.Fragments.OrdersFragment
import com.example.dunzoclone.Fragments.SearchFragment
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        menu_bottom.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> temp = HomeFragment()
                R.id.menu_search -> temp = SearchFragment()
                R.id.menu_orders -> temp = OrdersFragment()
                R.id.menu_dunzo_cash -> temp = DunzoCashFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp).commit()
            }
            true
        })

    }
}