package com.example.customview.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * ================================================
 * 类名：com.example.customview.view
 * 时间：2021/7/9 17:19
 * 描述：修改一个已有View的尺寸
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class SquareImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    //修改完以后仅仅自身改变了 未通知父View可能会导致页面出现问题
//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        val width = r - l
//        val height = b - t
//        val size = min(width, height)
//        super.layout(l, t, (l + size), (t + size))
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获得测量阶段的期望尺寸
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
        // width height 父View给的实际尺寸 可能获取不到

    }
}