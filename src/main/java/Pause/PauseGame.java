package Pause;

public class PauseGame {
    public static boolean isPaused;

    public static boolean pauseGame(){
        isPaused = !isPaused;

        return isPaused;

    }

    public static void resetState() {
        isPaused = false;
    }


}
