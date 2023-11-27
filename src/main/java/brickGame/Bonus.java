package brickGame;

import Block.Block;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Random;

public class Bonus implements Serializable {
    public Rectangle cheese;

    public double x;
    public double y;
    public long timeCreated;
    public boolean taken = false;

    public Bonus(int row, int column) {
        initializeCheese(row, column);
    }

    public void initializeCheese(int row, int column) {
        x = (column * (Block.getWidth())) + Block.getPaddingH() + ((double) Block.getWidth() / 2) - 15;
        y = (row * (Block.getHeight())) + Block.getPaddingTop() + ((double) Block.getHeight() / 2) - 15;

        cheese = new Rectangle();
        cheese.setWidth(30);
        cheese.setHeight(30);
        cheese.setX(x);
        cheese.setY(y);

        String url;
        if (new Random().nextInt(20) % 2 == 0) {
            url = "cheese1.png";
        } else {
            url = "cheese2.png";
        }

        cheese.setFill(new ImagePattern(new Image(url)));
    }
}
