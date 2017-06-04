package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.game.bodies.utils.BodyType
import com.gdx.pingpong.game.bodies.utils.Box2dConverters.toBox2d
import com.gdx.pingpong.game.GamePaths
import com.gdx.pingpong.game.GameProperties

class Wall(world: World, wallType: WallType) : Image(Texture(GamePaths.BALL_SRC)) {

    private val body: Body
    private val bodyDef: BodyDef

    companion object Factory {
        fun createSurroundingWalls(world: World): Set<Wall> {
            return Wall.WallType.values().map { it -> Wall(world, it) }.toSet()
        }
    }

    init {
        when (wallType) {
            WallType.DOWN -> setupDownWall()
            WallType.UP -> setupUpWall()
            WallType.LEFT -> setupLeftWall()
            WallType.RIGHT -> setupRightWall()
        }

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set(toBox2d(x, width), toBox2d(y, height))

        body = world.createBody(bodyDef)
        body.userData = BodyType.getWallBodyType(wallType)
        createBodyFixture()
    }

    private fun setupDownWall() {
        setPosition(0f, 0f)
        setSize(GameProperties.VIRTUAL_WIDTH, GameProperties.WALL_THICKNESS)
    }

    private fun setupUpWall() {
        setPosition(0f, GameProperties.VIRTUAL_HEIGHT - GameProperties.WALL_THICKNESS)
        setSize(GameProperties.VIRTUAL_WIDTH, GameProperties.WALL_THICKNESS)
    }

    private fun setupRightWall() {
        setPosition(GameProperties.VIRTUAL_WIDTH - GameProperties.WALL_THICKNESS, 0f)
        setSize(GameProperties.WALL_THICKNESS, GameProperties.VIRTUAL_HEIGHT)
    }

    private fun setupLeftWall() {
        setPosition(0f, 0f)
        setSize(GameProperties.WALL_THICKNESS, GameProperties.VIRTUAL_HEIGHT)
    }

    private fun createBodyFixture() {
        val ballShape = PolygonShape()
        ballShape.setAsBox(toBox2d(width), toBox2d(height))

        val fixtureDef = FixtureDef()
        fixtureDef.shape = ballShape
        fixtureDef.density = 100f
        fixtureDef.restitution = 1.0f

        body.createFixture(fixtureDef)
        ballShape.dispose()
    }

    enum class WallType {
        UP, DOWN, LEFT, RIGHT
    }

}