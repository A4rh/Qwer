package com.vha.qwer.utils

import android.widget.Toast
import androidx.annotation.StringRes
import com.vha.qwer.MyApp

object MyToast {

    @JvmStatic
    fun toast(message: String) {
        Toast.makeText(MyApp.getContext(), message, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun toast(@StringRes stringRes: Int) {
        val context = MyApp.getContext()
        Toast.makeText(context, context.getString(stringRes), Toast.LENGTH_SHORT).show()
    }
}