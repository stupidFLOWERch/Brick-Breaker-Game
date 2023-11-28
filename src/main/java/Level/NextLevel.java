package Level;

import Ball.BallObject;
import Block.BlockObject;
import Sound.Win;
import UI.Score;
import User.Main;
import brickGame.GameEngine;
import javafx.application.Platform;

public class NextLevel {

    public void nextLevel(Main main, GameEngine engine, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        levelobject.setRestartFromLevel(levelobject.getLevel() + 1);
        levelobject.setRestartFromHeart(levelobject.getHeart());
        levelobject.setRestartFromScore(levelobject.getScore());

        bo.setvX(1.000);
        // stop the engine
        engine.stop();
        // reset flags and game state
        main.resetCollideFlags();
        bo.setGoDownBall(true);
        bo.setGoRightBall(true);
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);


        bo.setGoldTime(0);
        bo.setTime(0);

        Platform.runLater(() -> {
            main.root.getChildren().clear();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();
            blockobject.getTraps().clear();
            levelobject.setDestroyedBlockCount(0);

            try {
                main.startGame();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (levelobject.getLevel() == 18) {
            levelobject.setRestartFromScore(0);
            levelobject.setRestartFromHeart(3);
            levelobject.setRestartFromLevel(1);
            Platform.runLater(() -> {
                new Score().showCongrat(main);
                new Win();
                main.root.getStyleClass().add("win");
                System.out.println("You pass all the levels!");
                GameEngine.setPaused(true);
            });
        }
    }

}
