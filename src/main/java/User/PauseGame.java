package User;

public class PauseGame {
    public static boolean isPaused;
    private static long elapsedGoldBallTime;

    public static boolean pauseGame(){
        isPaused = !isPaused;

        return isPaused;

    }


}
