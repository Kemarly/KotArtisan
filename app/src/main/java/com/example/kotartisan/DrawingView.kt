package com.example.kotartisan

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Size
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private var currentShape: Path = Path()
    private val paths = ArrayList<Path>()
    private val shapes = ArrayList<ShapeData>()
    private val paint = Paint()
    private var newColor: Int = Color.BLACK
    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5f
    }
    fun changeColor(color: Int) {
        newColor = color
        paint.color = newColor
    }
   /* fun addShape(color: Int, size: Float, path: Path) {
        shapes.add(ShapeData(Path(currentShape), color, size))
        currentShape.reset()
        invalidate()
    }

    fun setShapes(shapesList: List<ShapeData>) {
        shapes.clear()
        shapes.addAll(shapesList)
        invalidate()
    }*/
    override fun onDraw(canvas: Canvas) {
        for (shape in shapes) {
            paint.color = shape.color
            paint.strokeWidth = shape.size
            canvas.drawPath(shape.path, paint)
        }
        canvas.drawPath(currentShape, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> currentShape.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> currentShape.lineTo(x, y)
            MotionEvent.ACTION_UP -> { /* Nothing for now */ }
            else -> return false
        }

        invalidate()
        return true
    }

    companion object {
        fun addShape(Color: Int, Size: Size, Path: Path) {

        }
    }
   /* fun setCurrentShape(shape: Path) {
        currentShape = shape
    }

    fun setCurrentColor(color: Int) {
        paint.color = color
    }

    fun setCurrentSize(size: Float) {
        paint.strokeWidth = size
    }
*/
    fun clearDrawing()
    {
        paths.clear()
        shapes.clear()

        invalidate()
    }

    fun shrinkShape() {
        paint.strokeWidth = paint.strokeWidth - 5f
        invalidate()
    }
    fun growShape() {
        paint.strokeWidth = paint.strokeWidth + 5f
        invalidate()
    }
    fun undo() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            shapes.removeAt(shapes.size -1)
            invalidate()
        }
    }

}

data class ShapeData(
    val path: Path,
    val color: Int,
    val size: Float = 10.0f
)