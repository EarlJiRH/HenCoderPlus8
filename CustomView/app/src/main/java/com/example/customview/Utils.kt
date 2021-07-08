package com.example.customview

import android.content.Context
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

fun getAvatar(context: Context, width: Float): Bitmap = getAvatar(context, width.toInt())

fun getAvatar(context: Context, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    //快速读取bitmap的尺寸
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(context.resources, R.drawable.avatar_rengwuxian, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(context.resources, R.drawable.avatar_rengwuxian, options)
}