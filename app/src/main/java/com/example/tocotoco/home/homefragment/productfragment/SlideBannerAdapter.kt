package com.example.tocotoco.home.homefragment.productfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.tocotoco.R

class SlideBannerAdapter(val list: List<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return if (list.isNotEmpty()) list.size else 0
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_slide_banner, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val item = list[position]
        Glide.with(view.context).load(container.resources.getDrawable(item, null)).into(imageView)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}