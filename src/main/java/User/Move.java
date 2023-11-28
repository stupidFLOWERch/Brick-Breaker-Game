package User;

public class Move {
    private final Main main;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;

    public Move(Main main) {
        this.main = main;
    }

    void move(final int direction) {
        if (main.getBreakobject().isBreakMoveAllow()) {
            new Thread(() -> {
                int sleepTime = 4;
                for (int i = 0; i < 30; i++) {
                    synchronized (main) {
                        if (!main.getBreakobject().isBreakMoveAllow()) {
                            return;
                        }
                        if (main.getBreakobject().getxBreak() == (main.getLevelobject().getSceneWidth() - main.getBreakobject().getBreakWidth()) && direction == RIGHT) {
                            return;
                        }
                        if (main.getBreakobject().getxBreak() == 0 && direction == LEFT) {
                            return;
                        }
                        if (direction == RIGHT && main.getBreakobject().getxBreak() < (main.getLevelobject().getSceneWidth() - main.getBreakobject().getBreakWidth())) {
                            main.getBreakobject().setxBreak(main.getBreakobject().getxBreak() + 1);
                        }
                        if (direction == LEFT && main.getBreakobject().getxBreak() > 0) {
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