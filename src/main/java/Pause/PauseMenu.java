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

public class PauseMenu extends VBox {

    private Main main;  // Reference to the main game class

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


    private void resumeGame() {
        main.showPauseMenu.resumeGame(main);
    }

    private void exitGame() {
        main.showPauseMenu.exitGame();  // Call a method in your Main class to exit the game
    }

    public void restartLevel(Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject){
        this.main = main;
        RestartLevel restartlevel = new RestartLevel();
        restartlevel.restartLevel(main, bo, blockobject, levelobject);
    }

}

