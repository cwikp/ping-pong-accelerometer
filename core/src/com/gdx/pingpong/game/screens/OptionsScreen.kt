package com.gdx.pingpong.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects
import com.gdx.pingpong.game.GamePaths
import com.gdx.pingpong.game.GameProperties.VIRTUAL_HEIGHT
import com.gdx.pingpong.game.GameProperties.VIRTUAL_WIDTH
import com.gdx.pingpong.game.GameVariables
import com.gdx.pingpong.utils.ActorFactory

class OptionsScreen(game: PingPongGame) : BaseScreen(game) {

    private val optionsLabel: Label
    private val difficultyLabel: Label
    private val difficultySlider: Slider
    private val backButton: Button

    init {
        optionsLabel = ActorFactory.createLabel("OPTIONS", GameObjects.FONT_LOGO)
        difficultyLabel = ActorFactory.createLabel("Difficulty", GameObjects.FONT32)
        difficultySlider = Slider(0.01f, 0.4f, 0.01f, false, GameObjects.SKIN)
        backButton = ActorFactory.createTextButton(GamePaths.MENU_BUTTON_SRC, "BACK", GameObjects.FONT_MENU_BUTTONS)

        optionsLabel.setPosition((VIRTUAL_WIDTH - optionsLabel.prefWidth) / 2, VIRTUAL_HEIGHT - optionsLabel.prefHeight - 60)
        difficultyLabel.setPosition((VIRTUAL_WIDTH - difficultyLabel.prefWidth) / 2 - 75, VIRTUAL_HEIGHT / 2)
        difficultySlider.setPosition(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2)
        backButton.setPosition((VIRTUAL_WIDTH - backButton.prefWidth) / 2, VIRTUAL_HEIGHT / 2 - 200)
    }

    override fun show() {
        super.show()
        addActor(optionsLabel)
        addActor(difficultyLabel)
        addActor(difficultySlider)
        addActor(backButton)
        difficultySlider.value = GameVariables.difficultyLevel
    }

    override fun render(delta: Float) {
        super.render(delta)
        checkIfButtonWasPressed()
    }

    override fun dispose() {
        backButton.remove()
        optionsLabel.remove()
    }

    private fun checkIfButtonWasPressed() {
        if (backButton.isPressed) {
            GameVariables.difficultyLevel = difficultySlider.value
            game.showMenuScreen()
            dispose()
        }
    }
}
