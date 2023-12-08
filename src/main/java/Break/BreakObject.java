package Break;

import javafx.scene.shape.Rectangle;

/**
 * The BreakObject class represents the breaking paddle in the game. The breaking paddle
 * is controlled by the player to bounce the ball and break blocks. This class provides
 * methods to get and set properties of the breaking paddle.
 */
public class BreakObject {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private double xBreak;
    private double centerBreakX;
    private double yBreak = 640.0f;
    private Rectangle rect;
    private final int halfBreakWidth = getBreakWidth() / 2;
    private boolean BreakMoveAllow = true;

    /**
     * Returns the value representing left direction.
     *
     * @return The value representing left direction.
     */
    public static int getLEFT() {
        return LEFT;
    }

    /**
     * Returns the value representing right direction.
     *
     * @return The value representing right direction.
     */
    public static int getRIGHT() {
        return RIGHT;
    }

    /**
     * Gets the x-coordinate of the breaking paddle.
     *
     * @return The x-coordinate of the breaking paddle.
     */
    public double getxBreak() {
        return xBreak;
    }

    /**
     * Sets the x-coordinate of the breaking paddle.
     *
     * @param xBreak The x-coordinate to set.
     */
    public void setxBreak(double xBreak) {
        this.xBreak = xBreak;
    }

    /**
     * Gets the center x-coordinate of the breaking paddle.
     *
     * @return The center x-coordinate of the breaking paddle.
     */
    public double getCenterBreakX() {
        return centerBreakX;
    }

    /**
     * Sets the center x-coordinate of the breaking paddle.
     *
     * @param centerBreakX The center x-coordinate to set.
     */
    public void setCenterBreakX(double centerBreakX) {
        this.centerBreakX = centerBreakX;
    }


    /**
     * Gets the y-coordinate of the breaking paddle.
     *
     * @return The y-coordinate of the breaking paddle.
     */
    public double getyBreak() {
        return yBreak;
    }

    /**
     * Sets the y-coordinate of the breaking paddle.
     *
     * @param yBreak The y-coordinate to set.
     */
    public void setyBreak(double yBreak) {
        this.yBreak = yBreak;
    }

    /**
     * Gets the width of the breaking paddle.
     *
     * @return The width of the breaking paddle.
     */
    public int getBreakWidth() {
        return 130;
    }

    /**
     * Gets the height of the breaking paddle.
     *
     * @return The height of the breaking paddle.
     */
    public int getBreakHeight() {
        return 30;
    }

    /**
     * Gets half of the width of the breaking paddle.
     *
     * @return Half of the width of the breaking paddle.
     */
    public int getHalfBreakWidth() {
        return halfBreakWidth;
    }

    /**
     * Checks if the breaking paddle movement is allowed.
     *
     * @return True if movement is allowed, false otherwise.
     */
    public boolean isBreakMoveAllow() {
        return BreakMoveAllow;
    }

    /**
     * Sets whether breaking paddle movement is allowed.
     *
     * @param breakMoveAllow True to allow movement, false otherwise.
     */
    public void setBreakMoveAllow(boolean breakMoveAllow) {
        BreakMoveAllow = breakMoveAllow;
    }

    /**
     * Gets the rectangle representing the breaking paddle.
     *
     * @return The rectangle representing the breaking paddle.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Sets the rectangle representing the breaking paddle.
     *
     * @param rect The rectangle to set.
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
