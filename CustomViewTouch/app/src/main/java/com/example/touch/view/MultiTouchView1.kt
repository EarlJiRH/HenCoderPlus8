package com.example.touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.touch.dp
import com.example.touch.getAvatar

/**
 * ================================================
 * 类名：com.example.touch.view
 * 时间：2021/7/27 13:35
 * 描述：单点触碰拖动
 * 多点触碰效果:只有单点触碰有效 多点触碰会焦点抢占
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    //做操作的bitmap
    private val bitmap = getAvatar(resources, 200.dp.toInt())

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //手指拖动的偏移距离 拖动过程中的相对拖动距离
    private var offsetX = 0f
    private var offsetY = 0f

    //手指每次开始拖动的落下点 拖动的起始坐标
    private var downX = 0f
    private var downY = 0f

    //初始偏移量
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                //手指移动
                offsetX = event.x - downX + originalOffsetX //getX()第一根手指的坐标
                offsetY = event.y - downY + originalOffsetY
                invalidate()
            }

            MotionEvent.ACTION_DOWN -> {
                //记录落下的x y坐标值
                downX = event.x
                downY = event.y

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
        }

        return true
    }
}