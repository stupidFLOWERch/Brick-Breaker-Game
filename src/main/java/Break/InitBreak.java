package Break;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * The InitBreak class is responsible for initializing the paddle.
 * It sets up the properties of the paddle, such as its position, dimensions,
 * and visual appearance using a rectangle.
 */
public class InitBreak {

    /**
     * Initializes the paddle using the provided rectangle and x-coordinate.
     *
     * @param rectangle The rectangle representing the paddle.
     * @param x         The x-coordinate of the paddle.
     */
    public void initBreak(Rectangle rectangle, double x) {
        BreakObject breakobject = new BreakObject();
        breakobject.setRect(rectangle);
        breakobject.getRect().setWidth(breakobject.getBreakWidth());
        breakobject.getRect().setHeight(breakobject.getBreakHeight());
        breakobject.setxBreak(x);
        breakobject.getRect().setX(breakobject.getxBreak());
        breakobject.getRect().setY(breakobject.getyBreak());

        ImagePattern pattern = new ImagePattern(new Image("block.jpg"));
        breakobject.getRect().setFill(pattern);
    }
}
