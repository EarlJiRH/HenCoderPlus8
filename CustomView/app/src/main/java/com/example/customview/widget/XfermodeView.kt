package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp

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
        150f.dp,
        50f.dp,
        300f.dp,
        250f.dp
    )
    private val mode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

    private val circleBitmap = Bitmap.createBitmap(
        150.dp.toInt(),
        150.dp.toInt(),
        Bitmap.Config.ARGB_8888
    )

    private val squareBitmap = Bitmap.createBitmap(
        150.dp.toInt(),
        150.dp.toInt(),
        Bitmap.Config.ARGB_8888
    )

    init {

        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(
            50f.dp,
            0f.dp,
            150f.dp,
            100f.dp,
            paint
        )

        canvas.setBitmap(squareBitmap)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawRect(
            0f.dp,
            50f.dp,
            100f.dp,
            150f.dp,
            paint
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //bounds 离屏缓冲的范围
        val count = canvas.saveLayer(bounds, null)

        canvas.drawBitmap(
            circleBitmap,
            150f.dp,
            50f.dp,
            paint
        )

        paint.xfermode = mode

        canvas.drawBitmap(
            squareBitmap,
            150f.dp,
            50f.dp,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(count)
    }

}