package com.example.myappcustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleCounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 0.0f
    private var number = 0
    private var addMode = false

    init {
        textPaint.textSize = 50f

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleCounterView)

        number = typedArray.getInt(R.styleable.CircleCounterView_initialNumber, 0)
        addMode = typedArray.getBoolean(R.styleable.CircleCounterView_addMode, true)

        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = Math.min(width / 2f, height / 2f) - 20f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        when (number) {
            in 0..10 -> circlePaint.color = Color.GREEN
            in 11..20 -> circlePaint.color = Color.YELLOW
            else -> circlePaint.color = Color.RED
        }

        canvas.drawCircle(width / 2f, height / 2f, radius, circlePaint)
        canvas.drawText(number.toString(), width / 2f, height / 2f - (textPaint.descent() + textPaint.ascent()) / 2, textPaint)
    }

    init {
        isClickable = true
    }
    override fun performClick(): Boolean {
        if (super.performClick()) return true
        if (addMode) {
            number++
        } else {
            number--
        }
        invalidate()
        return true
    }

    fun toggleMode() {
        addMode = !addMode
    }
}