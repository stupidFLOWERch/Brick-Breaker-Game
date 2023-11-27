package Block;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Random;

public class Trap implements Serializable {
    public Rectangle mousetrap;

    public double x;
    public double y;
    public long timeCreated;
    public boolean taken = false;

    public Trap(int row, int column) {
        initializeTrap(row, column);
    }

    public void initializeTrap(int row, int column) {
        x = (column * (Block.getWidth())) + Block.getPaddingH() + ((double) Block.getWidth() / 2) - 15;
        y = (row * (Block.getHeight())) + Block.getPaddingTop() + ((double) Block.getHeight() / 2) - 15;

        mousetrap = new Rectangle();
        mousetrap.setWidth(30);
        mousetrap.setHeight(30);
        mousetrap.setX(x);
        mousetrap.setY(y);

        String url;
        if (new Random().nextInt(20) % 2 == 0) {
            url = "trap1.png";
        } else {
            url = "trap2.png";
        }

        mousetrap.setFill(new ImagePattern(new Image(url)));
    }
}
