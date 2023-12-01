package Ball;

import Score.Score;
import PlayGame.LevelObject;
import Break.BreakObject;
import Block.BlockObject;

import brickGame.Main;
import brickGame.GameEngine;

import javafx.stage.Stage;


public class SetPhysicsToBall {
    public void setPhysicsToBall(Stage stage, Main main, GameEngine engine, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        ResetCollideFlags resetcollideflags = new ResetCollideFlags();

        synchronized (this) {
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
                }
            }

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

            if (bo.getyBall() <= bo.getBallRadius()) {
                resetcollideflags.resetCollideFlags(bo);
                bo.setGoDownBall(true);
                return;
            }
        }

        if (bo.getyBall() >= breakobject.getyBreak() - bo.getBallRadius()) {
            //System.out.println("Colide1");
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

                if (bo.getxBall() - breakobject.getCenterBreakX() > 0) {
                    bo.setCollideToBreakAndMoveToRight(true);
                } else {
                    bo.setCollideToBreakAndMoveToRight(false);
                }
                //System.out.println("Colide2");
            }
        }

        if (bo.getxBall() >= levelobject.getSceneWidth() - bo.getBallRadius()) {
            resetcollideflags.resetCollideFlags(bo);
            bo.setCollideToRightWall(true);
        }

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

        //Wall Colide

        if (bo.isCollideToRightWall()) {
            bo.setGoRightBall(false);
        }

        if (bo.isCollideToLeftWall()) {
            bo.setGoRightBall(true);
        }

        //Block Colide

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