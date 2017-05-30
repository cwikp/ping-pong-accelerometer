package com.gdx.pingpong.game.bodies

import com.gdx.pingpong.utils.GameProperties.PIXELS_TO_METERS

object BodyUtils {

    fun toBox2d(position: Float, size: Float) = (position + size / 2) / PIXELS_TO_METERS

    fun fromBox2d(position: Float, size: Float) = position * PIXELS_TO_METERS - size / 2

    fun toBox2d(size: Float) = (size / 2) / PIXELS_TO_METERS

}