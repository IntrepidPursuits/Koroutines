package io.intrepid.koroutines.screens.animation

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.graphics.Color
import io.intrepid.koroutines.utils.LogUtils.logVerboseMessage
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*

private val rnd = Random()

private fun norm(x: Float, y: Float) = Math.hypot(x.toDouble(), y.toDouble()).toFloat()

private const val ACC = 1e-18f
private const val MAX_SPEED = 2e-9f // in screen_fraction/nanos
private const val INIT_POS = 0.8f

private fun Random.nextColor() = Color.rgb(nextInt(256), nextInt(256), nextInt(256))
private fun Random.nextPos() = nextFloat() * INIT_POS + (1 - INIT_POS) / 2
private fun Random.nextSpeed() = nextFloat() * MAX_SPEED - MAX_SPEED / 2
private val noShapes = emptySet<AnimatedShape>()
val halfNanoSecond = 0.5e9
val nanoSecond = 1e9

class AnimationModel : ViewModel() {

    private val shapes = MutableLiveData<Set<AnimatedShape>>()
    private val jobs = arrayListOf<Job>()
    private var parent = Job()

    fun observe(owner: LifecycleOwner, observer: Observer<Set<AnimatedShape>>) =
            shapes.observe(owner, observer)

    fun update(shape: AnimatedShape) {
        val old = shapes.value ?: noShapes
        shapes.value = if (shape in old) old else old + shape
    }

    fun addAnimation() {
        jobs += List(10) {
            launch(UI , parent =  parent) {
                animateShape(if (rnd.nextBoolean()) AnimatedCircle() else AnimatedSquare())
            }
        }
        logVerboseMessage(this, "Number of Jobs = ${jobs.size}")
    }

    fun clearAnimations() {
        parent.cancel()
        parent = Job()
        shapes.value = noShapes
    }
}

suspend fun AnimationModel.animateShape(shape: AnimatedShape) {
    shape.x = rnd.nextPos()
    shape.y = rnd.nextPos()
    shape.color = rnd.nextColor()
    var sx = rnd.nextSpeed()
    var sy = rnd.nextSpeed()
    var time = System.nanoTime() // nanos
    var checkTime = time
    while (true) {
        val dt = time.let { old -> UI.awaitFrame().also { time = it } - old }
        if (dt > halfNanoSecond) continue // don't animate through over a half second lapses
        val dx = shape.x - 0.5f
        val dy = shape.y - 0.5f
        val dn = norm(dx, dy)
        sx -= dx / dn * ACC * dt
        sy -= dy / dn * ACC * dt
        val sn = norm(sx, sy)
        val trim = sn.coerceAtMost(MAX_SPEED)
        sx = sx / sn * trim
        sy = sy / sn * trim
        shape.x += sx * dt
        shape.y += sy * dt
        update(shape)
        // check once a second
        if (time > checkTime + nanoSecond) {
            checkTime = time
            when (rnd.nextInt(10)) { // roll d20
                0 -> {
                    animateColor(shape) // wait a second & animate color
                    time = UI.awaitFrame() // and sync with next frame
                }
                1 -> { // random speed change
                    sx = rnd.nextSpeed()
                    sy = rnd.nextSpeed()
                }
            }
        }
    }
}

suspend fun AnimationModel.animateColor(shape: AnimatedShape) {
    val duration = 1e9f
    val startTime = System.nanoTime()
    val aColor = shape.color
    val bColor = rnd.nextColor()
    while (true) {
        val time = UI.awaitFrame()
        val b = (time - startTime) / duration
        if (b >= 1.0f) break
        val a = 1 - b
        shape.color = Color.rgb(
                (Color.red(bColor) * b + Color.red(aColor) * a).toInt(),
                (Color.green(bColor) * b + Color.green(aColor) * a).toInt(),
                (Color.blue(bColor) * b + Color.blue(aColor) * a).toInt()
        )
        update(shape)
    }
    shape.color = bColor
    update(shape)
}