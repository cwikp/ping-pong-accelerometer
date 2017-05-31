package com.gdx.pingpong.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label

object ActorFactory {

    fun createImage(imagePath: String): Image {
        return Image(Texture(Gdx.files.local((imagePath))));
    }

    fun createLabel(text: String, font: BitmapFont): Label {
        val labelStyle = Label.LabelStyle(font, font.getColor());
        return Label(text, labelStyle);
    }

}