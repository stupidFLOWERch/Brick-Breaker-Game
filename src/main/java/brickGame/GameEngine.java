package brickGame;

import javafx.animation.AnimationTimer;

/**
 * The GameEngine class manages the game loop and provides functionality to start, stop, and pause the game.
 * It uses an {@link OnAction} interface to define actions that should be performed during the game loop.
 */
public class GameEngine {

    private OnAction onAction;
    private boolean isStopped = true;
    private static boolean isPaused = false;
    private AnimationTimer animationTimer;

    /**
     * Sets the {@link OnAction} instance for handling game actions.
     *
     * @param onAction The {@link OnAction} instance.
     */
    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    /**
     * Starts the game loop, allowing the execution of game actions.
     */
    public void start() {
        isStopped = false;

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isStopped) {
                    stop();
                    return;
                }

                if (!isPaused) {
                    onAction.onUpdate();
                    onAction.onPhysicsUpdate();
                    onAction.onTime(System.currentTimeMillis());
                }
            }
        };
        animationTimer.start();
    }


    /**
     * Stops the game loop.
     */
    public void stop() {
        isStopped = true;

        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    /**
     * Sets the pause status of the game engine.
     *
     * @param paused True if the game is paused, false otherwise.
     */
    public static void setPaused(boolean paused) {
        isPaused = paused;
    }


    /**
     * Restarts the game engine with the specified {@link Main} instance.
     *
     * @param main The {@link Main} instance.
     */
    public static void restartGameEngine(Main main) {
        GameEngine engine = main.getEngine();
        engine.setOnAction(main);
        engine.start();
    }

    /**
     * The OnAction interface defines methods for handling game actions.
     */
    public interface OnAction {

        /**
         * Called on each update of the game loop.
         */
        void onUpdate();


        /**
         * Called on each physics update of the game loop.
         */
        void onPhysicsUpdate();

        /**
         * Called with the current time during the game loop.
         *
         * @param time The current time.
         */
        void onTime(long time);
    }
}
