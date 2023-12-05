package com.example.kotartisan

import android.graphics.Color
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.widget.ImageButton
import java.lang.Math.cos
import java.lang.Math.sin


class Drawing : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var deleteButton: ImageButton
    private lateinit var saveButton: ImageButton
    private lateinit var shrinkButton: ImageButton
    private lateinit var growButton: ImageButton
    private lateinit var undoButton: ImageButton
    private lateinit var starButton: ImageButton
    private lateinit var circleButton: ImageButton
    private lateinit var squareButton: ImageButton
    private lateinit var triangleButton: ImageButton
    private val points = mutableListOf<Pair<Float, Float>>()
    private var centerX = drawingView.width / 2f
    private var centerY = drawingView.height / 2f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        drawingView = findViewById(R.id.drawingView)
        deleteButton = findViewById(R.id.deleteButton)
        saveButton = findViewById(R.id.saveButton)
        shrinkButton = findViewById(R.id.shrinkButton)
        growButton = findViewById(R.id.growButton)
        undoButton = findViewById(R.id.undoButton)
        starButton = findViewById(R.id.starButton)
        circleButton = findViewById(R.id.circleButton)
        squareButton = findViewById(R.id.squareButton)
        triangleButton = findViewById(R.id.triangleButton)



        /*deleteButton.setOnClickListener { drawingView.clearDrawing() }
        saveButton.setOnClickListener {
            val bitmap = Bitmap.createBitmap(
                drawingView.width,
                drawingView.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawingView.draw(canvas)
        }
        shrinkButton.setOnClickListener { drawingView.shrinkShape() }
        growButton.setOnClickListener { drawingView.growShape() }
        undoButton.setOnClickListener { drawingView.undo() }*/
        starButton.setOnClickListener {drawStar()}
        triangleButton.setOnClickListener {drawTriangle()}
        squareButton.setOnClickListener {drawSquare()}
        circleButton.setOnClickListener {drawCircle()}

    }
}
private fun drawStar()
{
    val starColor = Color.BLUE
    val starSize = Size(10, 10)
    val starPath = createStarPath()
    DrawingView.addShape(starColor, starSize, starPath)
}
private fun createStarPath(): Path
{
    val path = Path()


    val size = 100f
    for (i in 0 until 5)
    {
        val angle = Math.toRadians((i * 144).toDouble())
        val x = centerX + size * cos(angle).toFloat()
        val y = centerY + size * sin(angle).toFloat()
        points.add(Pair(x, y))
    }

    return path
}
private fun drawTriangle()
{
    val triangleColor = Color.BLUE
    val triangleSize = Size(10, 10)

    // Create a Path for the triangle
    val trianglePath = createTrianglePath()

    // Add the triangle to the DrawingView
    DrawingView.addShape(triangleColor, triangleSize, trianglePath)
}

// Function to create a Path representing a triangle
private fun createTrianglePath(): Path {
    val path = Path()
    val size = 100f
    val x1 = centerX
    val y1 = centerY - size / 2
    val x2 = centerX - size / 2
    val y2 = centerY + size / 2
    val x3 = centerX + size / 2
    val y3 = centerY + size / 2

    path.moveTo(x1, y1)
    path.lineTo(x2, y2)
    path.lineTo(x3, y3)
    path.close()

    return path
}

private fun drawSquare()
{
    val squareColor = Color.BLUE
    val squareSize = Size(10, 10)

    // Create a Path for the square
    val squarePath = createSquarePath()

    // Add the square to the DrawingView
    DrawingView.addShape(squareColor, squareSize, squarePath)
}

private fun createSquarePath(): Path
{
    val path = Path()
    val size = 100f
    val x1 = centerX - size / 2
    val y1 = centerY - size / 2
    val x2 = centerX - size / 2
    val y2 = centerY + size / 2
    val x3 = centerX + size / 2
    val y3 = centerY + size / 2
    val x4 = centerX + size / 2
    val y4 = centerY - size / 2

    path.moveTo(x1, y1)
    path.lineTo(x2, y2)
    path.lineTo(x3, y3)
    path.lineTo(x4, y4)
    path.close()

    return path
}

private fun drawCircle()
{
    val circleColor = Color.BLUE
    val circleSize = Size(10, 10)
    val circlePath = createCirclePath()
    DrawingView.addShape(circleColor, circleSize, circlePath)
}

private fun createCirclePath(): Path {
    val path = Path()
    path.addCircle(centerX, centerY, 5f, Path.Direction.CW)
    return path
}