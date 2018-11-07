package com.simx.dailytest.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.simx.dailytest.GlideApp
import com.simx.dailytest.R

class BindAdapter {
    companion object {
        @BindingAdapter("bind:image_url") @JvmStatic
        fun loadImage(imageView: ImageView, url:String){
            GlideApp.with(imageView.context).load(url).error(R.drawable.placeholder).centerCrop().into(imageView)

        }
        @BindingAdapter("bind:image_assets") @JvmStatic
        fun loadImage(imageView: ImageView, url:Int){
            GlideApp.with(imageView.context).load(url).error(R.drawable.placeholder).centerCrop().into(imageView)
        }
    }
}