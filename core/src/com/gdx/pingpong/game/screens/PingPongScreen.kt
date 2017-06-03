package com.gdx.pingpong.game.screens

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.gdx.pingpong.PingPongGame
import com.gdx.pingpong.game.GameObjects
import com.gdx.pingpong.game.GameProperties
import com.gdx.pingpong.game.GameProperties.PIXELS_TO_METERS
import com.gdx.pingpong.game.GameVariables
import com.gdx.pingpong.game.bodies.Ball
import com.gdx.pingpong.game.bodies.Paddle
import com.gdx.pingpong.game.bodies.Wall
import com.gdx.pingpong.game.bodies.utils.BodyListener
import com.gdx.pingpong.game.sensors.Accelerometer
import com.gdx.pingpong.utils.ActorFactory

class PingPongScreen(game: PingPongGame) : BaseScreen(game) {

    val world: World
    val playerPaddle: Paddle
    val opponentPaddle: Paddle
    val gameBall: Ball
    val walls: Set<Wall>
    val accelerometer: Accelerometer
    val collisionListener: ContactListener
    val debugRenderer: Box2DDebugRenderer
    val debugMatrix: Matrix4
    val playerScoreLabel: Label
    val opponentScoreLabel: Label

    init {
        val gravity = Vector2(0f, 0f)
        world = World(gravity, true)
        gameBall = Ball(world)
        walls = Wall.createSurroundingWalls(world)
        playerPaddle = Paddle(world)
        opponentPaddle = Paddle(world)
        playerScoreLabel = ActorFactory.createLabel("0", GameObjects.FONT64)
        opponentScoreLabel = ActorFactory.createLabel("0", GameObjects.FONT64)
        accelerometer = Accelerometer()
        collisionListener = BodyListener()
        debugRenderer = Box2DDebugRenderer()
        debugMatrix = batch.projectionMatrix.cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0.0f)
    }

    override fun show() {
        super.show()
        addListener(setupMainClickListener())
        addActor(gameBall)
        walls.forEach { addActor(it) }
        addActor(playerPaddle)
        addActor(opponentPaddle)
        addActor(playerScoreLabel)
        addActor(opponentScoreLabel)
        playerScoreLabel.setPosition(GameProperties.VIRTUAL_WIDTH / 2 - playerScoreLabel.prefWidth / 2, 100.0f)
        opponentScoreLabel.setPosition(GameProperties.VIRTUAL_WIDTH / 2 - opponentScoreLabel.prefWidth / 2, GameProperties.VIRTUAL_HEIGHT - opponentScoreLabel.prefHeight / 2 - 100.0f)
        opponentPaddle.setBodyPosition(gameBall.x, GameProperties.VIRTUAL_HEIGHT * 0.9f)
        world.setContactListener(collisionListener)
    }

    override fun render(delta: Float) {
        super.render(delta)
        world.step(1f / 60f, 6, 2)
        gameBall.updatePosition()
        opponentPaddle.move((gameBall.x - opponentPaddle.x) * GameVariables.difficultyLevel, 0f)
        playerPaddle.move(accelerometer.getX(), accelerometer.getY())
        playerScoreLabel.setText(Integer.toString(GameVariables.playerScore))
        opponentScoreLabel.setText(Integer.toString(GameVariables.opponentScore))
        checkScores()
//        debugRenderer.render(world, debugMatrix)  // debugger
    }

    override fun dispose() {
        super.dispose()
        world.dispose()
    }

    private fun checkScores() {
        if(GameVariables.playerScore >= GameProperties.GAME_ENDED_SCORE){
            game.showGameEndScreen(GameResult.WON)
        } else if (GameVariables.opponentScore >= GameProperties.GAME_ENDED_SCORE){
            game.showGameEndScreen(GameResult.LOST)
        }
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