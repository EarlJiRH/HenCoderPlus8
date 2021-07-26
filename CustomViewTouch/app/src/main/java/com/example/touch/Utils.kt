package com.example.touch

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * ================================================
 * 类名：com.example.customview
 * 时间：2021/7/7 11:33
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */

fun getAvatar(res: Resources, width: Float): Bitmap = getAvatar(res, width.toInt())

fun getAvatar(res: Resources, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    //快速读取bitmap的尺寸
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res, R.drawable.avatar_rengwuxian, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(res, R.drawable.avatar_rengwuxian, options)
}