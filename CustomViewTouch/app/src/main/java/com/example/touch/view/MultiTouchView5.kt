package com.example.touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.example.touch.dp

/**
 * ================================================
 * 类名：com.example.touch.view
 * 时间：2021/7/27 15:59
 * 描述：自定义多点画板View
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MultiTouchView5(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var paths = SparseArray<Path>()


    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4.dp
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until paths.size()) {
            val path = paths.valueAt(i)
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                val path = Path()
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                //存入不变值 event的pointerId
                paths.append(event.getPointerId(actionIndex), path)
                invalidate()
            }


            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    //获取每个循环下标对应的pointerId
                    val pointId = event.getPointerId(i)
                    //通过pointerId获取存入的path
                    val path = paths.get(pointId)
                    path.lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                paths.remove(pointerId)
                invalidate()
            }

        }

        return true
    }
}