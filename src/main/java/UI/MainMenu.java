package UI;

import User.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static User.Main.savePath;
import static brickGame.LoadSave.check_mdds;

public class MainMenu {
    private final Main main;
    private final Stage primaryStage;

    private final Button load = new Button("Load a Game");
    private final Button newGame = new Button("Start a New Game");

    private final Button instruction = new Button("Instruction");

    public MainMenu(Main main, Stage primaryStage) {
        this.main = main;
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        load.setTranslateX(220);
        load.setTranslateY(300);
        newGame.setTranslateX(220);
        newGame.setTranslateY(340);
        instruction.setTranslateX(220);
        instruction.setTranslateY(380);


        boolean b = check_mdds(savePath);
        load.setVisible(b);

        // Set the button actions
        load.setOnAction(event -> {
            main.startGame();
            main.clearBlocks();
            main.loadGame();
        });

        newGame.setOnAction(event -> {
            main.clearBlocks();
            main.startGame();
            main.restartGameEngine();
        });

        instruction.setOnAction(event->{
            InstructionMenu instructionMenu = new InstructionMenu();
            InstructionMenu.showInstructionMenu(primaryStage);
        });
    }

    public VBox MainMenuLayout() {
        VBox mainMenuLayout = new VBox(20); // Adjust the spacing between buttons if needed
        mainMenuLayout.getChildren().addAll(load, newGame, instruction);

        return mainMenuLayout;
    }

    public void showMainMenu(Scene currentScene) {
        if (currentScene != null) {
            // Set the main menu layout as the scene content
            currentScene.setRoot(MainMenuLayout());
        }
    }
}
