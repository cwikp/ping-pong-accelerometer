package com.gdx.pingpong.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects
import com.gdx.pingpong.game.GameObjects.FONT_MENU_BUTTONS
import com.gdx.pingpong.game.GamePaths.MENU_BUTTON_SRC
import com.gdx.pingpong.utils.ActorFactory


class SimpleMenuScreen(game: PingPongGame) : BaseScreen(game) {

    private val play: Button
    private val exit: Button
    private val options: Button
    private val gameName: Label

    init {
        gameName = ActorFactory.createLabel("Ping Pong Redesigned", GameObjects.FONT_LOGO)
        play = createMenuButton("PLAY")
        options = createMenuButton("OPTIONS")
        exit = createMenuButton("EXIT")

        setActorsOnScreen()
    }

    fun setDependentActorPosition(baseActor: Actor, dependentActor: Actor, spaceX: Float, spaceY: Float) {
        val positionX = baseActor.x + spaceX
        val positionY = baseActor.y + spaceY
        dependentActor.setPosition(positionX, positionY)
    }

    override fun show() {
        super.show()
        addActor(play)
        addActor(exit)
        addActor(options)
        addActor(gameName)
    }

    override fun render(delta: Float) {
        super.render(delta)
        checkIfButtonWasPressed()
    }

    override fun dispose() {
        play.remove()
        exit.remove()
        options.remove()
        gameName.remove()
    }

    private fun setActorsOnScreen() {
        gameName.setPosition((width - gameName.prefWidth) / 2, height - gameName.prefHeight - 60)
        play.setPosition((width - play.prefWidth) / 2, height / 2)

        val buttonsSpaceX = 200f
        setDependentActorPosition(play, options, -buttonsSpaceX, 0f)
        setDependentActorPosition(play, exit, buttonsSpaceX, 0f)
    }

    private fun createMenuButton(propertyValue: String): Button {
        return ActorFactory.createTextButton(MENU_BUTTON_SRC, propertyValue, FONT_MENU_BUTTONS)
    }

    private fun checkIfButtonWasPressed() {
        if (play.isPressed) {
            game.showPingPongScreen()
            dispose()
        } else if (options.isPressed) {
            game.showOptionsScreen()
            dispose()
        } else if (exit.isPressed) {
            dispose()
            Gdx.app.exit()
        }
    }

}