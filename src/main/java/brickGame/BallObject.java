package brickGame;

import javafx.scene.shape.Circle;

public class BallObject {

    private Circle ball;
    private double xBall;
    private double yBall;

    public BallObject() {
    }

    public double getBallRadius() {
        return 10;
    }

    public Circle getBall() {
        return ball;
    }

    public void setBall(Circle ball) {
        this.ball = ball;
    }

    public double getxBall() {
        return xBall;
    }

    public void setxBall(double xBall) {
        this.xBall = xBall;
    }

    public double getyBall() {
        return yBall;
    }

    public void setyBall(double yBall) {
        this.yBall = yBall;
    }
}
