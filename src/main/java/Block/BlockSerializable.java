package Block;

import java.io.Serializable;

/**
 * The BlockSerializable class represents a serializable version of a block.
 * It stores the row, column, and type information of a block for easy serialization and deserialization.
 */
public class BlockSerializable implements Serializable {
    public final int row;
    public final int column;
    public final int type;


    /**
     * Constructs a BlockSerializable object with specified row, column, and type.
     *
     * @param row    The row of the block.
     * @param column The column of the block.
     * @param type   The type of the block.
     */
    public BlockSerializable(int row , int column , int type) {
        this.row = row;
        this.column = column;
        this.type = type;

    }


}
