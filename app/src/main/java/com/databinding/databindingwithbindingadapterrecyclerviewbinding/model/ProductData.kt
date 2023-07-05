package com.databinding.databindingwithbindingadapterrecyclerviewbinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.BR

class ProductData: BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var price: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }

    @get:Bindable
    var valid: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }
}