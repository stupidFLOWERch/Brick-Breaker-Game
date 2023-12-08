package Ball;

/**
 * The ResetCollideFlags class provides a method to reset collision flags for a ball object in the Brick Game.
 * It resets various collision flags to their initial state, allowing proper collision detection in the game.
 *
 */
public class ResetCollideFlags {

    /**
     * Resets the collision flags for a given BallObject.
     *
     * @param bo The BallObject for which collision flags that need to be reset.
     */
    public void resetCollideFlags(BallObject bo) {

        bo.setCollideToBreak(false);
        bo.setCollideToBreakAndMoveToRight(false);
        bo.setCollideToRightWall(false);
        bo.setCollideToLeftWall(false);

        bo.setCollideToRightBlock(false);
        bo.setCollideToBottomBlock(false);
        bo.setCollideToLeftBlock(false);
        bo.setCollideToTopBlock(false);
    }
}
