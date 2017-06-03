package com.gdx.pingpong

import com.badlogic.gdx.Game
import com.gdx.pingpong.game.screens.GameEndScreen
import com.gdx.pingpong.game.screens.GameResult
import com.gdx.pingpong.game.screens.PingPongScreen
import com.gdx.pingpong.game.screens.SimpleMenuScreen

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

    fun showGameEndScreen(gameResult: GameResult){
        setScreen(GameEndScreen(this, gameResult))
    }
}
