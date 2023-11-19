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


//        Button mainmenuButton = new Button("Main Menu");
//        mainmenuButton.setOnAction(event -> {
//            main.clearBlocks();
//            GameEngine.setPaused(true);
//            main.restartGameEngine();
//            main.restartGame();;
//
//            main.start(stage);
//        });

        setSpacing(10);

        // Set the position of PauseMenu to the center of the screen
        setTranslateX((400 - getWidth()) / 2);
        setTranslateY((600 - getHeight()) / 2);
        getChildren().addAll(resumeButton, exitButton);
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

//   private void showMainMenu() {
//        // Create an instance of MainMenu
//        MainMenu mainMenu = new MainMenu(main);
//
//        // Create the main menu scene
//        Scene mainMenuScene = new Scene(mainMenu.MainMenuLayout(), 800, 600); // Adjust the size
//        mainMenuScene.getStylesheets().add("style.css"); // Add your stylesheets if needed
//
//        // Set the main menu scene as the root of the stage
//        stage.setScene(mainMenuScene);
//    }




}

