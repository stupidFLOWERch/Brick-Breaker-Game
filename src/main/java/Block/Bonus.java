package Block;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Random;

/**
 * The Bonus class represents cheese.
 * It includes information about the position, creation time, and whether the bonus has been taken.
 *
 */

public class Bonus implements Serializable {
    public Rectangle cheese;

    public double x;
    public double y;
    public long timeCreated;
    public boolean taken = false;


    /**
     * Constructs a Bonus object and initializes the cheese at specified row and column.
     *
     * @param row    The row where the bonus item is located.
     * @param column The column where the bonus item is located.
     */
    public Bonus(int row, int column) {
        initializeCheese(row, column);
    }

    /**
     * Initializes cheese at specified row and column.
     *
     * @param row    The row where the cheese is located.
     * @param column The column where the cheese is located.
     */
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
