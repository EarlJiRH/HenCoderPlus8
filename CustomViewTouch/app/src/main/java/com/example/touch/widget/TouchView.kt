package com.example.touch.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * ================================================
 * 类名：com.example.customviewtouch.View
 * 时间：2021/7/19 9:32
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class TouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            performClick()
        }
        if (event.action == MotionEvent.ACTION_UP) {
            performClick()
        }
        //所有权标志 仅和Down事件关联
        return event.actionMasked == MotionEvent.ACTION_DOWN
//        super.onTouchEvent(event)
    }
}