package LoadGameSaveGame;

import Block.Block;
import Block.BlockSerializable;
import Block.BlockObject;
import PlayGame.LevelObject;
import Ball.BallObject;
import Break.BreakObject;

import Score.Score;
import brickGame.Main;
import javafx.application.Platform;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The SaveGame class provides functionality to save the current state of the game.
 * It includes the state of various game objects such as levels, scores, and ball position.
 */
public class SaveGame {
    public static String savePath = "C:/tch_BrickGame_GameProgress_File/Your_last_saved_GameProgress_Is_Here.mdds";
    public static String savePathDir = "C:/tch_BrickGame_GameProgress_File/";

    /**
     * Saves the current game state to a file in a separate thread.
     *
     * @param main         The main application instance.
     * @param bo           The BallObject containing ball-related parameters.
     * @param blockobject  The BlockObject containing block-related parameters.
     * @param levelobject  The LevelObject containing level-related parameters.
     * @param breakobject  The BreakObject containing break-related parameters.
     */
    public void saveGame(Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject, BreakObject breakobject) {

        new Thread(() -> {
            new File(savePathDir).mkdirs();
            File file = new File(savePath);
            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(file));

                outputStream.writeInt(levelobject.getLevel());
                outputStream.writeInt(levelobject.getScore());
                outputStream.writeInt(levelobject.getHeart());
                outputStream.writeBoolean(levelobject.isGetHeart());
                outputStream.writeInt(0);
                outputStream.writeInt(levelobject.getRestartFromHeart());
                outputStream.writeInt(levelobject.getRestartFromLevel());
                outputStream.writeInt(levelobject.getRestartFromScore());
                outputStream.writeDouble(bo.getxBall());
                outputStream.writeDouble(bo.getyBall());
                outputStream.writeDouble(breakobject.getxBreak());
                outputStream.writeDouble(breakobject.getyBreak());
                outputStream.writeDouble(breakobject.getCenterBreakX());
                outputStream.writeLong(bo.getTime());
                outputStream.writeLong(bo.getGoldTime());
                outputStream.writeDouble(bo.getvX());


                outputStream.writeBoolean(levelobject.isExistHeartBlock());
                outputStream.writeBoolean(levelobject.isGoldStatus());
                outputStream.writeBoolean(bo.isGoDownBall());
                outputStream.writeBoolean(bo.isGoRightBall());
                outputStream.writeBoolean(bo.isCollideToBreak());
                outputStream.writeBoolean(bo.isCollideToBreakAndMoveToRight());
                outputStream.writeBoolean(bo.isCollideToRightWall());
                outputStream.writeBoolean(bo.isCollideToLeftWall());
                outputStream.writeBoolean(bo.isCollideToRightBlock());
                outputStream.writeBoolean(bo.isCollideToBottomBlock());
                outputStream.writeBoolean(bo.isCollideToLeftBlock());
                outputStream.writeBoolean(bo.isCollideToTopBlock());

                ArrayList<BlockSerializable> blockSerializables = new ArrayList<BlockSerializable>();
                if (!blockobject.getBlocks().isEmpty()) {
                    for (Block block : blockobject.getBlocks()) {
                        if (block.isDestroyed) {
                            continue;
                        }
                        blockSerializables.add(new BlockSerializable(block.row, block.column, block.type));
                    }
                }
                outputStream.writeObject(blockSerializables);


                Platform.runLater(() -> {
                    new Score().showMessage("Game Saved", main);
                });


            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();

    }
}
