package com.example.jetpackdemo.adapter

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.jetpackdemo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(imageView: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int){
    val resources = textView.context.resources

    val text = resources.getQuantityString(R.plurals.watering_needs_suffix, wateringInterval, wateringInterval)

    textView.text = text
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(textView: TextView, description: String?){
    if (description != null) {
        textView.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        textView.movementMethod = LinkMovementMethod.getInstance()
    }else{
        textView.text = ""
    }
}

@BindingAdapter("isGone")
fun bindIsGone(floatingActionButton: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        floatingActionButton.hide()
    }else {
        floatingActionButton.show()
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean?) {
    view.visibility = if (isGone == null || isGone) {
        View.GONE
    }else {
        View.VISIBLE
    }
}