package com.gdx.pingpong.game.sensors

import com.badlogic.gdx.Gdx

class Accelerometer {

    val SCALE: Float = 200f
    val MIN_THRESHOLD: Float = 200f
    val MAX_INPUT: Float = 10f

    val initialValueX: Float
    val initialValueY: Float
    val initialValueZ: Float

    init {
        initialValueX = Gdx.input.accelerometerX
        initialValueY = Gdx.input.accelerometerY
        initialValueZ = Gdx.input.accelerometerZ
    }

    fun getX(): Float {
        val scaledValue = -(Gdx.input.accelerometerX - initialValueX) * Math.abs(MAX_INPUT / (MAX_INPUT - initialValueX)) * SCALE
        return if (Math.abs(scaledValue) < MIN_THRESHOLD) 0.0f else scaledValue
    }

    fun getY(): Float {
        val scaledValue = (Gdx.input.accelerometerY - initialValueY) * Math.abs(MAX_INPUT / (MAX_INPUT - initialValueY)) * SCALE
        return if (Math.abs(scaledValue) < MIN_THRESHOLD) 0.0f else scaledValue
    }

    fun getZ(): Float {
        return Gdx.input.accelerometerZ * SCALE
    }

}