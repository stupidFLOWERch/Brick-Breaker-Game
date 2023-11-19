package User;

import brickGame.Block;
import brickGame.GameEngine;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static User.Main.savePath;
import static brickGame.LoadSave.check_mdds;

public class MainMenu {
    private final Main main;

    private Button load;
    private Button newGame;
    private final Stage primaryStage;

    public MainMenu(Stage primaryStage, Main main) {
        this.primaryStage = primaryStage;
        this.main = main;
    }
    public VBox MainMenuLayout() {
        VBox mainMenuLayout = new VBox(20); // Adjust the spacing between buttons if needed
        mainMenuLayout.getChildren().addAll(load, newGame);

        return mainMenuLayout;
    }

    public void showMainMenu() {
        if (primaryStage.getScene() == null) {
            load = new Button("Load a Game");
            newGame = new Button("Start a New Game");
            load.setTranslateX(220);
            load.setTranslateY(300);
            newGame.setTranslateX(220);
            newGame.setTranslateY(340);
            boolean b = check_mdds(savePath);
            load.setVisible(b);

            // Set the button actions
            load.setOnAction(event -> {
                main.startGame();
                main.hideButton();
                main.clearBlocks();
                main.loadGame();


            });
            newGame.setOnAction(event -> {
                main.clearBlocks();
                main.startGame();
                main.restartGameEngine();
            });
            // Set the main menu layout as the scene for the primary stage
            Scene mainMenuScene = new Scene(MainMenuLayout(), 500, 700);
            mainMenuScene.getStylesheets().add("style.css");
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu");
            primaryStage.show();

        }
        primaryStage.show();
    }
}

