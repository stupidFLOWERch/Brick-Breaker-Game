package PlayGame;

import Ball.BallObject;
import Ball.ResetCollideFlags;
import Block.BlockObject;
import PlayGame.LevelObject;
import Sound.Win;
import Score.Score;
import brickGame.Main;
import PlayGame.StartGame;
import brickGame.GameEngine;
import javafx.application.Platform;

/**
 * The NextLevel class handles the transition to the next level in the game.
 */
public class NextLevel {


    /**
     * Advances the game to next level, resetting relevant parameters and restarting the game engine.
     *
     * @param main          The main game class.
     * @param engine        The game engine.
     * @param bo            The ball object.
     * @param blockobject   The block object.
     * @param levelobject   The level object.
     */
    public void nextLevel(Main main, GameEngine engine, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        StartGame startgame = new StartGame(main);
        ResetCollideFlags resetcollidflags = new ResetCollideFlags();
        levelobject.setRestartFromLevel(levelobject.getLevel() + 1);
        levelobject.setRestartFromHeart(levelobject.getHeart());
        levelobject.setRestartFromScore(levelobject.getScore());

        bo.setvX(1.000);
        // stop the engine
        engine.stop();
        // reset flags and game state
        resetcollidflags.resetCollideFlags(bo);
        bo.setGoDownBall(false);
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
                startgame.startGame();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (levelobject.getLevel() == 18) {
            levelobject.setRestartFromScore(0);
            levelobject.setRestartFromHeart(3);
            levelobject.setRestartFromLevel(1);
            Platform.runLater(() -> {
                new Score().showCongrat(main, levelobject);
                new Win();
                main.root.getStyleClass().add("win");
                System.out.println("You pass all the levels!");
                GameEngine.setPaused(true);
            });
        }
    }

}
