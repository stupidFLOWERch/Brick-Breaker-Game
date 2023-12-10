package Ball;

import Break.InitBreak;
import Score.Score;
import PlayGame.LevelObject;
import Break.BreakObject;
import Block.BlockObject;

import brickGame.Main;
import brickGame.GameEngine;

import javafx.stage.Stage;

/**
 * The SetPhysicsToBall class defines the physics behavior of the ball.
 * It handles collisions with walls, blocks, and the break object, adjusting the ball's position and velocity accordingly.
 */
public class SetPhysicsToBall {

    /**
     * Sets the physics behavior of the ball based on its interactions with the game elements.
     *
     * @param stage        The primary stage of the JavaFX application.
     * @param main         The main application instance.
     * @param engine       The game engine responsible for running the game loop.
     * @param bo           The BallObject representing the ball in the game.
     * @param breakobject  The BreakObject representing the break in the game.
     * @param blockobject  The BlockObject representing the blocks in the game.
     * @param levelobject  The LevelObject containing information about the game level.
     */
    public void setPhysicsToBall(Stage stage, Main main, GameEngine engine, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        ResetCollideFlags resetcollideflags = new ResetCollideFlags();
        InitBall initball = new InitBall();
        InitBreak initbreak = new InitBreak();
        synchronized (this) {
            // Check if the ball is below the game scene and adjust its behavior
            if (bo.getyBall() >= levelobject.getSceneHeight() - bo.getBallRadius() && bo.isGoDownBall()) {
                resetcollideflags.resetCollideFlags(bo);
                bo.setGoDownBall(false);
                if (levelobject.getLevel() == 18) {
                    levelobject.setGoldStatus(true);
                }
                if (!levelobject.isGoldStatus()) {
                    levelobject.setHeart(levelobject.getHeart() - 1);
                    new Score().show(levelobject.getSceneWidth() / 2.0, levelobject.getSceneHeight() / 2.0, -1, main);

                    if (levelobject.getHeart() == 0) {
                        new Score().showGameOver(stage, main, bo, blockobject, levelobject);
                        System.out.println("Lol so noob loss the game");
                        engine.stop();
                        return;
                    }
                    System.out.println("Ops, you lost a heart");
                    initball.initBall(main.getBo());
                    main.getBo().getBall().setCenterX(main.getBo().getxBall());
                    main.getBo().getBall().setCenterY(main.getBo().getyBall());
                    main.getBreakobject().setxBreak(185);
                    initbreak.initBreak(main.getBreakobject().getRect(), main.getBreakobject().getxBreak());

                }
            }


            // Adjust the ball's position based on its direction
            if (bo.isGoDownBall()) {
                bo.setyBall(bo.getyBall() + bo.getvY());
            } else {
                bo.setyBall(bo.getyBall() - bo.getvY());
            }

            if (bo.isGoRightBall()) {
                bo.setxBall(bo.getxBall() + bo.getvX());
            } else {
                bo.setxBall(bo.getxBall() - bo.getvX());
            }

            // Check for collision with the top of the screen
            if (bo.getyBall() <= bo.getBallRadius()) {
                resetcollideflags.resetCollideFlags(bo);
                bo.setGoDownBall(true);
                return;
            }
        }

        // Check for collision with the BreakObject
        if (bo.getyBall() >= breakobject.getyBreak() - bo.getBallRadius()) {

            if (bo.getxBall() >= breakobject.getxBreak() && bo.getxBall() <= breakobject.getxBreak() + breakobject.getBreakWidth()) {
                resetcollideflags.resetCollideFlags(bo);
                bo.setCollideToBreak(true);
                bo.setGoDownBall(false);

                double relation = (bo.getxBall() - breakobject.getCenterBreakX()) / ((double) breakobject.getBreakWidth() / 2);

                if (Math.abs(relation) <= 0.3) {
                    bo.setvX(Math.abs(relation));
                } else if (Math.abs(relation) > 0.3 && Math.abs(relation) <= 0.7) {
                    bo.setvX((Math.abs(relation) * 1.5) + (levelobject.getLevel() / 3.500));
                } else {
                    bo.setvX((Math.abs(relation) * 2) + (levelobject.getLevel() / 3.500));
                }

                // Set the direction of the ball based on the BreakObject's position
                if (bo.getxBall() - breakobject.getCenterBreakX() > 0) {
                    bo.setCollideToBreakAndMoveToRight(true);
                } else {
                    bo.setCollideToBreakAndMoveToRight(false);
                }

            }
        }

        // Check for collision with the right wall
        if (bo.getxBall() >= levelobject.getSceneWidth() - bo.getBallRadius()) {
            resetcollideflags.resetCollideFlags(bo);
            bo.setCollideToRightWall(true);
        }

        // Check for collision with the left wall
        if (bo.getxBall() <= bo.getBallRadius()) {
            resetcollideflags.resetCollideFlags(bo);
            bo.setCollideToLeftWall(true);
        }

        if (bo.isCollideToBreak()) {
            if (bo.isCollideToBreakAndMoveToRight()) {
                bo.setGoRightBall(true);
            } else {
                bo.setGoRightBall(false);
            }
        }


        // Wall Collide
        if (bo.isCollideToRightWall()) {
            bo.setGoRightBall(false);
        }

        if (bo.isCollideToLeftWall()) {
            bo.setGoRightBall(true);
        }

        //Block Collide

        if (bo.isCollideToRightBlock()) {
            bo.setGoRightBall(true);
        }

        if (bo.isCollideToLeftBlock()) {
            bo.setGoRightBall(false);
        }

        if (bo.isCollideToTopBlock()) {
            bo.setGoDownBall(false);
        }

        if (bo.isCollideToBottomBlock()) {
            bo.setGoDownBall(true);
        }

    }
}