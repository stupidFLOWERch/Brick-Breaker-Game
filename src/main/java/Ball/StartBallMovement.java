package Ball;


import brickGame.Main;

public class StartBallMovement {

    private Main main;
    public void startBallMovement(Main main){
        main.getBo().setBallMovementAllowed(true);

    }
}
