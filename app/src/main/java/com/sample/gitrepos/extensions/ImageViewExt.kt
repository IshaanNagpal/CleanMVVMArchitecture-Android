package com.sample.gitrepos.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.gitrepos.R

fun ImageView.urImageCircular(imageSource: String, placeholder: Int) {
    loadCircularImage(imageSource, placeholder = placeholder)
}

private fun <T> ImageView.loadCircularImage(
    model: T,
    placeholder: Int
) {

    Glide.with(context)
        .load(model)
        .placeholder(placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}


@BindingAdapter(value = ["imageUrl", "defaultImage"], requireAll = false)
fun ImageView.loadImageFromUrl(imageSource: String?, default: Int?) {
    urImageCircular(imageSource.filterNull(), default ?: R.drawable.ic_launcher_foreground)
}