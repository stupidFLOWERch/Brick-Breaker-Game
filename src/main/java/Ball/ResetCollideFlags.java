package Ball;


public class ResetCollideFlags {
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
