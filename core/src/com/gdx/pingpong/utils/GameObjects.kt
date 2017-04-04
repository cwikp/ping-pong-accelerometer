package com.gdx.pingpong.utils

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport;

object GameObjects {

    val VIEWPORT : Viewport = FitViewport(GameProperties.VIRTUAL_WIDTH, GameProperties.VIRTUAL_HEIGHT)
    val STAGE : Stage = Stage()

}
