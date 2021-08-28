package com.example.dunzoclone.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dunzoclone.Activities.AdminActivity
import com.example.dunzoclone.Activities.EmptyCartActivity
import com.example.dunzoclone.Activities.LocationActivity
import com.example.dunzoclone.Activities.StoresActivity
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.vf1))
        imageList.add(SlideModel(R.drawable.vf2))
        imageList.add(SlideModel(R.drawable.vf3))
        imageList.add(SlideModel(R.drawable.vf4))

        imageSlider.setImageList(imageList,ScaleTypes.FIT)
    }


    private fun initViews() {
        //Toolbar Views
        ivToolbarProfile.setOnClickListener(this)
        ivToolbarCart.setOnClickListener(this)
        tvToolbarLocation.setOnClickListener(this)


        //Categories Views
        ivFruits.setOnClickListener(this)
        ivGroceries.setOnClickListener(this)
        ivMeat.setOnClickListener(this)
        ivPickup.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivFruits -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "fruits")
                startActivity(intent)
            }
            R.id.ivGroceries -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "groceries")
                startActivity(intent)
            }
            R.id.ivMeat -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "meat")
                startActivity(intent)
            }
            R.id.ivPickup -> {
                Toast.makeText(context, "medicines", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.ivToolbarProfile -> {
                startActivity(Intent(context, AdminActivity::class.java))
            }
            R.id.ivToolbarCart -> {
                startActivity(Intent(context, EmptyCartActivity::class.java))
            }
            R.id.tvToolbarLocation -> {
                startActivity(Intent(context, LocationActivity::class.java))
            }
        }
    }


//     fun show_vf_images() {
//
//         view_Flipper.flipInterval = 3000
//         view_Flipper.isAutoStart = true
//         view_Flipper.startFlipping()
//         view_Flipper.setInAnimation(context,R.anim.flip_in)
//         view_Flipper.setOutAnimation(context,R.anim.flip_out)
//    }
}