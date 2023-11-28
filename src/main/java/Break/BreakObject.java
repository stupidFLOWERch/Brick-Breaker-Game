package Break;

import javafx.scene.shape.Rectangle;

public class BreakObject {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private double xBreak;
    private double centerBreakX;
    private double yBreak = 640.0f;
    private Rectangle rect;
    private final int halfBreakWidth = getBreakWidth() / 2;
    private boolean BreakMoveAllow = true;

    public static int getLEFT() {
        return LEFT;
    }

    public static int getRIGHT() {
        return RIGHT;
    }

    public double getxBreak() {
        return xBreak;
    }

    public void setxBreak(double xBreak) {
        this.xBreak = xBreak;
    }

    public double getCenterBreakX() {
        return centerBreakX;
    }

    public void setCenterBreakX(double centerBreakX) {
        this.centerBreakX = centerBreakX;
    }

    public double getyBreak() {
        return yBreak;
    }

    public void setyBreak(double yBreak) {
        this.yBreak = yBreak;
    }

    public int getBreakWidth() {
        return 130;
    }

    public int getBreakHeight() {
        return 30;
    }

    public int getHalfBreakWidth() {
        return halfBreakWidth;
    }

    public boolean isBreakMoveAllow() {
        return BreakMoveAllow;
    }

    public void setBreakMoveAllow(boolean breakMoveAllow) {
        BreakMoveAllow = breakMoveAllow;
    }
    public Rectangle getRect() {
        return rect;
    }
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
