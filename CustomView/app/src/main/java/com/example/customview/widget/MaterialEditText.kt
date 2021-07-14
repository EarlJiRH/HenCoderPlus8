package com.example.customview.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.customview.R
import com.example.customview.dp

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/9 13:54
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {


    private val textMargin = 8.dp
    private val size = 12.dp

    private val horizontalOffset = 5.dp//水平位移
    private val verticalOffset = 23.dp//垂直位移
    private val extraVerticalOffset = 16.dp //文字动画跳跃的距离

    private var floatingLabelShown = false

    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }


    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = size

    }

    var userFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + size + textMargin).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - size - textMargin).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }

        }

    init {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
//        userFloatingLabel =
//            typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        val typedArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
        userFloatingLabel =
            typedArray.getBoolean(0, true)
        //手动回收
        typedArray.recycle()
    }


    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        //判断是否有输入内容 进行动画效果
        if (floatingLabelShown && text.isNullOrEmpty()) {
            //下降动画
            floatingLabelShown = false
            animator.reverse()//1-0

        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            //上升动画
            floatingLabelShown = true
            animator.start()//0-1
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //文字透明度动画
        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue =
            verticalOffset + extraVerticalOffset * (1 - floatingLabelFraction)


        //绘制提示文字
        canvas.drawText(hint.toString(), horizontalOffset, currentVerticalValue, paint)
    }

}