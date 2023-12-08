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
     *
     * @param ball The JavaFX Circle object representing the ball.
     * @param x    The initial X-coordinate of the ball's position.
     * @param y    The initial Y-coordinate of the ball's position.
     */
    public void initBall(Circle ball, double x, double y) {
        BallObject bo = new BallObject();
        bo.setxBall(x);
        bo.setyBall(y);
        bo.setBall(ball);
        bo.getBall().setRadius(bo.getBallRadius());
        bo.getBall().setFill(new ImagePattern(new Image("ball.png")));
    }
}
