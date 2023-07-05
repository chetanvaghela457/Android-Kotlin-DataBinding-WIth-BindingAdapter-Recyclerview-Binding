package com.databinding.databindingwithbindingadapterrecyclerviewbinding.viewmodel

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.R
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.adapters.ProductsItemAdapter
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.model.ProductData

class ProductsViewModel(context: Context) : ViewModel() {




    fun onDataTextChanged(text: CharSequence,productData: ProductData) {

        if(text.isNotEmpty()) {
            productData.valid = !(text.toString().toInt() < 50)
        } else {
            productData.valid = true
        }
    }

    @BindingAdapter(
        "buttonAllValid",
        requireAll = true
    )
    fun saveButtonPriority(
        textView: TextView,
        isValid: Boolean,

        ) {
        textView.apply {
            when (isValid) {
                true -> {

                    setBackgroundResource(R.drawable.button_bg)
                    isEnabled = true

                }

                else -> {
                    setBackgroundResource(R.drawable.button_disable_bg)
                    isEnabled = false
                }
            }
        }
    }

}