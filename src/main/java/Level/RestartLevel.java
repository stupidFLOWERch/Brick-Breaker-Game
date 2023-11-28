package Level;

import Ball.ResetCollideFlags;
import User.Main;
import Ball.BallObject;
import Block.BlockObject;

public class RestartLevel {

    public void restartLevel(Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject) {

        ResetCollideFlags resetcollideflags = new ResetCollideFlags();
        levelobject.setFromRestartGame(true);
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
        levelobject.setLevel(levelobject.getRestartFromLevel());
        levelobject.setHeart(levelobject.getRestartFromHeart());
        levelobject.setScore(levelobject.getRestartFromScore());
        levelobject.setLoadFromSave(false);
        main.showPauseMenu.resumeGame(main);

        main.startGame();
    }

}
