package com.example.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import com.example.customview.getAvatar

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/7 11:21
 * 描述：图文混排效果 class03
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MultilineTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    //lorem ipsum
    private val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique urna tincidunt maximus viverra. Maecenas commodo pellentesque dolor ultrices porttitor. Vestibulum in arcu rhoncus, maximus ligula vel, consequat sem. Maecenas a quam libero. Praesent hendrerit ex lacus, ac feugiat nibh interdum et. Vestibulum in gravida neque. Morbi maximus scelerisque odio, vel pellentesque purus ultrices quis. Praesent eu turpis et metus venenatis maximus blandit sed magna. Sed imperdiet est semper urna laoreet congue. Praesent mattis magna sed est accumsan posuere. Morbi lobortis fermentum fringilla. Fusce sed ex tempus, venenatis odio ac, tempor metus."

    //bitmap绘制区域的宽度
    private val imageSize = 150.dp

    //bitmap起始绘制位置的y轴坐标
    private val imagePadding = 50.dp
    private val bitmap = getAvatar(context, imageSize)

    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val fontMetrics = Paint.FontMetrics()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        val staticLayout = StaticLayout(
//            text,
//            textPaint,
//            width,
//            Layout.Alignment.ALIGN_NORMAL,
//            1f,
//            0f,
//            false
//        )
//        staticLayout.draw(canvas)

        canvas.drawBitmap(
            bitmap,
            width - imageSize,
            imagePadding,
            paint
        )


        //第一行绘制
        paint.getFontMetrics(fontMetrics)
//        val measuredWidth = floatArrayOf(0f)
//        var count = paint.breakText(
//            text,
//            true,
//            width.toFloat(),
//            measuredWidth
//        )
//        canvas.drawText(
//            text,
//            0,
//            count,//结束位置
//            0f,
//            -fontMetrics.top,
//            paint
//        )

        //第二行绘制
//        val oldCount = count
//        count = paint.breakText(
//            text,
//            count,
//            text.length,
//            true,
//            width.toFloat(),
//            measuredWidth
//        )
//        canvas.drawText(
//            text,
//            oldCount,
//            oldCount + count,
//            0f,
//            -fontMetrics.top + paint.fontSpacing,//行间距 第一行底部到第二行底部的距离
//            paint
//        )

        val measuredWidth = floatArrayOf(0f)
        //起始值
        var start = 0
        //每行写入的个数
        var count: Int
        //起始的y轴坐标位置
        var verticalOffset = -fontMetrics.top
        var maxWidth: Float

        //起始位置条件小于text的文本长度 才进行绘制操作
        while (start < text.length) {
            maxWidth = if (verticalOffset + fontMetrics.bottom < imagePadding ||
                verticalOffset + fontMetrics.top > imagePadding + imageSize
            ) {
                width.toFloat()
            } else {
                width.toFloat() - imageSize
            }
            count = paint.breakText(
                text,
                start,//绘制的每次的起始位置
                text.length,
                true,
                maxWidth,
                measuredWidth
            )
            canvas.drawText(
                text,
                start,
                start + count,
                0f,
                verticalOffset,
                paint
            )
            start += count
            verticalOffset += paint.fontSpacing

        }
    }

}