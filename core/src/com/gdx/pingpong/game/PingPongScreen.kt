package com.gdx.pingpong.game

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.bodies.Ball
import com.gdx.pingpong.game.bodies.Paddle
import com.gdx.pingpong.game.bodies.Wall
import com.gdx.pingpong.game.sensors.Accelerometer
import com.gdx.pingpong.utils.GameProperties
import com.gdx.pingpong.utils.GameProperties.DIFFICULTY_LEVEL
import com.gdx.pingpong.utils.GameProperties.PIXELS_TO_METERS

class PingPongScreen(game: PingPongGame) : BaseScreen(game) {

    val world: World
    val playerPaddle: Paddle
    val opponentPaddle: Paddle
    val gameBall: Ball
    val downWall: Wall
    val walls: Set<Wall>
    val accelerometer: Accelerometer
    val debugRenderer: Box2DDebugRenderer
    val debugMatrix: Matrix4

    init {
        val gravity = Vector2(0f, 0f)
        world = World(gravity, true)
        gameBall = Ball(world)
        downWall = Wall(world, Wall.Type.DOWN)
        walls = Wall.createSurroundingWalls(world)
        playerPaddle = Paddle(world)
        opponentPaddle = Paddle(world)
        accelerometer = Accelerometer()
        debugRenderer = Box2DDebugRenderer()
        debugMatrix = batch.projectionMatrix.cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0.0f);
    }

    override fun show() {
        super.show()
        addListener(setupMainClickListener())
        addActor(gameBall)
        walls.forEach { addActor(it) }
        addActor(playerPaddle)
        addActor(opponentPaddle)
        opponentPaddle.setBodyPosition(gameBall.x, GameProperties.VIRTUAL_HEIGHT * 0.9f)
    }

    override fun render(delta: Float) {
        super.render(delta)
        world.step(1f / 60f, 6, 2)
        gameBall.updatePosition()
        opponentPaddle.move((gameBall.x - opponentPaddle.x) * DIFFICULTY_LEVEL, 0f)
        playerPaddle.move(accelerometer.getX(), accelerometer.getY())
        debugRenderer.render(world, debugMatrix);
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