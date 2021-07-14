package com.example.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.customview.drawable.CandleDrawable

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/9 11:41
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class SimpleCandleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val drawable = CandleDrawable()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制蜡烛图
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        //绘制额外的简单信息
    }
}