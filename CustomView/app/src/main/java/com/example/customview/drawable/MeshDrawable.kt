package com.example.customview.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import com.example.customview.dp

/**
 * ================================================
 * 类名：com.example.customview.drawable
 * 时间：2021/7/9 10:13
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MeshDrawable : Drawable() {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//        color= Color.parseColor("#F9A825")
        color = "#F9A825".toColorInt()
        strokeWidth = 5.dp
    }

    //网格的间隔
    private val interval = 50.dp





    override fun draw(canvas: Canvas) {
        //最左边的线 竖线
//        canvas.drawLine(
//            bounds.left.toFloat(),
//            bounds.top.toFloat(),
//            bounds.left.toFloat(),
//            bounds.bottom.toFloat(),
//            paint
//        )
        var startX = bounds.left.toFloat()
        while (startX <= bounds.right) {
            canvas.drawLine(
                startX,
                bounds.top.toFloat(),
                startX,
                bounds.bottom.toFloat(),
                paint
            )
            startX += interval
        }

        var startY = bounds.top.toFloat()
        while (startY <= bounds.bottom) {
            canvas.drawLine(
                bounds.left.toFloat(),
                startY,
                bounds.right.toFloat(),
                startY,
                paint
            )
            startY += interval
        }
    }

    override fun setAlpha(alpha: Int) {
        //设置Drawable的透明度
        paint.alpha = alpha
    }

    override fun getAlpha() = paint.alpha

    override fun getOpacity(): Int {
        //不透明 半透明...
        return when (paint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
    }


    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? = paint.colorFilter
}