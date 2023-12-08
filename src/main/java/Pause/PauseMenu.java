package Pause;


import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;

import PlayGame.LevelObject;
import brickGame.Main;
import PlayGame.RestartLevel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The PauseMenu class represents the pause menu of the game, providing options to resume the game,
 * exit the game, and restart the current level.
 */
public class PauseMenu extends VBox {

    private Main main;  // Reference to the main game class

    /**
     * Constructs a new PauseMenu instance.
     *
     * @param main          The main application instance.
     * @param bo            The BallObject instance representing the ball.
     * @param blockobject   The BlockObject instance representing the blocks.
     * @param levelobject   The LevelObject instance representing the game level.
     * @param breakobject   The BreakObject instance representing the breaks.
     */
    public PauseMenu(Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject, BreakObject breakobject) {
        this.main = main;

        // Create buttons or other UI elements for your pause menu
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(event -> resumeGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitGame());

        Button restartButton = new Button("Restart Level");
        restartButton.setOnAction(event -> restartLevel(main, bo, blockobject, levelobject));

        setSpacing(20);

        // Set the position of PauseMenu to the center of the screen
        setTranslateX((260 - getWidth()) / 2);
        setTranslateY((450 - getHeight()) / 2);
        getChildren().addAll(resumeButton, exitButton, restartButton);
        setAlignment(Pos.CENTER);
        // Customize the appearance of the pause menu if needed
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20;");
    }


    /**
     * Resumes the game from the pause menu.
     */
    private void resumeGame() {
        main.showPauseMenu.resumeGame(main);
    }

    /**
     * Exits the game from the pause menu.
     */
    private void exitGame() {
        main.showPauseMenu.exitGame();  // Call a method in your Main class to exit the game
    }

    /**
     * Restarts current level from the pause menu.
     *
     * @param main          The main application instance.
     * @param bo            The BallObject instance representing the game ball.
     * @param blockobject   The BlockObject instance representing the game blocks.
     * @param levelobject   The LevelObject instance representing the game level.
     */
    public void restartLevel(Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject){
        this.main = main;
        RestartLevel restartlevel = new RestartLevel();
        restartlevel.restartLevel(main, bo, blockobject, levelobject);
    }

}

