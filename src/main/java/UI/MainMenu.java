package UI;

import User.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

import static User.Main.savePath;
import static brickGame.LoadSave.check_mdds;

public class MainMenu {
    private final Main main;

    private final Button load = new Button("Load a Game");
    private final Button newGame = new Button("Start a New Game");
    private final Button instruction = new Button("Instruction");

    public MainMenu(Main main) {
        this.main = main;
        initialize();
    }

    private void initialize() {
        boolean b = check_mdds(savePath);
        load.setVisible(b);

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

        instruction.setOnAction(event -> {
            Pane instructionLayout = InstructionMenu.createInstructionLayout(main);
            main.getMainScene().setRoot(instructionLayout);
        });
    }

    public VBox createMainMenuLayout() {
        VBox mainMenuLayout = new VBox(20, load, newGame, instruction);
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenuLayout.setSpacing(30);

        return mainMenuLayout;
    }
}
