package com.example.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.customview.R
import com.example.customview.dp

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/7 10:12
 * 描述：文字的测量 居中效果 class03
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val circleColor = Color.parseColor("#90A4AE")
    private val highLightColor = Color.parseColor("#EF4081")

    private val ringWidth = 20.dp
    private val radius = 150.dp

    private val startAngle = -90f

    private val sweepAngle = 225f

    private val text = "abap"

    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        //查看型使用dp
        textSize = 100.dp
        //设置字体
        typeface = ResourcesCompat.getFont(context, R.font.font)
        //水平对齐方式 横向居中效果
        textAlign = Paint.Align.CENTER
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制环
        //空心效果
        paint.style = Paint.Style.STROKE
        paint.color = circleColor
        paint.strokeWidth = ringWidth
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)

        //绘制进度条
        //进度条颜色
        paint.color = highLightColor
        //进度条的两端是圆角效果
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - radius,
            height / 2f - radius,
            width / 2f + radius,
            height / 2f + radius,
            startAngle,
            sweepAngle,
            false,
            paint
        )


        //绘制文字
        //文字效果 填充满
        paint.textSize = 100.dp
        paint.style = Paint.Style.FILL
        //获取将要绘制的文字的边框属性
//        paint.getTextBounds(text, 0, text.length, bounds)
//        canvas.drawText(
//            text,
//            width / 2f,
//            height / 2f - (bounds.top + bounds.bottom) / 2f,//baseLine向下偏移 偏移量
//            paint
//        )
        //文字绘制 基准线baseLine 是相对View的中心线位置
        //所以文字会相对偏上方
        //文字整体向下偏移 1.获取文字的上下边框线 获取偏移量 适合静态的文字绘制 动态改变 文字会有跳动效果
        //                2.取上下边界 计算相对位置 实现相对居中 适合动态的文字绘制

        //top(顶部) ascent(核心部位的顶部-->与字体有关) baseline(基准线) descent(核心部位的底部) bottom(底部)
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(
            text,
            width / 2f,
            height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f,//核心部位的中点值 偏移量
            paint
        )

        //文字贴边效果 上顶贴边
//        paint.textSize = 100.dp
//        paint.textAlign = Paint.Align.LEFT
//        paint.getFontMetrics(fontMetrics)
//        canvas.drawText(
//            text,
//            0f,
//            0f - fontMetrics.top,
//            paint
//        )
//        paint.getTextBounds(text, 0, text.length, bounds)
//        canvas.drawText(
//            text,
//            0f - bounds.left,
//            0f - bounds.top,
//            paint
//        )


        //文字贴边效果 左顶贴边
//        paint.textSize = 15.dp
//        paint.getTextBounds(text, 0, text.length, bounds)
//        canvas.drawText(
//            text,
//            0f - bounds.left,
//            0f - bounds.top,
//            paint
//        )



    }

}