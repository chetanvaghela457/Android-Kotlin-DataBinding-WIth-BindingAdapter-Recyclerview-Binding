package com.databinding.databindingwithbindingadapterrecyclerviewbinding.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.IOException

fun getJsonDataFromRaw(context: Context, fileName: Int): String {
    val jsonString: String
    try {
        jsonString =
            context.resources.openRawResource(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return "null"
    }
    return jsonString
}

fun <DATA> LiveData<DATA>.observeByLambda(
    lifecycleOwner: LifecycleOwner,
    observeFun: (DATA) -> Unit
) { observe(lifecycleOwner) { data ->
    data?.let { observeFun.invoke(it) }
}
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> viewModelFactory(crossinline function: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = function() as T
    }

