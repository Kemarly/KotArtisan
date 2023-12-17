package com.example.kotartisan

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.view.View
import androidx.appcompat.widget.TooltipCompat

class Drawing : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var deleteButton: ImageButton
    private lateinit var saveButton: ImageButton
    private lateinit var shrinkButton: ImageButton
    private lateinit var growButton: ImageButton
    private lateinit var undoButton: ImageButton
    private lateinit var redoButton: ImageButton
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
    private fun showTooltip(message: String) {
        TooltipCompat.setTooltipText(deleteButton, message)
    }
    private fun hideTooltip() {
        TooltipCompat.setTooltipText(deleteButton, null)
    }
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
        redoButton = findViewById(R.id.redoButton)

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

        deleteButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Delete")
            true
        }
        saveButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Save")
            true
        }
        shrinkButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Shrink")
            true
        }
        growButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Grow")
            true
        }
        undoButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Undo")
            true
        }
        redoButton.setOnHoverListener { _, event ->
            handleHoverEvent(event, "Redo")
            true
        }

        deleteButton.setOnClickListener { drawingView.clearDrawing() }
        saveButton.setOnClickListener { drawingView.saveDrawing() }
        shrinkButton.setOnClickListener { drawingView.shrinkShape() }
        growButton.setOnClickListener { drawingView.growShape() }
        undoButton.setOnClickListener { drawingView.undo() }
        redoButton.setOnClickListener { drawingView.redo() }

        starButton.setOnClickListener { drawStar() }
        triangleButton.setOnClickListener { drawTriangle() }
        squareButton.setOnClickListener { drawSquare() }
        circleButton.setOnClickListener { drawCircle() }

        redButton.setOnClickListener { drawingView.changeColor(Color.RED) }
        orangeButton.setOnClickListener { drawingView.changeColor(Color.rgb(250, 130, 20)) }
        yellowButton.setOnClickListener { drawingView.changeColor(Color.YELLOW) }
        greenButton.setOnClickListener { drawingView.changeColor(Color.GREEN) }
        blueButton.setOnClickListener {drawingView.changeColor(Color.BLUE)}
        purpleButton.setOnClickListener { drawingView.changeColor(Color.rgb(200, 0, 255)) }
        blackButton.setOnClickListener { drawingView.changeColor(Color.BLACK) }
        brownButton.setOnClickListener { drawingView.changeColor(Color.rgb(130, 100, 30)) }

        findViewById<View>(android.R.id.content).setOnHoverListener { _, event ->
            handleHoverEvent(event, "Message")
            true
    }
        setButtonTooltips()

        setTooltip(saveButton, "Save")
        setTooltip(deleteButton, "Delete")
        setTooltip(shrinkButton, "Shrink")
        setTooltip(growButton, "Grow")
        setTooltip(undoButton, "Undo")
        setTooltip(redoButton, "Redo")
    }
    private fun setButtonTooltips() {
        setTooltip(saveButton, "Save")
        setTooltip(deleteButton, "Delete")
        setTooltip(shrinkButton, "Shrink")
        setTooltip(growButton, "Grow")
        setTooltip(undoButton, "Undo")
        setTooltip(redoButton, "Redo")

        setTooltip(starButton, "Star")
        setTooltip(circleButton, "Circle")
        setTooltip(triangleButton, "Triangle")
        setTooltip(squareButton, "Square")

        setTooltip(redButton, "Red")
        setTooltip(orangeButton, "Orange")
        setTooltip(yellowButton, "Yellow")
        setTooltip(greenButton, "Green")
        setTooltip(blueButton, "Blue")
        setTooltip(purpleButton, "Purple")
        setTooltip(blackButton, "Black")
        setTooltip(brownButton, "Brown")
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun setTooltip(view: View, tooltipText: String) {
        view.setOnHoverListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_HOVER_ENTER -> TooltipCompat.setTooltipText(view, tooltipText)
                MotionEvent.ACTION_HOVER_EXIT -> TooltipCompat.setTooltipText(view, null)
            }
            true
        }
    }

    private fun drawStar() {
        val starColor = drawingView.currentColor
        val starSize = drawingView.currentSize
        val starPath = createStarPath()
        drawingView.addShape(starColor, starSize, starPath)
    }

    private fun drawTriangle() {
        val triangleColor = drawingView.currentColor
        val triangleSize = drawingView.currentSize
        val trianglePath = createTrianglePath()
        drawingView.addShape(triangleColor, triangleSize, trianglePath)
    }

    private fun drawSquare() {
        val squareColor = drawingView.currentColor
        val squareSize = drawingView.currentSize
        val squarePath = createSquarePath()
        drawingView.addShape(squareColor, squareSize, squarePath)
    }

    private fun drawCircle() {
        val circleColor = drawingView.currentColor
        val circleSize = drawingView.currentSize
        val circlePath = createCirclePath()
        drawingView.addShape(circleColor, circleSize, circlePath)
    }


private fun createStarPath(): Path
{
    val centerX = drawingView.centerX
    val centerY = drawingView.centerY
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
private fun createTrianglePath(): Path {
    /*val centerX = 0f
    val centerY = 0f*/
    val centerX = drawingView.centerX
    val centerY = drawingView.centerY
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
private fun createSquarePath(): Path
{
    val path = Path()
    val size = 100f
    val centerX = drawingView.centerX
    val centerY = drawingView.centerY
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
private fun createCirclePath(): Path {
    val centerX = drawingView.centerX
    val centerY = drawingView.centerY
    val path = Path()
    path.addCircle(centerX, centerY, 5f, Path.Direction.CW)
    return path
}
private fun handleHoverEvent(event: MotionEvent?, message: String) {
    when (event?.action) {
        MotionEvent.ACTION_HOVER_ENTER -> showTooltip(message)
        MotionEvent.ACTION_HOVER_EXIT -> hideTooltip()
    }
}
}