package Ball;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class InitBall {
    public void initBall(Circle ball, double x, double y) {
        BallObject bo = new BallObject();
        bo.setxBall(x);
        bo.setyBall(y);
        bo.setBall(ball);
        bo.getBall().setRadius(bo.getBallRadius());
        bo.getBall().setFill(new ImagePattern(new Image("ball.png")));
    }
}
