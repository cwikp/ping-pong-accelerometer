package com.gdx.pingpong.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.pingpong.utils.FontGenerator

object GameObjects {

    val VIEWPORT: Viewport = FitViewport(GameProperties.VIRTUAL_WIDTH, GameProperties.VIRTUAL_HEIGHT)
    val STAGE: Stage = Stage()
    val FONT64 = FontGenerator.generateFont(64, Color.WHITE, GamePaths.REGULAR_FONT);

}
