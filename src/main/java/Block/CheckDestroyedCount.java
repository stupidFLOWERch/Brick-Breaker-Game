package Block;

import Level.NextLevel;
import Level.LevelObject;
import Ball.BallObject;
import User.Main;
import brickGame.GameEngine;

public class CheckDestroyedCount {
    public void checkDestroyedCount(Main main, GameEngine engine, BallObject bo, BlockObject blockobject, LevelObject levelobject) {

        NextLevel nextlevel = new NextLevel();
        if (levelobject.getDestroyedBlockCount() == blockobject.getBlocks().size()) {
            if (levelobject.isGetHeart() && levelobject.getHeart() > levelobject.getRestartFromHeart()) {
                System.out.println("Well done! You pass the level without losing any heart. +20 score for you");
                levelobject.setScore(levelobject.getScore()+20);
            }
            if (!levelobject.isGetHeart() && levelobject.getHeart() == levelobject.getRestartFromHeart()) {
                System.out.println("Well done! You pass the level without losing any heart. +20 score for you");
                levelobject.setScore(levelobject.getScore()+20);
            }

            levelobject.setGetHeart(false);
            if (levelobject.getLevel() <= 18) {
                nextlevel.nextLevel(main, engine, bo, blockobject, levelobject);
            }

        }
    }
}
