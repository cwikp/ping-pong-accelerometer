package com.gdx.pingpong

import com.badlogic.gdx.Game
import com.gdx.pingpong.game.screens.*

class PingPongGame : Game() {

    override fun create() {
        showMenuScreen()
    }

    fun showMenuScreen(){
        setScreen(SimpleMenuScreen(this))
    }

    fun showPingPongScreen(){
        setScreen(PingPongScreen(this))
    }

    fun showOptionsScreen(){
        setScreen(OptionsScreen(this))
    }

    fun showGameEndScreen(gameResult: GameResult){
        setScreen(GameEndScreen(this, gameResult))
    }
}
