package brickGame;

import User.Main;
import javafx.animation.AnimationTimer;

public class GameEngine {

    private OnAction onAction;
    private boolean isStopped = true;
    private static boolean isPaused = false;
    private AnimationTimer animationTimer;

    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    public void setFps(int fps) {

    }

    public void start() {
        isStopped = false;

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isStopped) {
                    stop();
                    return;
                }

                if(!isPaused) {
                    onAction.onUpdate();
                    onAction.onPhysicsUpdate();
                    onAction.onTime(System.currentTimeMillis());
                }
            }
        }.start();
    }

    public void stop() {
        isStopped = true;

        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    public static void setPaused(boolean paused) {
        isPaused = paused;
    }

    public static void restartGameEngine(Main main) {
        GameEngine engine = main.getEngine();
        engine.setOnAction(main);
        engine.setFps(120);
        engine.start();
    }

    public interface OnAction {
        void onUpdate();

        void onPhysicsUpdate();

        void onTime(long time);
    }
}
