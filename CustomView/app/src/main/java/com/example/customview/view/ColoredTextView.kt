package com.example.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.customview.dp
import java.util.*

/**
 * ================================================
 * 类名：com.example.customview.view
 * 时间：2021/7/12 13:30
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class ColoredTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val textSizeList = intArrayOf(16, 22, 28) //px
    private val paintColorList = listOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#2496F3"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )


    private val cornerRadius = 4.dp
    private val xPadding = 16.dp.toInt()
    private val yPadding = 8.dp.toInt()

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val random = Random()

    init {
        setTextColor(Color.WHITE)
        textSize = textSizeList[random.nextInt(textSizeList.size)].toFloat()
        paint.color = paintColorList[random.nextInt(paintColorList.size)]
        setPadding(xPadding, yPadding, xPadding, yPadding)
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f, 0f, width.toFloat(), height.toFloat(),
            cornerRadius, cornerRadius, paint
        )
        super.onDraw(canvas)
    }
}