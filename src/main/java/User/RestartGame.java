package User;

import Block.BlockObject;
import Level.LevelObject;
import Ball.BallObject;
import javafx.stage.Stage;


public class RestartGame {

    public RestartGame() {
    }

    public void restartGame(Stage stage, Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        try {
            levelobject.setLevel(0);
            levelobject.setHeart(3);
            levelobject.setScore(0);
            bo.setvX(1.000);
            levelobject.setDestroyedBlockCount(0);
            main.resetCollideFlags();
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
