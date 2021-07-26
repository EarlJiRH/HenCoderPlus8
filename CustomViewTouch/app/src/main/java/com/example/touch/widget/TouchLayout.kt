package com.example.touch.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import kotlin.math.abs

/**
 * ================================================
 * 类名：com.example.touch.Widget
 * 时间：2021/7/19 10:22
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class TouchLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    override fun shouldDelayChildPressedState(): Boolean {
        //是否需要延迟子View的按下状态
        //滑动控件通常需要添加(true) 非滑动控件不需要添加(false)
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        return super.onInterceptTouchEvent(ev)
        val delta = ev.y - 100
        return abs(delta)>200
        //默认先不拦截事件 让子View触发条件 再拦截返回
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}