package com.gdx.pingpong.game.bodies.utils

import com.gdx.pingpong.game.bodies.Wall

enum class BodyType {
    PADDLE, BALL, WALL_DOWN, WALL_UP, WALL_LEFT, WALL_RIGHT;

    companion object Converter {
        fun getWallBodyType(wallType: Wall.WallType): BodyType =
                when (wallType) {
                    Wall.WallType.DOWN -> WALL_DOWN
                    Wall.WallType.UP -> WALL_UP
                    Wall.WallType.LEFT -> WALL_LEFT
                    Wall.WallType.RIGHT -> WALL_RIGHT
                }
    }
}