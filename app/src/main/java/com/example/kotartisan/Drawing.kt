package com.example.kotartisan

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.widget.Button
import android.widget.ImageButton

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
    private lateinit var redButton: Button
    private lateinit var orangeButton: Button
    private lateinit var yellowButton: Button
    private lateinit var greenButton: Button
    private lateinit var blueButton: Button
    private lateinit var purpleButton: Button
    private lateinit var blackButton: Button
    private lateinit var brownButton: Button

    private var centerX = 0f
    private var centerY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        drawingView = findViewById(R.id.drawingView)
        centerY = drawingView.height / 2f
        centerX = drawingView.width / 2f

        deleteButton = findViewById(R.id.deleteButton)
        saveButton = findViewById(R.id.saveButton)
        shrinkButton = findViewById(R.id.shrinkButton)
        growButton = findViewById(R.id.growButton)
        undoButton = findViewById(R.id.undoButton)

        starButton = findViewById(R.id.starButton)
        circleButton = findViewById(R.id.circleButton)
        squareButton = findViewById(R.id.squareButton)
        triangleButton = findViewById(R.id.triangleButton)

        redButton = findViewById(R.id.redButton)
        orangeButton = findViewById(R.id.orangeButton)
        yellowButton = findViewById(R.id.yellowButton)
        greenButton = findViewById(R.id.greenButton)
        blueButton = findViewById(R.id.blueButton)
        purpleButton = findViewById(R.id.purpleButton)
        blackButton = findViewById(R.id.blackButton)
        brownButton = findViewById(R.id.brownButton)


        deleteButton.setOnClickListener { drawingView.clearDrawing()}
        saveButton.setOnClickListener{
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
        undoButton.setOnClickListener { drawingView.undo() }

        starButton.setOnClickListener {drawStar()}
        triangleButton.setOnClickListener {drawTriangle()}
        squareButton.setOnClickListener {drawSquare()}
        circleButton.setOnClickListener {drawCircle()}

        redButton.setOnClickListener {drawingView.changeColor(Color.RED)}
        orangeButton.setOnClickListener {drawingView.changeColor(Color.rgb(250,130,20))}
        yellowButton.setOnClickListener {drawingView.changeColor(Color.YELLOW)}
        greenButton.setOnClickListener {drawingView.changeColor(Color.GREEN)}
        blueButton.setOnClickListener {drawingView.changeColor(Color.BLUE)
        purpleButton.setOnClickListener {drawingView.changeColor(Color.rgb(204,85,255))}
        blackButton.setOnClickListener {drawingView.changeColor(Color.BLACK)}
        brownButton.setOnClickListener {drawingView.changeColor(Color.rgb(90,60,30))}


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
    val centerX = 0f
    val centerY = 0f
    val path = Path()
    val size = 100f
    val starRadius = size / 3f

    for (i in 0 until 10) {
        val angle = Math.toRadians((i * 36).toDouble())
        val radius = if (i % 2 == 0) size else starRadius
        val x = centerX + radius * kotlin.math.cos(angle).toFloat()
        val y = centerY + radius * kotlin.math.sin(angle).toFloat()

        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }

    path.close()

    return path
}
private fun drawTriangle()
{
    val triangleColor = Color.BLUE
    val triangleSize = Size(10, 10)
    val trianglePath = createTrianglePath()

    DrawingView.addShape(triangleColor, triangleSize, trianglePath)
}

private fun createTrianglePath(): Path {
    val centerX = 0f
    val centerY = 0f
    val path = Path()
    val size = 100f
    val y1 = centerY - size / 2
    val x2 = centerX - size / 2
    val y2 = centerY + size / 2
    val x3 = centerX + size / 2
    val y3 = centerY + size / 2

    path.moveTo(centerX, y1)
    path.lineTo(x2, y2)
    path.lineTo(x3, y3)
    path.close()

    return path
}

private fun drawSquare()
{
    val squareColor = Color.BLUE
    val squareSize = Size(10, 10)

    val squarePath = createSquarePath()

    DrawingView.addShape(squareColor, squareSize, squarePath)
}

private fun createSquarePath(): Path
{
    val path = Path()
    val size = 100f
    val centerX = 0f
    val centerY = 0f
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
    val centerX = 0f
    val centerY = 0f
    val path = Path()
    path.addCircle(centerX, centerY, 5f, Path.Direction.CW)
    return path
}
}