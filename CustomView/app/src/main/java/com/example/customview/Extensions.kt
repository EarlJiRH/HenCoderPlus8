package com.example.customview

import android.content.res.Resources
import android.util.TypedValue

/**
 * ================================================
 * 类名：com.example.retrofit
 * 时间：2021/7/5 17:08
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */

//扩展属性
val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics //Resources.getSystem() 获取系统相关配置
    )

val Int.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics //Resources.getSystem() 获取系统相关配置
    ).toInt()