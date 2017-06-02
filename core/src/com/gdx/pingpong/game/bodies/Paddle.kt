package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.game.bodies.utils.BodyType
import com.gdx.pingpong.game.bodies.utils.Box2dConverters.fromBox2d
import com.gdx.pingpong.game.bodies.utils.Box2dConverters.toBox2d
import com.gdx.pingpong.game.GamePaths
import com.gdx.pingpong.game.GameProperties

class Paddle(world: World) : Image(Texture(GamePaths.PADDLE_SRC)) {

    val UPPER_THRESHOLD: Float = 50f

    val body: Body
    val bodyDef: BodyDef

    init {
        setPosition(GameProperties.VIRTUAL_WIDTH / 2, 60f)
        setSize(100f, 30f)

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.fixedRotation = true
        bodyDef.position.set(toBox2d(x, width), toBox2d(y, height))

        body = world.createBody(bodyDef)
        body.userData = BodyType.PADDLE
        createBodyFixture()
    }

    //todo only temporary - to check collisions - delete this later
    fun setBodyPosition(x: Float, y: Float) {
        body.setTransform(toBox2d(x, width), toBox2d(y, height), body.angle)
        setPosition(x, y)
    }

    fun move(speedX: Float, speedY: Float) {
        val speedY = if (belowThreshold(speedX, speedY)) 0.0f else speedY
        val speedX = if (belowThreshold(speedY, speedX)) 0.0f else speedX

        body.setLinearVelocity(speedX, speedY)
        setPosition(fromBox2d(body.position.x, width), fromBox2d(body.position.y, height))
    }

    private fun createBodyFixture() {
        val paddleShape = PolygonShape()
        paddleShape.setAsBox(toBox2d(width), toBox2d(height))

        val fixtureDef = FixtureDef()
        fixtureDef.shape = paddleShape
        fixtureDef.density = 100f
        fixtureDef.restitution = 1.0f

        body.createFixture(fixtureDef)
        paddleShape.dispose()
    }

    private fun belowThreshold(otherAxisSpeed: Float, thisAxisSpeed: Float)
            = Math.abs(otherAxisSpeed) > UPPER_THRESHOLD && Math.abs(thisAxisSpeed) < UPPER_THRESHOLD

}