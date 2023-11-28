package Menu;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import User.Main;
import Level.LevelObject;
import User.SaveGame;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import User.LoadGame;

import static brickGame.LoadSave.checkfile;

public class MainMenu {

    private final Button load = new Button("Load a Game");
    private final Button newGame = new Button("Start a New Game");
    private final Button instruction = new Button("Instruction");

    public MainMenu(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        LoadGame loadgame = new LoadGame(main, bo, breakobject, blockobject, levelobject);
        boolean b = checkfile(SaveGame.savePath);
        load.setVisible(b);

        load.setOnAction(event -> {
            main.startGame();
            main.clearBlocks();
            loadgame.loadGame();
            main.showPauseMenu.resumeGame(main);
        });

        newGame.setOnAction(event -> {
            main.clearBlocks();
            main.startGame();
            main.restartGameEngine();
            main.showPauseMenu.resumeGame(main);
        });

        instruction.setOnAction(event -> {
            InstructionMenu instructionmenu = new InstructionMenu();
            Pane instructionLayout = instructionmenu.createInstructionLayout(main, bo, breakobject, blockobject, levelobject);
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
