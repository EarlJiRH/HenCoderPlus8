package com.example.touch.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.touch.dp
import com.example.touch.getAvatar
import kotlin.math.max
import kotlin.math.min

/**
 * ================================================
 * 类名：com.example.touch.widget
 * 时间：2021/7/20 8:59
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */


private val IMAGE_SIZE = 300.dp.toInt() //image的宽高
private const val EXTRA_SCALE_FACTOR = 1.5f //图片显示的放大倍数

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // GestureDetector.OnGestureListener, Runnable,GestureDetector.OnDoubleTapListener
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, 300.dp.toInt())

    //原始的偏移量
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    private var offsetX = 0f
    private var offsetY = 0f

    var smallScale = 0f
    var bigScale = 0f

    private val scalableFlingRunnable = ScalableFlingRunnable()

    private val scalableGestureListener = ScalableGestureListener()

    private val gestureDetector = GestureDetectorCompat(context, scalableGestureListener).apply {
        //添加双击事件监听
        setOnDoubleTapListener(scalableGestureListener)
    }

    private val scaleGestureListener = ScalableScaleGestureListener()

    //多点触碰
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    //一开始默认是小图
    var big = false

    //动画完成度
//    private var scaleFraction = 0f
//        set(value) {
//            field = value
//            invalidate()
//        }

    var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator: ObjectAnimator =
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)

//    by lazy {
//
////        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f)
////            .apply {
////            doOnEnd {
////                //3.将缩小动画结束 修正值为0 问题 无论点击哪个位置 都从正中心放大 未能跟随点击触发
////                if (!big) {
////                    offsetX = 0f
////                    offsetY = 0f
////                }
////            }
////        }
//    }


    private val scroller = OverScroller(context)//适合做惯性滑动 有初始滑动速度


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            //图片比较宽 小图
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }
        currentScale = smallScale
        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        //2.处理动画偏移的影响效果 会导致第二次的结果有问题  offsetX、offsetY 未恢复为0f
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        //当前放缩比
//        val scale = smallScale + (bigScale - smallScale) * scaleFraction
//        canvas.scale(scale, scale, width / 2f, height / 2f)

        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //使用自己的事件Event替换默认的逻辑
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
//        return gestureDetector.onTouchEvent(event)

//        return scaleGestureDetector.onTouchEvent(event)

        return true
    }


    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }


    inner class ScalableGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDoubleTap(e: MotionEvent): Boolean {
            //双击事件 40ms-300ms
            big = !big
            if (big) {
                //初始偏移量
                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimator.start()
            } else {
                //1.此处双击缩小会有空白边  offsetX offsetY未能变为0f
                //直接将 offsetX offsetY设置为0f 会有跳动效果
                scaleAnimator.reverse()
            }
            return true
        }

//        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
//            return false
//        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            //是否支持双击
            return true
        }

//        override fun onShowPress(e: MotionEvent?) {
//            //触摸超过100ms以后触发
//        }

//        override fun onSingleTapUp(e: MotionEvent?): Boolean {
//            //单次点击时抬起  —————单击
//            //返回是否消费点击事件 对事件分发整体流程无影响 给系统做记录使用
//            return true
//        }

        override fun onDown(e: MotionEvent): Boolean {
            return e.actionMasked == MotionEvent.ACTION_DOWN
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            //快速滑动事件触发
            //velocityX velocityY 快滑的位移
            if (big) {
                //定为图片的中心点为起始点
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    -((bitmap.width * bigScale - width) / 2f).toInt(),
                    ((bitmap.width * bigScale - width) / 2f).toInt(),
                    -((bitmap.height * bigScale - height) / 2f).toInt(),
                    (((bitmap.height * bigScale - height) / 2f)).toInt(),
                    40.dp.toInt(),
                    40.dp.toInt()//最大超出滑动距离
                )
                ViewCompat.postOnAnimation(this@ScalableImageView, scalableFlingRunnable)
            }

            return false
        }


        override fun onScroll(
            downEvent: MotionEvent?,
            currentEvent: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            //Move事件触发  返回值不影响事件分发
            //downEvent按下事件  currentEvent当前事件
            //distanceX    distanceY 旧位置减新位置的距离
            if (big) {
                //拖动边界值处理
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
//            offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
//            offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
//            offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
//            offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
                invalidate()
            }
            return false
        }

//        override fun onLongPress(e: MotionEvent?) {
//            //长按事件
//        }

    }

    inner class ScalableFlingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                //起始点选择为正中心点 无须处理坐标系问题
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
//            postOnAnimation(this)//api版本较高 可能需要考虑兼容
                ViewCompat.postOnAnimation(this@ScalableImageView, this)//安全性较高
            }
        }

    }

    inner class ScalableScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            //捏撑过程
            val tempCurrentScale = currentScale * detector.scaleFactor
            return if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                false
            } else {
                currentScale *= detector.scaleFactor//手指捏撑的放缩系数[0f-1f]
                //                currentScale = currentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)
                //判断 currentScale是否被修正过 修正过应当返回false
                //true 当前时刻与上一刻的比值 false 当前时刻与初始时刻的比值
                true
            }
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            //捏撑开始
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            //捏撑结束
        }

    }

}