package com.gdx.pingpong.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage

import com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.utils.GameProperties.FADEIN_TIME
import com.gdx.pingpong.utils.GameObjects

open class BaseScreen(game: PingPongGame) : Stage(), Screen {

    val game: PingPongGame = game;

    override fun show() {
        viewport = GameObjects.VIEWPORT
        root.color.a = 0f;
        root.addAction(fadeIn(FADEIN_TIME));
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.inputProcessor = this;
        act(Math.min(Gdx.graphics.deltaTime, 1 / 30f));
        super.draw();
        this.draw();
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true);
    }

    override fun pause() {
        throw UnsupportedOperationException()
    }

    override fun resume() {
        throw UnsupportedOperationException()
    }

    override fun hide() {
        throw UnsupportedOperationException()
    }

    override fun dispose() {
        throw UnsupportedOperationException()
    }

}