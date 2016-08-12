package io.intrepid.koroutines.screens.animation

import android.graphics.Color

sealed class AnimatedShape {
    var x = 0.5f // 0 .. 1
    var y = 0.5f // 0 .. 1
    var color = Color.BLACK
    var r = 0.05f
}

class AnimatedCircle : AnimatedShape()
class AnimatedSquare : AnimatedShape()



