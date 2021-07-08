package com.example.customview.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp
import com.example.customview.getAvatar

/**
 * ================================================
 * 类名：com.example.customview.widget
 * 时间：2021/7/7 14:32
 * 描述：Canvas的范围裁切 几何变换 class04
 * 修改人：
 * 修改时间：
 * 修改备注：
 * ================================================
 * @author Admin
 */
class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {


    private val bitmapSize = 200.dp
    private val bitmapPadding = 100.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(context, bitmapSize)

    //图形的3D变换
    private val camera = Camera()

    private val clipped = Path().apply {
        addOval(
            bitmapPadding,
            bitmapPadding,
            bitmapPadding + bitmapSize,
            bitmapPadding + bitmapSize,
            Path.Direction.CCW
        )
    }

    init {
        //沿着x轴旋转30°
        camera.rotateX(30f)
        //无法设置camera的旋转轴心 默认是左上角的位置(0,0)

        //防止糊脸效果 将camera在z轴上进行拉远 使camera的投影效果变小
        camera.setLocation(
            0f,
            0f,
            -6 * resources.displayMetrics.density //需要根据手机密度进行动态适配
        )//正常只填写z轴的距离 默认-8f 单位:英寸 1英寸=72px
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //有毛边效果
//        canvas.clipPath(clipped)
//        canvas.clipRect(
//            bitmapPadding,
//            bitmapPadding,
//            bitmapPadding + bitmapSize / 2f,
//            bitmapPadding + bitmapSize / 2f
//        )
        //绘制上部停止不动效果
        canvas.save()
        canvas.translate(
            bitmapPadding + bitmapSize / 2,
            bitmapPadding + bitmapSize / 2
        )
        canvas.rotate(-30f)
        canvas.clipRect(
            -bitmapSize,
            -bitmapSize,
            bitmapSize,
            0f
        )
        canvas.rotate(30f)
        canvas.translate(
            -(bitmapPadding + bitmapSize / 2),
            -(bitmapPadding + bitmapSize / 2)
        )
        canvas.drawBitmap(
            bitmap,
            bitmapPadding,
            bitmapPadding,
            paint
        )
        canvas.restore()

        //绘制下部翻页效果
        canvas.save()
        //现将canvas的坐标系移到左上角 再旋转camrea 实现轴心旋转 再将canvas的坐标系移动回原位置
        //倒着写
        canvas.translate(
            bitmapPadding + bitmapSize / 2,
            bitmapPadding + bitmapSize / 2
        )
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)

        //可以裁切 此处方便计算
        //裁切图片下半部分做翻页效果
        canvas.clipRect(
            -bitmapSize,
            0f,
            bitmapSize,
            bitmapSize
        )

        canvas.rotate(30f)
        canvas.translate(
            -(bitmapPadding + bitmapSize / 2),
            -(bitmapPadding + bitmapSize / 2)
        )

        //可以裁切

        canvas.drawBitmap(
            bitmap,
            bitmapPadding,
            bitmapPadding,
            paint
        )

        canvas.restore()
        //1.绘制bitmap
        //2.canvas的坐标系上移到左顶点
        //3.裁剪图片的下半部分
        //4.camera进行3d旋转效果
        //canvas的坐标系下移到原位置


    }
}