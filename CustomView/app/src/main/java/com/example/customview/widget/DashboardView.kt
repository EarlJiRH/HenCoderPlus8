package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * ================================================
 * 类名：com.example.retrofit
 * 时间：2021/7/5 17:46
 * 描述：自定义仪表盘效果控件 class01
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    //仪表盘开口角度
    private val openAngle = 120f

    private val dashWidth = 2f.dp //刻度宽
    private val dashHeight = 10f.dp //刻度高
    private val radius = 150f.dp //刻度盘半径

    private val length = 120f.dp //长度

    private val dashCount = 20f//刻度值数量

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    private val dash = Path()

    //虚线效果 使用特效来绘制
    private lateinit var pathEffect: PathEffect

    //刻度值
    private val mark = 5


    init {
        paint.strokeWidth = 3f.dp
        //空心
        paint.style = Paint.Style.STROKE
        //dir 绘制顺序
        dash.addRect(0f, 0f, dashWidth, dashHeight, Path.Direction.CCW)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()

        //圆弧的路线
        path.addArc(
            width / 2f - radius,
            height / 2f - radius,
            width / 2f + radius,
            height / 2f + radius,
            90 + openAngle / 2,//起始角度
            360 - openAngle//圆弧滑过的角度
        )

        val pathMeasure = PathMeasure(path, false)
        pathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - dashWidth) / dashCount,
                0f,
                PathDashPathEffect.Style.ROTATE
            )


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制一个圆弧(仪表盘)
        canvas.drawPath(
            path,
            paint
        )


        //刻度效果 使用特效来绘制
        paint.pathEffect = pathEffect
        //绘制刻度
        canvas.drawPath(
            path,
            paint
        )
        //去除虚线效果
        paint.pathEffect = null

        //绘制仪表盘的指针 第mark个刻度
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + length * cos(Math.toRadians(markToRadians(mark))).toFloat(),//余弦
            height / 2 + length * sin(Math.toRadians(markToRadians(mark))).toFloat(),//正弦
            paint
        )

    }


    private fun markToRadians(mark: Int) =
        (90 + openAngle / 2 + (360 - openAngle) / dashCount * mark).toDouble()

}