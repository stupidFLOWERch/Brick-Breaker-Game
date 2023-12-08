package LoadGameSaveGame;


import Block.Block;
import Block.BlockSerializable;
import Block.Bonus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The LoadSave class provides functionality to load saved game data and initialize game state.
 * It reads data from a saved game file and populates the relevant variables and objects.
 */

public class LoadSave {
    public boolean isExistHeartBlock;
    public boolean isGoldStatus;
    public boolean goDownBall;
    public boolean goRightBall;
    public boolean collideToBreak;
    public boolean collideToBreakAndMoveToRight;
    public boolean collideToRightWall;
    public boolean collideToLeftWall;
    public boolean collideToRightBlock;
    public boolean collideToBottomBlock;
    public boolean collideToLeftBlock;
    public boolean collideToTopBlock;
    public int level;
    public int score;
    public int heart;
    public boolean getHeart;
    public int destroyedBlockCount;
    public double xBall;
    public double yBall;
    public double xBreak;
    public double yBreak;
    public double centerBreakX;
    public long time;
    public long goldTime;
    public double vX;
    public int restartFromScore;
    public int restartFromLevel;
    public int restartFromHeart;
    public ArrayList<BlockSerializable> blocks = new ArrayList<BlockSerializable>();

    /**
     * Reads the saved game state from a file, populates game variables, and initializes game objects.
     */
    public void read() {

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream((SaveGame.savePath)));


            level = inputStream.readInt();
            score = inputStream.readInt();
            heart = inputStream.readInt();
            getHeart = inputStream.readBoolean();
            destroyedBlockCount = inputStream.readInt();
            restartFromHeart = inputStream.readInt();
            restartFromLevel = inputStream.readInt();
            restartFromScore = inputStream.readInt();
            xBall = inputStream.readDouble();
            yBall = inputStream.readDouble();
            xBreak = inputStream.readDouble();
            yBreak = inputStream.readDouble();
            centerBreakX = inputStream.readDouble();
            time = inputStream.readLong();
            goldTime = inputStream.readLong();
            vX = inputStream.readDouble();


            isExistHeartBlock = inputStream.readBoolean();
            isGoldStatus = inputStream.readBoolean();
            goDownBall = inputStream.readBoolean();
            goRightBall = inputStream.readBoolean();
            collideToBreak = inputStream.readBoolean();
            collideToBreakAndMoveToRight = inputStream.readBoolean();
            collideToRightWall = inputStream.readBoolean();
            collideToLeftWall = inputStream.readBoolean();
            collideToRightBlock = inputStream.readBoolean();
            collideToBottomBlock = inputStream.readBoolean();
            collideToLeftBlock = inputStream.readBoolean();
            collideToTopBlock = inputStream.readBoolean();


            try {
                blocks = (ArrayList<BlockSerializable>) inputStream.readObject();
                initializeBonusObjects(blocks);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

    }

    /**
     * Initializes cheese based on the provided serialized block data.
     *
     * @param blockSerializables The serialized block data containing information about cheese.
     */
    private void initializeBonusObjects(ArrayList<BlockSerializable> blockSerializables) {
        for (BlockSerializable ser : blockSerializables) {
            if (ser.type == Block.BLOCK_CHEESE) {
                Bonus bonus = new Bonus(ser.row, ser.column);
                bonus.initializeCheese(ser.row, ser.column);
            }
        }
    }

    /**
     * Checks if the file exists at the specified path.
     *
     * @param savePath The path to the saved file.
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    public static boolean checkfile(String savePath) {
        Path path = Paths.get(savePath);
        return Files.exists(path) && Files.isRegularFile(path);
    }


}

