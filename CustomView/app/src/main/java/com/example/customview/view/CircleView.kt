package com.example.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import kotlin.math.min

/**
 * ================================================
 * 类名：com.example.customview.view
 * 时间：2021/7/9 17:34
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */

class CircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var radius = 100.dp

    private val padding = 100.dp


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = ((padding + radius) * 2).toInt()

//        val spaceWidthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val spaceWidthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val width = when (spaceWidthMode) {
//            MeasureSpec.EXACTLY -> spaceWidthSize //确定值
//            MeasureSpec.AT_MOST -> if (size > spaceWidthSize) spaceWidthSize else size  //上限值
//            MeasureSpec.UNSPECIFIED -> size//不限制
//            else -> size
//        }
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(padding + radius, padding + radius, radius, paint)
    }

}