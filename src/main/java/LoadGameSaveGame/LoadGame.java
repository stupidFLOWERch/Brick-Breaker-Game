package LoadGameSaveGame;

import Block.Block;
import Block.BlockSerializable;
import Level.LevelObject;
import Ball.BallObject;
import Break.BreakObject;
import Block.BlockObject;
import User.Main;


import java.util.Random;

public class LoadGame{
    public long time;
    public long goldTime;
    private final Main main;
    private final BallObject bo;
    private final BreakObject breakobject;
    private final BlockObject blockobject;
    private final LevelObject levelobject;

    public LoadGame(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject){
        this.main = main;
        this.bo = bo;
        this.breakobject = breakobject;
        this.blockobject = blockobject;
        this.levelobject = levelobject;
    }

    public void loadGame() {

        Random random = new Random();

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
            main.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
