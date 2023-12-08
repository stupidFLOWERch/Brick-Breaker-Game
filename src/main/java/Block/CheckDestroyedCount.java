package Block;

import PlayGame.NextLevel;
import PlayGame.LevelObject;
import Ball.BallObject;
import brickGame.Main;
import brickGame.GameEngine;

/**
 * The CheckDestroyedCount class is responsible for checking if all blocks have been destroyed in the game.
 * It also handles scoring and advancement to the next level based on the game state.
 *
 */
public class CheckDestroyedCount {

    /**
     * Checks the number of destroyed blocks and triggers actions based on game state.
     *
     * @param main          The Main class instance.
     * @param engine        The GameEngine instance.
     * @param bo            The BallObject instance.
     * @param blockobject   The BlockObject instance.
     * @param levelobject   The LevelObject instance.
     */
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

            // Advance to the next level if not reached the last level
            if (levelobject.getLevel() <= 18) {
                nextlevel.nextLevel(main, engine, bo, blockobject, levelobject);
            }

        }
    }
}
