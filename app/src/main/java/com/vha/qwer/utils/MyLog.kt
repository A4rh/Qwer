package com.vha.qwer.utils

import android.util.Log

object MyLog {

    private const val TAG = "Qwe"

    private val fileLineMethod: String
    get() {
        val thread = Thread.currentThread()
        val traceElement = thread.stackTrace[4]
        return "[${thread.name}](${traceElement.fileName}:${traceElement.lineNumber})"
    }

    @JvmStatic
    fun i(msg: String) {
        Log.i(TAG, "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        Log.i("${TAG}_${tag}", "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun w(msg: String) {
        Log.w(TAG, "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        Log.w("${TAG}_${tag}", "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun d(msg: String) {
        Log.d(TAG, "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        Log.d("${TAG}_${tag}", "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun e(msg: String) {
        Log.e(TAG, "${fileLineMethod}${msg}")
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        Log.e("${TAG}_${tag}", "${fileLineMethod}${msg}")
    }
}