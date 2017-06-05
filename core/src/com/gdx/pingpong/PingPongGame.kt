package com.gdx.pingpong

import com.badlogic.gdx.Game
import com.badlogic.gdx.audio.Music
import com.gdx.pingpong.game.screens.*
import com.gdx.pingpong.game.GameObjects

class PingPongGame : Game() {

    private lateinit var backgroundMusic: Music

    override fun create() {
        setBackgroundMusic()
        showMenuScreen()
    }

    fun showMenuScreen(){
        backgroundMusic.pause()
        setScreen(SimpleMenuScreen(this))
    }

    fun showPingPongScreen(){
        backgroundMusic.play()
        setScreen(PingPongScreen(this))
    }

    fun showOptionsScreen(){
        setScreen(OptionsScreen(this))
    }

    fun showGameEndScreen(gameResult: GameResult){
        setScreen(GameEndScreen(this, gameResult))
    }

    private fun setBackgroundMusic() {
        backgroundMusic = GameObjects.MUSIC_BACKGROUND
        backgroundMusic.isLooping = true
        backgroundMusic.volume = 0.10f
    }
}
