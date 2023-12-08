package Block;

import Ball.BallObject;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * The Block class represents a block in the Brick Game. Blocks can have different types such as normal, cheese, star, heart, or trap.
 * It handles the drawing of blocks, their positions, and the logic for checking block collisions with the ball.
 */
public class Block implements Serializable {
    private static final Block block = new Block(-1, -1, Color.TRANSPARENT, 99);

    public int row;
    public int column;
    public boolean isDestroyed = false;
    private final Color color;
    public int type;
    public int x;
    public int y;
    private final int width = 100;
    private final int height = 30;
    private final int paddingTop = height * 2;
    private final int paddingH = 50;
    public Rectangle rect;

    // Constants for hit detection
    public static int NO_HIT = -1;
    public static int HIT_RIGHT = 0;
    public static int HIT_BOTTOM = 1;
    public static int HIT_LEFT = 2;
    public static int HIT_TOP = 3;

    // Constants for block types
    public static int BLOCK_NORMAL = 99;
    public static int BLOCK_CHEESE = 100;
    public static int BLOCK_STAR = 101;
    public static int BLOCK_HEART = 102;
    public static int BLOCK_TRAP = 103;



    /**
     * Constructs a Block with the specified row, column, color, and type.
     *
     * @param row    The row position of the block.
     * @param column The column position of the block.
     * @param color  The color of the block.
     * @param type   The type of the block.
     */
    public Block(int row, int column, Color color, int type) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.type = type;

        draw();
    }

    /**
     * Draws the block based on its type and color.
     */
    private void draw() {
        x = (column * width) + paddingH;
        y = (row * height) + paddingTop;

        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(x);
        rect.setY(y);

        if (type == BLOCK_CHEESE) {
            Image image = new Image("cheese.jpg");
            ImagePattern pattern = new ImagePattern(image);
            Platform.runLater(() -> rect.setFill(pattern));
        } else if (type == BLOCK_HEART) {
            Image image = new Image("heart.jpg");
            ImagePattern pattern = new ImagePattern(image);
            Platform.runLater(() -> rect.setFill(pattern));
        } else if (type == BLOCK_STAR) {
            Image image = new Image("star.jpg");
            ImagePattern pattern = new ImagePattern(image);
            Platform.runLater(() -> rect.setFill(pattern));
        } else if (type == BLOCK_TRAP) {
            Image image = new Image("trap.png");
            ImagePattern pattern = new ImagePattern(image);
            Platform.runLater(() -> rect.setFill(pattern));
        }else {
            Platform.runLater(() -> rect.setFill(color));
        }

    }

    /**
     * Checks the hit location of the ball concerning the block.
     *
     * @param xBall The x-coordinate of the ball.
     * @param yBall The y-coordinate of the ball.
     * @return An integer indicating the hit location.
     */
    public int checkHitToBlock(double xBall, double yBall) {
        BallObject bo = new BallObject();

        double epsilon = 0.01 * height;
        double brickLeft = x;
        double brickRight = x + width;
        double brickTop = y;
        double brickBottom = y + height;

        if (isDestroyed) {
            return NO_HIT;
        }

        if (yBall  >= brickTop - epsilon && yBall  <= brickBottom + epsilon && xBall >= brickRight && xBall <= brickRight + bo.getBallRadius()) {
            return HIT_RIGHT;
        }

        if (yBall >= brickTop - epsilon && yBall  <= brickBottom + epsilon && xBall <= brickLeft && xBall >= brickLeft - bo.getBallRadius()) {
            return HIT_LEFT;
        }

        if (xBall  >= brickLeft - bo.getBallRadius() && xBall <= brickRight + bo.getBallRadius() && yBall >= brickTop - bo.getBallRadius() && yBall <= brickTop + bo.getBallRadius() + epsilon) {
            return HIT_TOP;
        }

        if (xBall  >= brickLeft - bo.getBallRadius() && xBall  <= brickRight + bo.getBallRadius() && yBall  <= brickBottom + bo.getBallRadius() && yBall >= brickBottom - bo.getBallRadius() - epsilon) {
            return HIT_BOTTOM;

        }

        return NO_HIT;
    }

    public static int getPaddingTop() {
        return block.paddingTop;
    }

    public static int getPaddingH() {
        return block.paddingH;
    }

    public static int getHeight() {
        return block.height;
    }

    public static int getWidth() {
        return block.width;
    }

}
