package Block;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BlockObject {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private final ArrayList<Bonus> cheeses = new ArrayList<>();
    private final ArrayList<Trap> traps = new ArrayList<>();

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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Bonus> getCheeses() {
        return cheeses;
    }

    public ArrayList<Trap> getTraps() { return traps; }

    public Color[] getColors() {
        return colors;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }


}
