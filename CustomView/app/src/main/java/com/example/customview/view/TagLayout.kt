package com.example.customview.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

/**
 * ================================================
 * 类名：com.example.customview.view
 * 时间：2021/7/12 13:20
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()

    //测量
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //父View的要求
//        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
//        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthUsed = 0     //最宽行的宽度
        var heightUsed = 0
        var lineWidthUsed = 0 //当前行的已用宽度
        var lineMaxHeight = 0 //当前行的高度(取最高的高度)
        //父View给的宽度限制
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        //测量
        for ((index, child) in children.withIndex()) {
            //child进行测量 给与子View的要求
            //开发者意见 xml设置的属性
//            val layoutParams = child.layoutParams
            //MarginLayoutParams
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,//给与子View的引用宽度
                heightMeasureSpec,
                heightUsed
            )

            //Mode不是MeasureSpec.UNSPECIFIED 不是父类无约束
            //width 超过当前行的父View的宽度
            //折行
            if (specWidthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > specWidthSize) {
                lineWidthUsed = 0
                heightUsed += lineMaxHeight //折行起点高度
                lineMaxHeight = 0
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed
                )
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )

            lineWidthUsed += child.measuredWidth  //当前行已用行宽
            widthUsed = max(widthUsed, lineWidthUsed) //比对最宽行的行宽赋值
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight) //当前行行高比对赋值

//            var childWidthSpecMode = 0
//            var childWidthSpecSize = 0
//            when (layoutParams.width) {
//                LayoutParams.MATCH_PARENT ->
//                    //match_parent
//                    when (widthSpecMode) {//父View的限制
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            //精确值
//                            childWidthSpecMode = MeasureSpec.EXACTLY
//                            childWidthSpecSize = widthSpecSize - widthUsed//父View剩余空间
//                        }
//                        MeasureSpec.AT_MOST -> {
//                            //填满
//                            childWidthSpecMode = MeasureSpec.EXACTLY
//                            childWidthSpecSize = widthSpecSize - widthUsed//父View剩余空间
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            //无限制
//                            childWidthSpecMode = MeasureSpec.UNSPECIFIED
//                            childWidthSpecSize = 0
//                        }
//                    }
//                LayoutParams.WRAP_CONTENT ->
//                    //wrap_content
//                    when (widthSpecMode) {
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            //精确空间、 给父View最大可用空间
//                            childWidthSpecMode = MeasureSpec.AT_MOST
//                            childWidthSpecSize = widthSpecSize - widthUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthSpecMode = MeasureSpec.UNSPECIFIED
//                            childWidthSpecSize = 0
//                        }
//                    }
//                else -> {
//                    childWidthSpecMode = MeasureSpec.EXACTLY
//                    childWidthSpecSize = layoutParams.width
//                }
//            }
        }

        val selfWidth = widthUsed
        val selfHeight = heightUsed + lineMaxHeight
        setMeasuredDimension(selfWidth, selfHeight)
    }

    //排布子View
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //布局排布
        for (child in children) {
            //仅有一个子View
//            child.layout(0, 0, r - l, b - t)
            //两个子View 左上角、右下角
//            if (children.indexOf(child) == 0) {
//                child.layout(0, 0, (r - l) / 2, (b - t) / 2)
//            } else {
//                child.layout((r - l) / 2, (b - t) / 2, r - l, b - t)
//            }
            //不定个数子View 遍历
            for ((index, child) in children.withIndex()) {
                val childBounds = childrenBounds[index]
                child.layout(
                    childBounds.left,
                    childBounds.top,
                    childBounds.right,
                    childBounds.bottom
                )

            }
        }
    }


    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}