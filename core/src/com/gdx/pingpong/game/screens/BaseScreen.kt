package com.gdx.pingpong.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects
import com.gdx.pingpong.game.GameProperties.FADEIN_TIME

open class BaseScreen(protected val game: PingPongGame) : Stage(), Screen {

    override fun show() {
        viewport = GameObjects.VIEWPORT
        root.color.a = 0f
        root.addAction(fadeIn(FADEIN_TIME))
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.input.inputProcessor = this
        act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        super.draw()
        this.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }

}