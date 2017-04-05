package com.gdx.pingpong.game.bodies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.gdx.pingpong.utils.GamePaths
import com.gdx.pingpong.utils.GameProperties

class Ball(world: World) : Image(Texture(GamePaths.BALL_SRC)) {

    val body: Body
    val bodyDef: BodyDef

    init {
        setPosition(GameProperties.VIRTUAL_WIDTH / 2, GameProperties.VIRTUAL_HEIGHT / 2)

        bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(x + width / 2, y + height / 2)

        body = world.createBody(bodyDef)
        createBodyFixture()
        body.applyLinearImpulse(0.0f, 1000.0f, x, y, true);
    }

    fun updatePosition() {
        setPosition(body.position.x - width / 2, body.position.y - height / 2)
    }

    private fun createBodyFixture() {
        val ballShape = PolygonShape()
        ballShape.setAsBox(width / 2, height / 2)

        val fixtureDef = FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.density = 0.0f;
        fixtureDef.restitution = 1.0f;

        body.createFixture(fixtureDef)
        ballShape.dispose()
    }

}