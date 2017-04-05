package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.utils.GamePaths
import com.gdx.pingpong.utils.GameProperties

class Paddle(world: World) : Image(Texture(GamePaths.PADDLE_SRC)) {

    val UPPER_THRESHOLD: Float = 500f

    val body: Body
    val bodyDef: BodyDef

    init {
        setPosition(GameProperties.VIRTUAL_WIDTH / 2, 60f)
        setSize(100f, 30f)

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(x + width / 2, y + height / 2)

        body = world.createBody(bodyDef)
        createBodyFixture()
    }

    //todo only temporary - to check collisions - delete this later
    fun setBodyPosition(x: Float, y: Float) {
        body.setTransform(x + width / 2, y + height / 2, body.angle)
        setPosition(x, y)
    }

    fun move(speedX: Float, speedY: Float) {
        val speedY = if (Math.abs(speedX) > UPPER_THRESHOLD && Math.abs(speedY) < UPPER_THRESHOLD) 0.0f else speedY
        val speedX = if (Math.abs(speedY) > UPPER_THRESHOLD && Math.abs(speedX) < UPPER_THRESHOLD) 0.0f else speedX

        body.setLinearVelocity(speedX, speedY)
        setPosition(body.position.x - width / 2, body.position.y - height / 2)
    }

    private fun createBodyFixture() {
        val paddleShape = PolygonShape()
        paddleShape.setAsBox(width / 2, height / 2)

        val fixtureDef = FixtureDef()
        fixtureDef.shape = paddleShape
        fixtureDef.density = 0.1f
        fixtureDef.restitution = 1.0f

        body.createFixture(fixtureDef)
        paddleShape.dispose()
    }

}