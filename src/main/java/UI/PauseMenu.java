package UI;


import User.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseMenu extends VBox {

    private final Main main;  // Reference to the main game class
    private final Stage stage;

    public PauseMenu(Stage stage , Main main) {
        this.main = main;
        //this.mainMenu = mainMenu;
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        // Create buttons or other UI elements for your pause menu
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(event -> resumeGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitGame());

        Button restartButton = new Button("Restart Level");
        restartButton.setOnAction(event -> restartLevel());

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

    public void restartLevel(){
        main.restartLevel();
    }

}

