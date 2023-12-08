package brickGame;

import Break.BreakObject;
import brickGame.Main;

/**
 * The Move class handles the movement of the BreakObject in the game.
 * It provides methods to move the BreakObject left or right based on the specified direction.
 * The movement is performed in a separate thread to avoid blocking the main application thread.
 */
public class Move {
    private final Main main;


    /**
     * Constructs a new Move instance with the specified {@link Main} instance.
     *
     * @param main The {@link Main} instance.
     */
    public Move(Main main) {
        this.main = main;
    }

    /**
     * Moves the BreakObject in the left or right.
     * @param direction The direction of movement (use constants from {@link BreakObject}).
     */
    void move(final int direction) {
        if (main.getBreakobject().isBreakMoveAllow()) {
            new Thread(() -> {
                int sleepTime = 4;
                for (int i = 0; i < 30; i++) {
                    synchronized (main) {
                        if (!main.getBreakobject().isBreakMoveAllow()) {
                            return;
                        }
                        if (main.getBreakobject().getxBreak() == (main.getLevelobject().getSceneWidth() - main.getBreakobject().getBreakWidth()) && direction == BreakObject.getRIGHT()) {
                            return;
                        }
                        if (main.getBreakobject().getxBreak() == 0 && direction == BreakObject.getLEFT()) {
                            return;
                        }
                        if (direction == BreakObject.getRIGHT() && main.getBreakobject().getxBreak() < (main.getLevelobject().getSceneWidth() - main.getBreakobject().getBreakWidth())) {
                            main.getBreakobject().setxBreak(main.getBreakobject().getxBreak() + 1);
                        }
                        if (direction == BreakObject.getLEFT() && main.getBreakobject().getxBreak() > 0) {
                            main.getBreakobject().setxBreak(main.getBreakobject().getxBreak() - 1);
                        }
                        main.getBreakobject().setCenterBreakX(main.getBreakobject().getxBreak() + main.getBreakobject().getHalfBreakWidth());
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i >= 20) {
                        sleepTime = i;
                    }
                }
            }).start();
        }
    }
}