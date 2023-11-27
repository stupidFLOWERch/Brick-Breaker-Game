package Block;

import brickGame.LevelObject;

import java.util.ArrayList;
import java.util.Random;

public class InitBlock {
    private final Random random = new Random();

    public void initBlock(int level, ArrayList<Block> blocks) {
        LevelObject levelobject = new LevelObject();
        BlockObject blockobject = new BlockObject();
        levelobject.setLevel(level);

        synchronized (this) {

            int type;
            if (levelobject.getLevel() < 18) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < levelobject.getLevel() + 1; j++) {
                        int r = random.nextInt(500);
                        if (r % 5 == 0) {
                            continue;
                        }


                        if (r % 10 == 1) {
                            type = Block.BLOCK_CHEESE;
                        } else if (r % 10 == 2) {
                            if (!levelobject.isExistHeartBlock()) {
                                type = Block.BLOCK_HEART;
                                levelobject.setExistHeartBlock(true);
                            } else {
                                type = Block.BLOCK_NORMAL;
                            }
                        } else if (r % 10 == 3) {
                            type = Block.BLOCK_STAR;
                        } else if (levelobject.getLevel() > 5 && r % 10 == 4) {
                            type = Block.BLOCK_TRAP;
                        }else {
                            type = Block.BLOCK_NORMAL;
                        }
                        blocks.add(new Block(j, i, blockobject.getColors()[r % (blockobject.getColors().length)], type));
                    }
                }
            }
            if (levelobject.getLevel() == 18) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 10; j++) {

                        type = Block.BLOCK_CHEESE;
                        blocks.add(new Block(j, i, blockobject.getColors()[1 % (blockobject.getColors().length)], type));
                    }
                }
            }
        }
    }
}
