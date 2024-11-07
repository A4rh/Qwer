package com.vha.qwer.utils

import android.graphics.Bitmap

object BitmapUtils {

    @JvmStatic
    fun cropBitmap(source: Bitmap?): Bitmap? {
        if (source == null) {
            return null
        }
        val startX = 0
        val startY = 0
        val width = source.width
        val height = source.height
        return Bitmap.createBitmap(source, startX, startY, width, height)
    }
}