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
 * 时间：2021/7/27 14:27
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MultiTouchView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
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

        var pointerCount = event.pointerCount
        var sumX = 0f
        var sumY = 0f
        val isPointUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until pointerCount) {
            //抬起的是最后一根手指  取反
            if (!(isPointUp && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }

        }
        //抬起事件 去除一根手指的计算量
        if (isPointUp) {
            pointerCount--
        }

        //焦点坐标(sunX/count)
        val focusX: Float = sumX / pointerCount
        val focusY: Float = sumY / pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                //重置焦点坐标(手指数量变化 焦点变化会跳跃效果比较明显)
                downX = focusX
                downY = focusY

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                //手指移动
                //通过当前手指的id获取它的index下标 从而获取真正的下标
                offsetX = focusX - downX + originalOffsetX //getX()第一根手指的坐标
                offsetY = focusY - downY + originalOffsetY
                invalidate()
            }

        }

        return true
    }
}