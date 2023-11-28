package UI;


import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import Level.LevelObject;
import User.Main;
import Level.RestartLevel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseMenu extends VBox {

    private Main main;  // Reference to the main game class
    private final Stage stage;
    private BallObject bo;
    private BreakObject breakobject;
    private BlockObject blockobject;
    private LevelObject levelobject;

    public PauseMenu(Stage stage , Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        this.main = main;
        this.bo = bo;
        this.breakobject = breakobject;
        this.blockobject = blockobject;
        this.levelobject = levelobject;
        this.stage = stage;

        // Create buttons or other UI elements for your pause menu
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(event -> resumeGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitGame());

        Button restartButton = new Button("Restart Level");
        restartButton.setOnAction(event -> restartLevel(main, bo, breakobject, blockobject, levelobject));

        setSpacing(20);

        // Set the position of PauseMenu to the center of the screen
        setTranslateX((400 - getWidth()) / 2);
        setTranslateY((600 - getHeight()) / 2);
        getChildren().addAll(resumeButton, exitButton, restartButton);
        setAlignment(Pos.CENTER);
        // Customize the appearance of the pause menu if needed
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20;");
    }

    private void initialize() {
        // Create buttons or other UI elements for your pause menu
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(event -> resumeGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitGame());

        Button restartButton = new Button("Restart Level");
        restartButton.setOnAction(event -> restartLevel(main, bo, breakobject, blockobject, levelobject));

        setSpacing(20);

        // Set the position of PauseMenu to the center of the screen
        setTranslateX((400 - getWidth()) / 2);
        setTranslateY((600 - getHeight()) / 2);
        getChildren().addAll(resumeButton, exitButton, restartButton);
        setAlignment(Pos.CENTER);
        // Customize the appearance of the pause menu if needed
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20;");
    }

    private void resumeGame() {
        main.resumeGame();
    }

    private void exitGame() {
        main.exitGame();  // Call a method in your Main class to exit the game
    }

    public void restartLevel(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject){
        this.main = main;
        this.bo = bo;
        this.breakobject = breakobject;
        this.blockobject = blockobject;
        this.levelobject = levelobject;
        RestartLevel restartlevel = new RestartLevel();
        restartlevel.restartLevel(main, bo, blockobject, levelobject);
    }

}

