package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.utils.GamePaths
import com.gdx.pingpong.utils.GameProperties

class Wall(world: World, type: Type) : Image(Texture(GamePaths.BALL_SRC)) {

    val body: Body
    val bodyDef: BodyDef

    companion object Factory {
        fun createSurroundingWalls(world: World): Set<Wall> {
            return Wall.Type.values().map { it -> Wall(world, it) }.toSet()
        }
    }

    init {
        when (type) {
            Type.DOWN -> setupDownWall()
            Type.UP -> setupUpWall()
            Type.LEFT -> setupLeftWall()
            Type.RIGHT -> setupRightWall()
        }

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set(x + width / 2, y + height / 2)

        body = world.createBody(bodyDef)
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
        ballShape.setAsBox(width / 2, height / 2)

        val fixtureDef = FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1.0f;

        body.createFixture(fixtureDef)
        ballShape.dispose()
    }

    enum class Type {
        UP, DOWN, LEFT, RIGHT
    }

}