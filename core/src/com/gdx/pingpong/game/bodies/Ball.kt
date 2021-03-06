package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.game.bodies.utils.BodyType
import com.gdx.pingpong.game.bodies.utils.Box2dConverters.fromBox2d
import com.gdx.pingpong.game.bodies.utils.Box2dConverters.toBox2d
import com.gdx.pingpong.game.GamePaths
import com.gdx.pingpong.game.GameProperties.BALL_SPEED
import com.gdx.pingpong.game.GameProperties.VIRTUAL_HEIGHT
import com.gdx.pingpong.game.GameProperties.VIRTUAL_WIDTH

class Ball(world: World) : Image(Texture(GamePaths.BALL_SRC)) {

    private val body: Body
    private val bodyDef: BodyDef

    init {
        setPosition(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2)

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(toBox2d(x, width), toBox2d(y, height))

        body = world.createBody(bodyDef)
        body.userData = BodyType.BALL
        createBodyFixture()
        body.applyLinearImpulse(0.0f, BALL_SPEED, x, y, true)
    }

    fun updatePosition() {
        setPosition(fromBox2d(body.position.x, width), fromBox2d(body.position.y, height))
    }

    private fun createBodyFixture() {
        val ballShape = CircleShape()
        ballShape.radius = toBox2d(width)

        val fixtureDef = FixtureDef()
        fixtureDef.shape = ballShape
        fixtureDef.density = 0.1f
        fixtureDef.restitution = 1.0f

        body.createFixture(fixtureDef)
        ballShape.dispose()
    }

}