package com.example.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import com.example.customview.drawable.MeshDrawable

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/9 9:56
 * 描述：Bitmap和Drawable class06
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class DrawableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val drawable = MeshDrawable()
//    ColorDrawable(Color.RED)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //设置Drawable的绘制范围 默认(0,0)
        drawable.setBounds(50.dp.toInt(),80.dp.toInt(),width,height)
        drawable.draw(canvas)
    }
}