package io.intrepid.koroutines.screens.animation

import android.arch.lifecycle.Observer
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class AnimationView(
        context: Context, attributeSet: AttributeSet
) : View(context, attributeSet), Observer<Set<AnimatedShape>> {
    private val noShapes = emptySet<AnimatedShape>()
    private var shapes = noShapes
    private val paint = Paint()
    private val rect = RectF()

    override fun onChanged(shapes: Set<AnimatedShape>?) {
        this.shapes = shapes ?: noShapes
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val scale = minOf(width, height) / 2.0f
        shapes.forEach { shape ->
            val x = (shape.x - 0.5f) * scale + width / 2
            val y = (shape.y - 0.5f) * scale + height / 2
            val r = shape.r * scale
            rect.set(x - r, y - r, x + r, y + r)
            paint.color = shape.color
            when (shape) {
                is AnimatedCircle -> canvas.drawArc(rect, 0.0f, 360.0f, true, paint)
                is AnimatedSquare -> canvas.drawRect(rect, paint)
            }
        }
    }
}
