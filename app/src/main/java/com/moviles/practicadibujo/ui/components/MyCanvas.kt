package com.moviles.practicadibujo.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyCanvas(context: Context?, attrs: AttributeSet) : View(context, attrs) {
    val objPaint = Paint()
    var xStart = 0f
    var yStart = 0f
    var xFin = 0f
    var yFin = 0f
    var forma = "ninguna"
    var bitmap: Bitmap?=null
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
    }
    override fun onDraw(canvas: Canvas?) {
        bitmap?.let {
            canvas?.drawBitmap(it,0f,0f,objPaint)
        }
        super.onDraw(canvas)
        if (forma == "cuadrado") {
            objPaint.color = Color.GREEN
            canvas?.drawRect(xStart, yStart, xFin, yFin, objPaint)
        }
        if (forma == "linea") {
            objPaint.color = Color.RED
            objPaint.strokeWidth = 10f
            canvas?.drawLine(xStart, yStart, xFin, yFin, objPaint)
        }
        if (forma == "circulo") {
            objPaint.color = Color.BLUE
            canvas?.drawCircle(xStart, yStart, xFin / 2, objPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                xStart = event.x
                yStart = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                xFin = event.x
                yFin = event.y
                invalidate()
            }
            MotionEvent.ACTION_UP->{
                bitmap =getBitMapFromView(this)
            }
        }
        return true
    }

    private fun getBitMapFromView(view: View): Bitmap? {
        val bitmap= Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun form (forma:String){
        this.forma=forma
    }
}