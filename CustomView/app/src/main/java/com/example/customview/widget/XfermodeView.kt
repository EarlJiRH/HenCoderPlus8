package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.px

/**
 * ================================================
 * 类名：com.example.retrofit
 * 时间：2021/7/6 14:41
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bounds = RectF(
        150f.px,
        50f.px,
        300f.px,
        250f.px
    )
    private val mode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

    private val circleBitmap = Bitmap.createBitmap(
        150.px,
        150.px,
        Bitmap.Config.ARGB_8888
    )
    private val squareBitmap = Bitmap.createBitmap(
        150.px,
        150.px,
        Bitmap.Config.ARGB_8888
    )

    init {

        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(
            50f.px,
            0f.px,
            150f.px,
            100f.px,
            paint
        )

        canvas.setBitmap(squareBitmap)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(
            0f.px,
            50f.px,
            100f.px,
            150f.px,
            paint
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //bounds 离屏缓冲的范围
        val count = canvas.saveLayer(bounds, null)

        canvas.drawBitmap(
            circleBitmap,
            150f.px,
            50f.px,
            paint
        )

        paint.xfermode = mode

        canvas.drawBitmap(
            squareBitmap,
            150f.px,
            50f.px,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(count)
    }

}