package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import com.example.customview.getAvatar

/**
 * ================================================
 * 类名：com.example.retrofit
 * 时间：2021/7/6 14:41
 * 描述：自定义头像切割圆角控件 class02
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val imageWidth = 200f.dp
    private val imagePadding = 20f.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bounds = RectF(
        imagePadding,
        imagePadding,
        imagePadding + imageWidth,
        imagePadding + imageWidth
    )

    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        paint.strokeWidth = 3f.dp
        //空心
//        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //bounds 离屏缓冲的范围
        val count = canvas.saveLayer(bounds, null)
        canvas.drawOval(
            imagePadding,
            imagePadding,
            imagePadding + imageWidth,
            imagePadding + imageWidth,
            paint
        )
        paint.xfermode = mode
        canvas.drawBitmap(
            getAvatar(context,imageWidth),
            imagePadding,
            imagePadding,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(count)

        paint.strokeWidth = 3f.dp
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            imagePadding,
            imagePadding,
            imagePadding + imageWidth,
            imagePadding + imageWidth,
            paint
        )

    }


}