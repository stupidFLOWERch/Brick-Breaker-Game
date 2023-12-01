package PlayGame;

import Block.BlockObject;
import Ball.BallObject;
import brickGame.Main;
import javafx.stage.Stage;
import Ball.ResetCollideFlags;


public class RestartGame {


    public void restartGame(Stage stage, Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        ResetCollideFlags resetcollideflags = new ResetCollideFlags();
        try {
            levelobject.setLevel(0);
            levelobject.setHeart(3);
            levelobject.setScore(0);
            bo.setvX(1.000);
            levelobject.setDestroyedBlockCount(0);
            resetcollideflags.resetCollideFlags(bo);
            bo.setGoDownBall(true);
            bo.setGoRightBall(true);
            levelobject.setGoldStatus(false);
            levelobject.setExistHeartBlock(false);
            levelobject.setGetHeart(false);

            bo.setTime(0);
            bo.setGoldTime(0);

            main.clearBlocks();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();
            blockobject.getTraps().clear();

            main.start(stage);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
