package com.example.kotartisan

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Bitmap
import android.os.Environment
import java.io.FileOutputStream
import java.io.IOException
import android.widget.Toast
import android.graphics.*
import android.util.Size

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private var currentShape: Path = Path()
    private val paths = ArrayList<Path>()
    private val shapes = ArrayList<ShapeData>()
    private val paint = Paint()
    private var newColor: Int = Color.BLACK
    private val undoPaths = ArrayList<Path>()
    private val undoShapes = ArrayList<ShapeData>()
    var centerX = 0f
    var centerY = 0f
    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5f
        centerY = height / 2f
        centerX = width / 2f
    }
    fun changeColor(color: Int) {
        newColor = color
        paint.color = newColor
    }
    fun addShape(color: Int, size: Float, path: Path) {
        shapes.add(ShapeData(Path(currentShape), color, size))
        currentShape.reset()
        invalidate()
    }

    fun setShapes(shapesList: List<ShapeData>) {
        shapes.clear()
        shapes.addAll(shapesList)
        invalidate()
    }
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
            MotionEvent.ACTION_DOWN ->
            {
                currentShape.reset()
                currentShape.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> currentShape.lineTo(x, y)
            MotionEvent.ACTION_UP ->
            {
                paths.add(Path(currentShape))
                shapes.add(ShapeData(Path(currentShape), newColor, paint.strokeWidth))
                currentShape.reset()
            }
            else -> return false
        }
        invalidate()
        return true
    }

    companion object {
        fun addShape(Color: Int, Size: Size, Path: Path) {

        }
    }
   fun setCurrentShape(shape: Path) {
        currentShape = shape
    }

    fun setCurrentColor(color: Int) {
        paint.color = color
    }

    fun setCurrentSize(size: Float) {
        paint.strokeWidth = size
    }

    fun clearDrawing()
    {
        paths.clear()
        shapes.clear()
        currentShape.reset()
        invalidate()
    }
    fun saveDrawing(): Bitmap? {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        val externalPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val filePath = "$externalPath/Artisan.png"
        try {
            val fileOutputStream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
            Toast.makeText(context, "Drawing saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save drawing", Toast.LENGTH_SHORT).show()
            return null
        }
        return bitmap
    }
    fun shrinkShape() {
        paint.strokeWidth = paint.strokeWidth - 5f
        val scaling = 0.8f
        for (shape in shapes)
        {shape.path.transform(Matrix().apply { postScale(scaling, scaling, centerX, centerY )})}

        invalidate()
    }
    fun growShape() {
        paint.strokeWidth = paint.strokeWidth + 5f
        val scaling = 1.2f
        for (shape in shapes)
        {shape.path.transform(Matrix().apply { postScale(scaling, scaling, centerX, centerY )})}
        invalidate()
    }
    fun undo() {
        if (paths.isNotEmpty()) {
            //paths.removeAt(paths.size - 1)
            //shapes.removeAt(shapes.size -1)
            undoPaths.add(paths.removeAt(paths.size - 1))
            undoShapes.add(shapes.removeAt(shapes.size - 1))
            invalidate()
        }
    }
    fun redo() {
        if (undoPaths.isNotEmpty()) {
            paths.add(undoPaths.removeAt(undoPaths.size - 1))
            shapes.add(undoShapes.removeAt(undoShapes.size - 1))
            invalidate()
        }
    }
}

data class ShapeData
    (
    val path: Path,
    val color: Int,
    val size: Float = 10.0f
)