package com.example.customview

import android.animation.*
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.widget.ProvinceView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //属性动画 功能限制较大 仅有部分动画功能
        if (imageView.visibility == View.VISIBLE) {
//            imageView.animate()
//                .translationX(200.dp)
//                .translationY(100.dp)
//                .alpha(0.5f)
//                .scaleX(1.5f)
//                .scaleY(1.5f)
//                .rotation(180f)
//                .startDelay = 1000L
        }

        //ObjectAnimator 每次只能针对一个属性 进行动画效果
        if (circleView.visibility == View.VISIBLE) {
            val animator = ObjectAnimator.ofFloat(circleView, "radius", 150.dp)
            animator.startDelay = 1000L
            animator.start()
        }


        if (cameraView.visibility == View.VISIBLE) {

            val bottomFlipAnimator = ObjectAnimator.ofFloat(cameraView, "bottomFlip", 60f)
            bottomFlipAnimator.startDelay = 1000L
            bottomFlipAnimator.duration = 1000L
//            bottomFlipAnimator.start()

            val flipRotationAnimator = ObjectAnimator.ofFloat(cameraView, "flipRotation", 270f)
            flipRotationAnimator.startDelay = 200L
            flipRotationAnimator.duration = 1000L
//            flipRotationAnimator.start()


            val topFlipAnimator = ObjectAnimator.ofFloat(cameraView, "topFlip", -60f)
            topFlipAnimator.startDelay = 200L
            topFlipAnimator.duration = 1000L
//            topFlipAnimator.start()

            //AnimatorSet 组合ObjectAnimator
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//            animatorSet.start()

            val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 60f)
            val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270f)
            val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -60f)

            //PropertyValuesHolder 可以对一个View的多个动画进行组合
            val holderAnimator = ObjectAnimator.ofPropertyValuesHolder(
                cameraView, bottomFlipHolder,
                flipRotationHolder, topFlipHolder
            )
            holderAnimator.startDelay = 1000L
            holderAnimator.duration = 2000L
            holderAnimator.start()


        }


        //Keyframe
        if (imageView.visibility == View.VISIBLE) {
            val length = 200.dp
            //使用组合Keyframe 实现 快->慢->快 的动画效果
            val keyFrame0 = Keyframe.ofFloat(0f, 0f * length)
            //0.0->0.4的距离使用0.2的时间 快速
            val keyFrame1 = Keyframe.ofFloat(0.2f, 0.4f * length)
            //0.4->0.6的距离使用0.6的时间 慢速
            val keyFrame2 = Keyframe.ofFloat(0.8f, 0.6f * length)
            //0.6->1.0的距离使用0.2的时间 快速
            val keyFrame3 = Keyframe.ofFloat(1f, 1f * length)
            val keyframeHolder =
                PropertyValuesHolder.ofKeyframe(
                    "translationX",
                    keyFrame0,
                    keyFrame1,
                    keyFrame2,
                    keyFrame3
                )
            val animator = ObjectAnimator.ofPropertyValuesHolder(imageView, keyframeHolder)
            animator.startDelay = 1000L
            animator.duration = 2000L
            animator.start()
        }


        //Interpolator 内插器 插值器 从时间完成度->动画完成度(动画曲线)
//        val animator = ObjectAnimator.ofFloat()
//        animator.interpolator = AccelerateDecelerateInterpolator()//默认 先加速后减速 适用于变换的场景效果
//        animator.interpolator = AccelerateInterpolator()//加速插值器 结束时突然停住 适用于出场动画
//        animator.interpolator = DecelerateInterpolator()//减速插值器 适用于入场动画
//        animator.interpolator = LinearInterpolator()//匀速插值器

        if (pointFView.visibility == View.VISIBLE) {
            val animator = ObjectAnimator.ofObject(
                pointFView,
                "point",
                PointFEvaluator(),
                PointF(100.dp, 200.dp)
            )
            animator.startDelay = 1000L
            animator.duration = 5000L
            animator.start()
        }

        if (provinceView.visibility == View.VISIBLE) {
            val animator = ObjectAnimator.ofObject(
                provinceView,
                "province",
                ProvinceView.ProvinceEvaluator(),
                "澳门特别行政区"
            )
            animator.startDelay = 1000L
            animator.duration = 10000L
            animator.start()
//            provinceView.animate()
//                .translationY(200.dp)
//                .withLayer()//临时开启硬件加速
        }
    }


    inner class PointFEvaluator : TypeEvaluator<PointF> {

        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF =
            PointF(
                startValue.x + (endValue.x - startValue.x) * fraction,
                startValue.y + (endValue.y - startValue.y) * fraction
            )
//        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
//            val startX = startValue.x
//            val endX = endValue.x
//            val currentX = startX + (endX - startX) * fraction
//
//            val startY = startValue.y
//            val endY = endValue.y
//            val currentY = startY + (endY - startY) * fraction
//            return PointF(currentX, currentY)
//        }
    }


}

