package Ball;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The InitBall class initializes the properties of the ball.
 * It sets the position, radius, and fill pattern of the ball using a JavaFX Circle object.
 *
 */
public class InitBall {

    /**
     * Initializes the properties of the ball, including its position, radius, and fill pattern.
     * @param bo The BallObject to be initialized.
     */
    public void initBall(BallObject bo) {

        bo.setxBall(250);
        bo.setyBall(630);
        bo.getBall().setRadius(bo.getBallRadius());
        bo.getBall().setFill(new ImagePattern(new Image("ball.png")));
        bo.setBallMovementAllowed(false);
    }
}
