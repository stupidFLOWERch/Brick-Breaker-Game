package Block;

import brickGame.Bonus;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BlockObject {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private final ArrayList<Bonus> cheeses = new ArrayList<>();
    private Color[] colors = new Color[]{
            Color.MAGENTA,
            Color.RED,
            Color.GOLD,
            Color.CORAL,
            Color.AQUA,
            Color.VIOLET,
            Color.GREENYELLOW,
            Color.ORANGE,
            Color.PINK,
            Color.SLATEGREY,
            Color.YELLOW,
            Color.TOMATO,
            Color.TAN,
    };
    private Rectangle rect;
    private long time = 0;
    private long goldTime = 0;

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Bonus> getCheeses() {
        return cheeses;
    }

    public Color[] getColors() {
        return colors;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(long goldTime) {
        this.goldTime = goldTime;
    }
}
