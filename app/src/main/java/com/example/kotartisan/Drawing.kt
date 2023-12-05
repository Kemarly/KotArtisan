package com.example.kotartisan

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.view.View
import android.widget.ImageButton
import com.example.kotartisan.DrawingView
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
    private var centerX = 0f
    private var centerY = 0f
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

        centerX = drawingView.width / 2f
        centerY = drawingView.height / 2f

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

    // Create a Path for the star
    val starPath = createStarPath()

    // Add the star to the DrawingView
    drawingView.addShape(starColor, starSize, starPath)
}
private fun createStarPath(): Path {
    // Implement the logic to create a star-shaped Path
    // This will depend on your specific requirements for the star shape

    val path = Path()
    // Add points to the path to create a star
    // Example: path.moveTo(x1, y1)
    //          path.lineTo(x2, y2)
    //          ...
    //          path.close()

    val size = 100f
    for (i in 0 until 5) {
        val angle = Math.toRadians((i * 144).toDouble())
        val x = centerX + size * cos(angle).toFloat()
        val y = centerY + size * sin(angle).toFloat()
        points.add(Pair(x, y))
    }

    return path
}
private fun drawTriangle()
{
    val starColor = Color.BLUE
    val starSize = Size(10, 10)

    // Create a Path for the triangle
    val trianglePath = createTrianglePath()

    // Add the triangle to the DrawingView
    drawingView.addShape(triangleColor, triangleSize, trianglePath)
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
    val starColor = Color.BLUE
    val starSize = Size(10, 10)

    // Create a Path for the square
    val squarePath = createSquarePath()

    // Add the square to the DrawingView
    drawingView.addShape(squareColor, squareSize, squarePath)
}

// Function to create a Path representing a square
private fun createSquarePath(): Path {
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
    val starColor = Color.BLUE
    val starSize = Size(10, 10)

    // Create a Path for the circle
    val circlePath = createCirclePath()

    // Add the circle to the DrawingView
    drawingView.addShape(circleColor, circleSize, circlePath)
}

// Function to create a Path representing a circle
private fun createCirclePath(): Path {
    val path = Path()
    // Use a single point to represent the center of the circle
    // Example:
    path.addCircle(centerX, centerY, 5f, Path.Direction.CW)

    // Adjust the logic based on your specific requirements for the circle shape

    return path
}