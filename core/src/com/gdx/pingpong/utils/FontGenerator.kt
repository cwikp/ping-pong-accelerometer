package com.gdx.pingpong.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

object FontGenerator {

    fun generateFont(size: Int, color: Color, font: String): BitmapFont {
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal(font))
        val fontParameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        val fontCharacters = "abcdefghijklmnopqrstuvwxyząćęłńóśźżABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>"
        fontParameter.size = size
        fontParameter.color = color
        fontParameter.characters = fontCharacters
        val generatedFont = fontGenerator.generateFont(fontParameter)
        fontGenerator.dispose()
        return generatedFont
    }

}