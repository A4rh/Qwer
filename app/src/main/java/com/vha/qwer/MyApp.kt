package com.vha.qwer

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class MyApp: Application(), ViewModelStoreOwner {

    private val viewModelStore: ViewModelStore = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
        app = applicationContext
        mAppViewModelStoreOwner = this

    }

    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }

    companion object {

        @JvmStatic
        private var app: Context? = null

        private var mAppViewModelStoreOwner: ViewModelStoreOwner? = null

        @JvmStatic
        fun getContext(): Context {
            return app!!
        }

        @JvmStatic
        fun getViewModelOwner(): ViewModelStoreOwner {
            return mAppViewModelStoreOwner as ViewModelStoreOwner
        }
    }
}