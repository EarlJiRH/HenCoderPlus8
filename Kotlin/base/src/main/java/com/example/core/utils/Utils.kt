@file:JvmName("Utils")

package com.example.core.utils

import android.content.res.Resources
import android.text.Editable
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import com.example.core.BaseApplication


private val displayMetrics = Resources.getSystem().displayMetrics

//扩展函数定义
fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}


@JvmOverloads
fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.currentApplication, string, duration).show()
}


fun TextView.setText(string: String?) = this.apply {
    text = Editable.Factory.getInstance().newEditable(string)
}