package com.gdx.pingpong.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.gdx.pingpong.utils.FontGenerator

object GameObjects {

    val VIEWPORT: Viewport = FitViewport(GameProperties.VIRTUAL_WIDTH, GameProperties.VIRTUAL_HEIGHT)
    val STAGE: Stage = Stage()
    val FONT64 = FontGenerator.generateFont(64, Color.WHITE, GamePaths.REGULAR_FONT)
    val FONT32 = FontGenerator.generateFont(32, Color.WHITE, GamePaths.REGULAR_FONT)
    val FONT_LOGO = FontGenerator.generateFont(86, Color.WHITE, GamePaths.REGULAR_FONT)
    val FONT_MENU_BUTTONS = FontGenerator.generateFont(24, Color.WHITE, GamePaths.REGULAR_FONT)
    val SKIN = Skin(Gdx.files.internal(GamePaths.SKIN_SRC))
}
