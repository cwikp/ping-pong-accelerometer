package com.gdx.pingpong.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects
import com.gdx.pingpong.game.GamePaths
import com.gdx.pingpong.game.GameVariables
import com.gdx.pingpong.utils.ActorFactory

class OptionsScreen(game: PingPongGame) : BaseScreen(game) {

    val optionsLabel: Label
    val difficultyLabel: Label
    val difficultySlider: Slider
    val backButton: Button

    init {
        optionsLabel = ActorFactory.createLabel("OPTIONS", GameObjects.FONT_LOGO)
        difficultyLabel = ActorFactory.createLabel("Difficulty", GameObjects.FONT32)
        difficultySlider = Slider(0.01f, 0.4f, 0.01f, false, GameObjects.SKIN)
        backButton = ActorFactory.createTextButton(GamePaths.MENU_BUTTON_SRC, "BACK", GameObjects.FONT_MENU_BUTTONS)

        optionsLabel.setPosition((width - optionsLabel.prefWidth) / 2, height - optionsLabel.prefHeight - 60)
        difficultyLabel.setPosition((width - difficultyLabel.prefWidth) / 2 - 75, height / 2)
        difficultySlider.setPosition(width / 2, height / 2)
        backButton.setPosition((width - backButton.prefWidth) / 2, height / 2 - 200)
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
