package com.gdx.pingpong.game.bodies.utils

import com.gdx.pingpong.game.GameProperties.PIXELS_TO_METERS

object Box2dConverters {

    fun toBox2d(position: Float, size: Float) = (position + size / 2) / PIXELS_TO_METERS

    fun fromBox2d(position: Float, size: Float) = position * PIXELS_TO_METERS - size / 2

    fun toBox2d(size: Float) = (size / 2) / PIXELS_TO_METERS

}