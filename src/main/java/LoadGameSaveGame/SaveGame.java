package LoadGameSaveGame;

import Block.Block;
import Block.BlockSerializable;
import Block.BlockObject;
import Level.LevelObject;
import Ball.BallObject;
import Break.BreakObject;

import Score.Score;
import User.Main;
import javafx.application.Platform;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveGame {
    public static String savePath = "C:/save/save.mdds";
    public static String savePathDir = "C:/save/";
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
