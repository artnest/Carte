package by.carte.restaurants.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ViewGroup.inflate(@LayoutRes resId: Int) =
        LayoutInflater.from(this.context).inflate(resId, this, false)!!

fun ImageView.loadImage(imageUrl: String) = Glide.with(this.context)
        .load(imageUrl)
        .asBitmap()
        //.centerCrop()
        .into(this)
