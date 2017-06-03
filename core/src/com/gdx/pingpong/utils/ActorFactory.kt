package com.gdx.pingpong.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton


object ActorFactory {

    fun createImage(imagePath: String): Image {
        return Image(Texture(Gdx.files.local((imagePath))))
    }

    fun createLabel(text: String, font: BitmapFont): Label {
        val labelStyle = Label.LabelStyle(font, font.color)
        return Label(text, labelStyle)
    }

    fun createTextButton(fileName: String, text: String, textFont: BitmapFont): Button {
        val style = TextButton.TextButtonStyle()
        style.up = createImage(fileName).drawable
        style.font = textFont
        addOptionalStyles(fileName, style)
        return TextButton(text, style)
    }

    private fun addOptionalStyles(fileName: String, style: Button.ButtonStyle) {
        val clickedImagePath = addSuffixBeforeChar(fileName, "_clicked", '.')
        if (fileExists(clickedImagePath)) {
            style.down = createImage(clickedImagePath).drawable
        }
        val overImagePath = addSuffixBeforeChar(fileName, "_over", '.')
        if (fileExists(overImagePath)) {
            style.over = createImage(overImagePath).drawable
        }
        val checkedImagePath = addSuffixBeforeChar(fileName, "_checked", '.')
        if (fileExists(checkedImagePath)) {
            style.checked = createImage(checkedImagePath).drawable
        }
    }

    private fun addSuffixBeforeChar(path: String, suffix: String, charAfter: Char): String {
        val extensionIndex = path.lastIndexOf(charAfter)
        return path.substring(0, extensionIndex) + suffix + path.substring(extensionIndex)
    }

    private fun fileExists(path: String): Boolean {
        return Gdx.files.local(path).exists()
    }

}