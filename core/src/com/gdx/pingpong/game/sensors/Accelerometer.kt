package com.gdx.pingpong.game.sensors

import com.badlogic.gdx.Gdx

class Accelerometer {

    private val SCALE_X: Float = 15f
    private val SCALE_Y: Float = 3f
    private val MIN_X_THRESHOLD: Float = 5f
    private val MIN_Y_THRESHOLD: Float = 8f
    private val MAX_INPUT: Float = 10f

    private val initialValueX: Float
    private val initialValueY: Float
    private val initialValueZ: Float

    init {
        initialValueX = Gdx.input.accelerometerX
        initialValueY = Gdx.input.accelerometerY
        initialValueZ = Gdx.input.accelerometerZ
    }

    /**
     *  Since we are using phone in landscape mode, X axis is represented by Y axis
     */
    fun getX(): Float {
        val scaledValue = (Gdx.input.accelerometerY - initialValueY) * Math.abs(MAX_INPUT / (MAX_INPUT - initialValueY)) * SCALE_X
        return if (Math.abs(scaledValue) < MIN_X_THRESHOLD) 0.0f else scaledValue
    }

    /**
     *  Since we are using phone in landscape mode, Y axis is represented by X axis
     */
    fun getY(): Float {
        val scaledValue = -(Gdx.input.accelerometerX - initialValueX) * Math.abs(MAX_INPUT / (MAX_INPUT - initialValueX)) * SCALE_Y
        return if (Math.abs(scaledValue) < MIN_Y_THRESHOLD) 0.0f else scaledValue
    }

    fun getZ(): Float {
        return Gdx.input.accelerometerZ
    }

}