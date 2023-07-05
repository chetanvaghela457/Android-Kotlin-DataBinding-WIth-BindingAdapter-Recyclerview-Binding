package com.databinding.databindingwithbindingadapterrecyclerviewbinding

import android.app.Application

// application class
class AppController : Application() {

    //set context, initializes firebase & universal image loader
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        registerActivityLifecycleCallbacks(AllActivityClass(this))
    }

    companion object {
        private var mInstance: AppController? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): AppController {
            return mInstance ?: AppController().also { mInstance = it }
        }
    }
}