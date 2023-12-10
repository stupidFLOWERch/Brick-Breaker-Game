package Ball;


import brickGame.Main;

/**
 * This class is responsible for initiating the movement of the ball in the game.
 * It provides a method to start the ball movement by setting the appropriate flag.
 */
public class StartBallMovement {

    /**
     * Initiates the movement of the ball in the game by allowing ball movement.
     * This method sets the ball movement flag to {@code true}.
     *
     * @param main The Main class instance associated with the game.
     *             It should not be {@code null}.
     */
    public void startBallMovement(Main main){
        main.getBo().setBallMovementAllowed(true);

    }
}
