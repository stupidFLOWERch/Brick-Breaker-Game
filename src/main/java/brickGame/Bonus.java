package brickGame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Random;

public class Bonus implements Serializable {
    public Rectangle choco;

    public double x;
    public double y;
    public long timeCreated;
    public boolean taken = false;

    public Bonus(int row, int column) {
        initializeChoco(row, column);
    }

    public void initializeChoco(int row, int column) {
        x = (column * (Block.getWidth())) + Block.getPaddingH() + ((double) Block.getWidth() / 2) - 15;
        y = (row * (Block.getHeight())) + Block.getPaddingTop() + ((double) Block.getHeight() / 2) - 15;

        choco = new Rectangle();
        choco.setWidth(30);
        choco.setHeight(30);
        choco.setX(x);
        choco.setY(y);

        String url;
        if (new Random().nextInt(20) % 2 == 0) {
            url = "cheese1.png";
        } else {
            url = "cheese2.png";
        }

        choco.setFill(new ImagePattern(new Image(url)));
    }
}
