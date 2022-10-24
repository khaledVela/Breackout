package com.moviles.practicadibujo.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Path
import android.view.MotionEvent
import kotlin.math.abs

class PaintCanvas(context: Context?, attr: AttributeSet?) : View(context, attr) {
    private var my: Float = 0f
    private var mx: Float = 0f
    private var pathList: ArrayList<Path> = ArrayList()
    private var path: Path = Path()
    private var objPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        pathList.forEach {
            canvas?.drawPath(it, objPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
            else -> return false
        }
        return true
    }

    private fun touchStart(x: Float?, y: Float?) {
        if (x==null || y==null) return
        pathList.add(path)
        path.moveTo(x, y)
        mx = x
        my = y
    }

    private fun touchMove(x: Float?, y: Float?) {
        if (x==null || y==null) return
        val dx = abs(x-mx)
        val dy = abs(y-my)
        if (dx >= 4 || dy >= 4) {
            path.quadTo(mx, my, (x+mx)/2, (y+my)/2)
            mx = x
            my = y
        }
    }

    private fun touchUp() {
        path.lineTo(mx, my)
    }

}
