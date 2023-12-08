package Block;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The BlockObject class represents a collection of blocks, cheeses, and traps in the Brick Game.
 * It manages the storage of blocks and provides access to the lists of blocks, cheeses, and traps.
 */
public class BlockObject {

    // Lists to store blocks, cheeses, and traps
    private final ArrayList<Block> blocks = new ArrayList<>();
    private final ArrayList<Bonus> cheeses = new ArrayList<>();
    private final ArrayList<Trap> traps = new ArrayList<>();

    // Array of colors for normal blocks
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
    /**
     * Gets the list of normal blocks.
     *
     * @return The list of normal blocks.
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * Gets the list of cheese bonuses.
     *
     * @return The list of cheese bonuses.
     */
    public ArrayList<Bonus> getCheeses() {
        return cheeses;
    }

    /**
     * Gets the list of trap bonuses.
     *
     * @return The list of trap bonuses.
     */
    public ArrayList<Trap> getTraps() { return traps; }

    /**
     * Gets the array of colors for normal blocks.
     *
     * @return The array of colors for normal blocks.
     */
    public Color[] getColors() {
        return colors;
    }

}
