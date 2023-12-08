package Pause;

/**
 * The PauseGame class provides functionality to pause and resume the game.
 * It maintains the state of whether the game is currently paused.
 */
public class PauseGame {
    public static boolean isPaused;

    /**
     * Toggles the pause state of the game.
     *
     * @return The updated pause state after toggling.
     */
    public static boolean pauseGame(){
        isPaused = !isPaused;

        return isPaused;

    }

    /**
     * Resets the pause state to false, indicating that the game is not paused.
     */
    public static void resetState() {
        isPaused = false;
    }


}
