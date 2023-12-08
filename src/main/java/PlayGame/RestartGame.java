package PlayGame;

import Block.BlockObject;
import Ball.BallObject;
import brickGame.Main;
import javafx.stage.Stage;
import Ball.ResetCollideFlags;

/**
 * The RestartGame class handles the process of restarting the game.
 */
public class RestartGame {


    /**
     * Restarts the game, resetting relevant parameters and clearing the game elements.
     *
     * @param stage         The main stage of the application.
     * @param main          The main game class.
     * @param bo            The ball object.
     * @param blockobject   The block object.
     * @param levelobject   The level object.
     */
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
