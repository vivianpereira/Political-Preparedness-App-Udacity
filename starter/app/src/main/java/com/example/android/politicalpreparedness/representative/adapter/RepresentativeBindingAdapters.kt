package com.example.android.politicalpreparedness.representative.adapter

import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.android.politicalpreparedness.R

@BindingAdapter("profileImage")
fun fetchImage(view: ImageView, src: String?) {
    src?.let {
        val uri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(uri)
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .transform(CenterCrop(), CircleCrop())
            .into(view)
    }
}

@BindingAdapter("stateValue")
fun Spinner.setNewValue(value: String?) {
    this.adapter?.let {
        val adapter = toTypedAdapter<String>(this.adapter as ArrayAdapter<*>)
        val position = when (adapter.getItem(0)) {
            is String -> adapter.getPosition(value)
            else -> this.selectedItemPosition
        }
        if (position >= 0) {
            setSelection(position)
        }
    }
}

inline fun <reified T> toTypedAdapter(adapter: ArrayAdapter<*>): ArrayAdapter<T> {
    return adapter as ArrayAdapter<T>
}
