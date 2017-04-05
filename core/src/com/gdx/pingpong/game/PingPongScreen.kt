package com.gdx.pingpong.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.bodies.Ball
import com.gdx.pingpong.game.bodies.Paddle
import com.gdx.pingpong.game.bodies.Wall
import com.gdx.pingpong.game.sensors.Accelerometer

class PingPongScreen(game: PingPongGame) : BaseScreen(game) {

    val world: World
    val playerPaddle: Paddle
    val gameBall: Ball
    val downWall: Wall
    val walls: Set<Wall>
    val accelerometer: Accelerometer

    init {
        val gravity = Vector2(0f, 0f)
        world = World(gravity, true)
        gameBall = Ball(world)
        downWall = Wall(world, Wall.Type.DOWN)
        walls = Wall.createSurroundingWalls(world)
        playerPaddle = Paddle(world)
        accelerometer = Accelerometer()
    }

    override fun show() {
        super.show()
        addListener(setupMainClickListener())
        addActor(gameBall)
        walls.forEach { addActor(it) }
        addActor(playerPaddle)
    }

    override fun render(delta: Float) {
        super.render(delta)
        world.step(1f / 60f, 6, 2)
        gameBall.updatePosition()
        playerPaddle.move(accelerometer.getY(), accelerometer.getX())
    }

    override fun dispose() {
        super.dispose()
        world.dispose()
    }

    private fun setupMainClickListener(): ClickListener {
        return object : ClickListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                playerPaddle.setBodyPosition(x, y)
                return super.touchDown(event, x, y, pointer, button)
            }
        }
    }

}