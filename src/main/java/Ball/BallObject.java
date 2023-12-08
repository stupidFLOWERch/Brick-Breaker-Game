package Ball;

import javafx.scene.shape.Circle;

/**
 * The BallObject class represents the ball in the Brick Game.
 * It encapsulates the properties and behaviors of the ball, such as its position,
 * direction, collision status, and movement velocities.
 *
 */
public class BallObject {

    private Circle ball;
    private double xBall;
    private double yBall;
    private boolean goDownBall = true;
    private boolean goRightBall = true;
    private boolean collideToBreak = false;
    private boolean collideToBreakAndMoveToRight = true;
    private boolean collideToRightWall = false;
    private boolean collideToLeftWall = false;
    private boolean collideToRightBlock = false;
    private boolean collideToBottomBlock = false;
    private boolean collideToLeftBlock = false;
    private boolean collideToTopBlock = false;
    private double vX = 1.000;
    private double vY = 1.000;
    private long goldTime = 0;
    private long time = 0;

    /**
     * Gets the radius of the ball.
     *
     * @return The radius of the ball.
     */
    public double getBallRadius() {
        return 10;
    }

    /**
     * Gets the JavaFX Circle object representing the ball.
     *
     * @return The Circle object that representing the ball.
     */
    public Circle getBall() {
        return ball;
    }

    /**
     * Sets the JavaFX Circle object that representing the ball.
     *
     * @param ball The Circle object that representing the ball.
     */
    public void setBall(Circle ball) {
        this.ball = ball;
    }

    /**
     * Gets the X-coordinate of the ball's position.
     *
     * @return The X-coordinate of the ball's position.
     */
    public double getxBall() {
        return xBall;
    }

    /**
     * Sets the X-coordinate of the ball's position.
     *
     * @param xBall The new X-coordinate of the ball's position.
     */
    public void setxBall(double xBall) {
        this.xBall = xBall;
    }

    /**
     * Gets the Y-coordinate of ball's position.
     *
     * @return The Y-coordinate of ball's position.
     */
    public double getyBall() {
        return yBall;
    }

    /**
     * Sets the Y-coordinate of the ball's position.
     *
     * @param yBall The new Y-coordinate of the ball's position.
     */
    public void setyBall(double yBall) {
        this.yBall = yBall;
    }

    public boolean isGoDownBall() {
        return goDownBall;
    }

    public void setGoDownBall(boolean goDownBall) {
        this.goDownBall = goDownBall;
    }

    public boolean isGoRightBall() {
        return goRightBall;
    }

    public void setGoRightBall(boolean goRightBall) {
        this.goRightBall = goRightBall;
    }

    public boolean isCollideToBreak() {
        return collideToBreak;
    }

    public void setCollideToBreak(boolean collideToBreak) {
        this.collideToBreak = collideToBreak;
    }

    public boolean isCollideToBreakAndMoveToRight() {
        return collideToBreakAndMoveToRight;
    }

    public void setCollideToBreakAndMoveToRight(boolean collideToBreakAndMoveToRight) {
        this.collideToBreakAndMoveToRight = collideToBreakAndMoveToRight;
    }

    public boolean isCollideToRightWall() {
        return collideToRightWall;
    }

    public void setCollideToRightWall(boolean collideToRightWall) {
        this.collideToRightWall = collideToRightWall;
    }

    public boolean isCollideToLeftWall() {
        return collideToLeftWall;
    }

    public void setCollideToLeftWall(boolean collideToLeftWall) {
        this.collideToLeftWall = collideToLeftWall;
    }

    public boolean isCollideToRightBlock() {
        return collideToRightBlock;
    }

    public void setCollideToRightBlock(boolean collideToRightBlock) {
        this.collideToRightBlock = collideToRightBlock;
    }

    public boolean isCollideToBottomBlock() {
        return collideToBottomBlock;
    }

    public void setCollideToBottomBlock(boolean collideToBottomBlock) {
        this.collideToBottomBlock = collideToBottomBlock;
    }

    public boolean isCollideToLeftBlock() {
        return collideToLeftBlock;
    }

    public void setCollideToLeftBlock(boolean collideToLeftBlock) {
        this.collideToLeftBlock = collideToLeftBlock;
    }

    public boolean isCollideToTopBlock() {
        return collideToTopBlock;
    }

    public void setCollideToTopBlock(boolean collideToTopBlock) {
        this.collideToTopBlock = collideToTopBlock;
    }

    /**
     * Gets the velocity in the X-direction.
     *
     * @return The velocity in the X-direction.
     */
    public double getvX() {
        return vX;
    }

    /**
     * Sets the velocity in the X-direction.
     *
     * @param vX The new velocity in the X-direction.
     */
    public void setvX(double vX) {
        this.vX = vX;
    }

    /**
     * Gets the velocity in the Y-direction.
     *
     * @return The velocity in the Y-direction.
     */
    public double getvY() {
        return vY;
    }


    /**
     * Gets the time duration for which the ball is in the gold state.
     *
     * @return The time duration for the gold state.
     */
    public long getGoldTime() {
        return goldTime;
    }


    /**
     * Sets the time duration for which the ball is in the gold state.
     *
     * @param goldTime The new gold state time duration.
     */
    public void setGoldTime(long goldTime) {
        this.goldTime = goldTime;
    }

    /**
     * Gets the overall time elapsed.
     *
     * @return The overall time elapsed.
     */
    public long getTime() {
        return time;
    }


    /**
     * Sets the overall time elapsed.
     *
     * @param time The new overall time elapsed.
     */
    public void setTime(long time) {
        this.time = time;
    }
}
