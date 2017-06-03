package com.gdx.pingpong.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects.FONT_LOGO
import com.gdx.pingpong.game.GameObjects.FONT_MENU_BUTTONS
import com.gdx.pingpong.game.GamePaths.MENU_BUTTON_SRC
import com.gdx.pingpong.game.GameVariables
import com.gdx.pingpong.utils.ActorFactory

class GameEndScreen(game: PingPongGame, result: GameResult) : BaseScreen(game) {

    val resultLabel: Label
    val continueButton: Button

    init {
        resultLabel = ActorFactory.createLabel("", FONT_LOGO)
        continueButton = ActorFactory.createTextButton(MENU_BUTTON_SRC, "CONTINUE", FONT_MENU_BUTTONS)

        when (result) {
            GameResult.LOST -> gameLost()
            GameResult.WON -> gameWon()
        }
        resultLabel.setPosition((width - resultLabel.prefWidth) / 2, height - resultLabel.prefHeight / 2 - 50)
        continueButton.setPosition((width - continueButton.prefWidth) / 2, height / 2 - 100)
    }

    override fun show() {
        super.show()
        addActor(resultLabel)
        addActor(continueButton)
        resetScore()
//        game.getBackgroundMusic().stop()
    }

    override fun render(delta: Float) {
        super.render(delta)
        checkIfButtonWasPressed()
    }

    override fun dispose() {
        continueButton.remove()
        resultLabel.remove()
    }

    private fun resetScore() {
        GameVariables.opponentScore = 0
        GameVariables.playerScore = 0
    }

    private fun gameWon() {
        resultLabel.setText("Game Won!\n Congratulations!")
    }

    private fun gameLost() {
        resultLabel.setText("Game Lost!\n Try next time!")
    }

    private fun checkIfButtonWasPressed() {
        if (continueButton.isPressed) {
            game.showMenuScreen()
            dispose()
        }
    }

}

