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
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
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

    private var trackingPointId = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                //获取第一根手指
                trackingPointId = event.getPointerId(0)
                //记录落下的x y坐标值
                downX = event.x
                downY = event.y

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                //获取当前多点触碰按下的手指
                val actionIndex = event.actionIndex
                trackingPointId = event.getPointerId(actionIndex)

                //记录落下的x y坐标值
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_POINTER_UP -> {
                //判断是否是当前跟踪的手指
                val actionIndex = event.actionIndex
                val pointId = event.getPointerId(actionIndex)
                if (pointId == trackingPointId) {
                    //id值相等 是跟踪的手指
                    //判断当前手指是不是最后一根手指
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        //最后一根手指接力给倒数第二根手指
                        event.pointerCount - 2
                    } else {
                        //非最后一根手指接力给最后一根???
                        event.pointerCount - 1
                    }

                    trackingPointId = event.getPointerId(newIndex)

                    //记录落下的x y坐标值
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)

                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }
            }

            MotionEvent.ACTION_MOVE -> {
                //手指移动
                //通过当前手指的id获取它的index下标 从而获取真正的下标
                val index = event.findPointerIndex(trackingPointId)
                offsetX = event.getX(index) - downX + originalOffsetX //getX()第一根手指的坐标
                offsetY = event.getY(index) - downY + originalOffsetY
                invalidate()
            }


        }

        return true
    }
}