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
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics //Resources.getSystem() 获取系统相关配置
    )

val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics //Resources.getSystem() 获取系统相关配置
    )

val Int.dp
    get() = this.toFloat().dp



