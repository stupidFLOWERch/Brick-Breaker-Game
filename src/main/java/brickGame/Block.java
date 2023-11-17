package brickGame;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

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


    public static int NO_HIT = -1;
    public static int HIT_RIGHT = 0;
    public static int HIT_BOTTOM = 1;
    public static int HIT_LEFT = 2;
    public static int HIT_TOP = 3;

    public static int BLOCK_NORMAL = 99;
    public static int BLOCK_CHOCO = 100;
    public static int BLOCK_STAR = 101;
    public static int BLOCK_HEART = 102;




    public Block(int row, int column, Color color, int type) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.type = type;

        draw();
    }

    private void draw() {
        x = (column * width) + paddingH;
        y = (row * height) + paddingTop;

        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(x);
        rect.setY(y);

        if (type == BLOCK_CHOCO) {
            Image image = new Image("choco.jpg");
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
        } else {
            Platform.runLater(() -> rect.setFill(color));
        }

    }


    public int checkHitToBlock(double xBall, double yBall) {

        double ballRadius = 10;
        double epsilon = 0.01 * height;
        double brickLeft = x;
        double brickRight = x + width;
        double brickTop = y;
        double brickBottom = y + height;

        if (isDestroyed) {
            return NO_HIT;
        }

        if (yBall  >= brickTop - epsilon && yBall  <= brickBottom + epsilon && xBall >= brickRight && xBall <= brickRight + ballRadius) {
            System.out.println("right");
            return HIT_RIGHT;
        }

        if (yBall >= brickTop - epsilon && yBall  <= brickBottom + epsilon && xBall <= brickLeft && xBall >= brickLeft - ballRadius) {
            System.out.println("left");
            return HIT_LEFT;
        }

        if (xBall  >= brickLeft - ballRadius && xBall <= brickRight + ballRadius && yBall >= brickTop && yBall <= brickTop + ballRadius + epsilon) {
            System.out.println("TOP");
            return HIT_TOP;
        }

        if (xBall  >= brickLeft - ballRadius && xBall  <= brickRight + ballRadius && yBall  <= brickBottom + epsilon && yBall >= brickBottom - ballRadius) {
            System.out.println("Bottom");
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
