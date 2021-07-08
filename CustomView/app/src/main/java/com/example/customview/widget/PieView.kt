package com.example.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * ================================================
 * 类名：com.example.retrofit
 * 时间：2021/7/6 13:19
 * 描述：自定义饼图效果控件 class01
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val radius = 150f.dp //饼图半径

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //角度
    private val angles = floatArrayOf(60f, 90f, 150f, 60f)

    //颜色
    private val colors = listOf(
        Color.parseColor("#C2185B"),
        Color.parseColor("#00ACC1"),
        Color.parseColor("#55882F"),
        Color.parseColor("#5D4037")
    )

    //偏移距离
    private val offsetLength = 20f.dp

    private val offsetIndex = 2

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        for ((index, angle) in angles.withIndex()) {
            paint.color = colors[index]
            if (index == offsetIndex) {
                canvas.save()
                canvas.translate(
                    offsetLength * cos(Math.toRadians((startAngle + angle / 2f).toDouble())).toFloat(),
                    offsetLength * sin(Math.toRadians((startAngle + angle / 2f).toDouble())).toFloat()
                )
            }

            canvas.drawArc(
                width / 2f - radius,
                height / 2f - radius,
                width / 2f + radius,
                height / 2f + radius,
                startAngle,
                angle,//扫过的角度
                true,
                paint
            )
            startAngle += angle
            if (index == offsetIndex) {
                canvas.restore()
            }
        }


    }


}