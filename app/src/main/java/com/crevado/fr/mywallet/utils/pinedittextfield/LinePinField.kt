package com.crevado.fr.mywallet.utils.pinedittextfield

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.crevado.fr.mywallet.R

class LinePinField : PinField {

    private val cursorTopPadding = Util.dpToPx(5f)

    private val cursorBottomPadding = Util.dpToPx(2f)

    var bottomTextPaddingDp = 0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        initParams(attr)
    }

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(
        context,
        attr,
        defStyle
    ) {
        initParams(attr)
    }

    private fun initParams(attr: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(attr, R.styleable.LinePinField, 0, 0)
        try {
            bottomTextPaddingDp =
                a.getDimension(R.styleable.LinePinField_bottomTextPaddingDp, bottomTextPaddingDp)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {

        for (i in 0 until numberOfFields) {

            val x1 = (i * singleFieldWidth)
            val padding =
                (if (distanceInBetween != DEFAULT_DISTANCE_IN_BETWEEN) distanceInBetween else getDefaultDistanceInBetween()) / 2
            val paddedX1 = (x1 + padding)
            val paddedX2 = ((x1 + singleFieldWidth) - padding)
            val paddedY1 = height - yPadding
            val textX = ((paddedX2 - paddedX1) / 2) + paddedX1
            val textY = (paddedY1 - lineThickness) - (textPaint.textSize / 4) - bottomTextPaddingDp
            val character: Char? = getCharAt(i)

            if (highlightAllFields() && hasFocus()) {
                canvas?.drawLine(paddedX1, paddedY1, paddedX2, paddedY1, highlightPaint)
            } else {
                canvas?.drawLine(paddedX1, paddedY1, paddedX2, paddedY1, fieldPaint)
            }

            if (character != null) {
                canvas?.drawText(character.toString(), textX, textY, textPaint)
            }

            if (hasFocus() && i == text?.length ?: 0) {
                if (isCursorEnabled) {
                    val cursorY1 =
                        paddedY1 - cursorBottomPadding - highLightThickness - bottomTextPaddingDp
                    val cursorY2 = cursorTopPadding
                    drawCursor(canvas, textX, cursorY1, cursorY2, highlightPaint)
                }
            }
            highlightLogic(i, text?.length) {
                canvas?.drawLine(paddedX1, paddedY1, paddedX2, paddedY1, highlightPaint)
            }
        }

        if (shouldDrawHint()) {
            hintPaint.textAlign = Paint.Align.CENTER
            canvas?.drawText(
                hint.toString(),
                (width / 2).toFloat(),
                (height / 2).toFloat(),
                hintPaint
            )
        }
    }
}