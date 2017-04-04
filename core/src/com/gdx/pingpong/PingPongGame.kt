package com.gdx.pingpong

import com.badlogic.gdx.Game
import com.gdx.pingpong.game.PingPongScreen

class PingPongGame : Game() {

    override fun create() {
        showPingPongScreen()
    }

    fun showPingPongScreen(){
        setScreen(PingPongScreen(this))
    }
}
