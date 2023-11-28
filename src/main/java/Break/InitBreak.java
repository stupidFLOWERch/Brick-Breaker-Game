package Break;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class InitBreak {
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
