package LoadGameSaveGame;

import Block.Block;
import Block.BlockSerializable;
import PlayGame.LevelObject;
import Ball.BallObject;
import Break.BreakObject;
import Block.BlockObject;
import brickGame.Main;
import PlayGame.StartGame;


import java.util.Random;

/**
 * The LoadGame class provides functionality to load a saved game state and resume the game.
 * It reads data from a saved game file and sets the game state accordingly.
 */
public class LoadGame{
    public long time;
    public long goldTime;
    private final Main main;
    private final BallObject bo;
    private final BreakObject breakobject;
    private final BlockObject blockobject;
    private final LevelObject levelobject;

    /**
     * Constructs a {@code LoadGame} instance with references to the main game objects.
     *
     * @param main        The main game object.
     * @param bo          The ball object.
     * @param breakobject The break object.
     * @param blockobject The block object.
     * @param levelobject The level object.
     */
    public LoadGame(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject){
        this.main = main;
        this.bo = bo;
        this.breakobject = breakobject;
        this.blockobject = blockobject;
        this.levelobject = levelobject;
    }

    /**
     * Loads the saved game state, initializes game objects, and starts the game.
     */
    public void loadGame() {

        Random random = new Random();
        StartGame startgame = new StartGame(main);
        LoadSave loadSave = new LoadSave();
        loadSave.read();


        levelobject.setExistHeartBlock(loadSave.isExistHeartBlock);
        levelobject.setGoldStatus(loadSave.isGoldStatus);
        levelobject.setGetHeart(loadSave.getHeart);
        bo.setGoDownBall(loadSave.goDownBall);
        bo.setGoRightBall(loadSave.goRightBall);
        bo.setCollideToBreak(loadSave.collideToBreak);
        bo.setCollideToBreakAndMoveToRight(loadSave.collideToBreakAndMoveToRight);
        bo.setCollideToRightWall(loadSave.collideToRightWall);
        bo.setCollideToLeftWall(loadSave.collideToLeftWall);
        bo.setCollideToRightBlock(loadSave.collideToRightBlock);
        bo.setCollideToBottomBlock(loadSave.collideToBottomBlock);
        bo.setCollideToLeftBlock(loadSave.collideToLeftBlock);
        bo.setCollideToTopBlock(loadSave.collideToTopBlock);
        levelobject.setLevel(loadSave.level);
        levelobject.setScore(loadSave.score);
        levelobject.setHeart(loadSave.heart);
        levelobject.setRestartFromLevel(loadSave.restartFromLevel);
        levelobject.setRestartFromHeart(loadSave.restartFromHeart);
        levelobject.setRestartFromScore(loadSave.restartFromScore);
        levelobject.setDestroyedBlockCount(loadSave.destroyedBlockCount);
        bo.setxBall(loadSave.xBall);
        bo.setyBall(loadSave.yBall);
        breakobject.setxBreak(loadSave.xBreak);
        breakobject.setyBreak(loadSave.yBreak);
        breakobject.setCenterBreakX(loadSave.centerBreakX);
        time = loadSave.time;
        goldTime = loadSave.goldTime;
        bo.setvX(loadSave.vX);

        main.clearBlocks();

        blockobject.getBlocks().clear();
        blockobject.getCheeses().clear();
        blockobject.getTraps().clear();

        for (BlockSerializable ser : loadSave.blocks) {
            int r = random.nextInt(200);
            blockobject.getBlocks().add(new Block(ser.row, ser.column, blockobject.getColors()[r % blockobject.getColors().length], ser.type));
        }


        try {
            levelobject.setLoadFromSave(true);
            startgame.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
