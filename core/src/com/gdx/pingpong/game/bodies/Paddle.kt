package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.utils.GamePaths
import com.gdx.pingpong.utils.GameProperties

class Paddle(world: World) : Image(Texture(GamePaths.PADDLE_SRC)) {

    val body: Body
    val bodyDef: BodyDef

    init {
        setPosition(GameProperties.VIRTUAL_WIDTH / 2, 60f)
        setSize(100f, 30f)

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.KinematicBody
        bodyDef.position.set(x + width / 2, y + height / 2)

        body = world.createBody(bodyDef)
        createBodyFixture()
    }

    //todo only temporary - to check collisions - delete this later
    fun setBodyPosition(x: Float, y: Float) {
        body.setTransform(x + width / 2, y + height / 2, body.angle)
        setPosition(x, y)
    }

    private fun createBodyFixture() {
        val paddleShape = PolygonShape()
        paddleShape.setAsBox(width / 2, height / 2)

        val fixtureDef = FixtureDef();
        fixtureDef.shape = paddleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1.0f;

        body.createFixture(fixtureDef)
        paddleShape.dispose()
    }

}