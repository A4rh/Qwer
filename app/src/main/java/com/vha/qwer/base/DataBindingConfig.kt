package com.vha.qwer.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel

class DataBindingConfig(
    val layout: Int,
    val vmVariableId: Int,
    val stateViewModel: ViewModel?
) {

    val bindingParams = SparseArray<Any>()

    fun addBindingParam(variableId: Int, `object`: Any): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, `object`)
        }
        return this
    }
}