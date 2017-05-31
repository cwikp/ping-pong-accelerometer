package com.gdx.pingpong.game.bodies.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.gdx.pingpong.game.bodies.utils.BodyType.*
import com.gdx.pingpong.utils.GameProperties
import com.gdx.pingpong.utils.GameVariables


class BodyListener : ContactListener {
    override fun beginContact(contact: Contact) {
        val bodyA = contact.fixtureA.body.userData
        val bodyB = contact.fixtureB.body.userData

        if (bodyA.equals(WALL_DOWN) || bodyB.equals(WALL_DOWN)) {
            if (bodyA.equals(BALL) || bodyB.equals(BALL))
                GameVariables.opponentScore += 1
        }

        if (bodyA.equals(WALL_UP) || bodyB.equals(WALL_UP)) {
            if (bodyA.equals(BALL) || bodyB.equals(BALL))
                GameVariables.playerScore += 1
        }

        Gdx.app.log("score: ", "" + GameVariables.opponentScore);
        Gdx.app.log("beginContact", "between $bodyA and $bodyB");
    }

    override fun endContact(contact: Contact) {
        val bodyA = contact.fixtureA.body
        val bodyB = contact.fixtureB.body

        if (bodyA.userData.equals(PADDLE) || bodyB.userData.equals(PADDLE)) {
            if (bodyA.userData.equals(BALL) || bodyB.userData.equals(BALL)) {
                val body = if (bodyA.equals(BALL)) bodyA else bodyB
                val velocity = body.linearVelocity.cpy()
                val sign = if (velocity.y < 0) -1 else 1
                velocity.set(velocity.x, sign * GameProperties.BALL_SPEED)
                body.linearVelocity = velocity
            }
        }
    }

    override fun preSolve(p0: Contact?, p1: Manifold?) {

    }

    override fun postSolve(p0: Contact?, p1: ContactImpulse?) {

    }
}