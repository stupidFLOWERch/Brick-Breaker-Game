package Menu;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import PlayGame.LevelObject;
import PlayGame.StartGame;
import Score.HighScorePage;
import Score.LoadHighScore;
import brickGame.Main;
import brickGame.GameEngine;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import LoadGameSaveGame.LoadGame;
import LoadGameSaveGame.SaveGame;

import static LoadGameSaveGame.LoadSave.checkfile;

/**
 * The MainMenu class represents the main menu of the game, providing options for starting a new game,
 * loading a saved game, the instruction page, viewing the high score page, and exiting the game.
 */
public class MainMenu {
    private final Button load = new Button("Load a Game");
    private final Button newGame = new Button("Start a New Game");
    private final Button instruction = new Button("Instruction");
    private final Button high = new Button("High Score Page");
    private final Button exit = new Button("Exit");


    /**
     * Constructs a new MainMenu instance.
     *
     * @param main          The main application instance.
     * @param bo            The BallObject instance representing the game ball.
     * @param breakobject   The BreakObject instance representing the game breaks.
     * @param blockobject   The BlockObject instance representing the game blocks.
     * @param levelobject   The LevelObject instance representing the game level.
     */
    public MainMenu(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        LoadHighScore loadhighscore = new LoadHighScore();
        StartGame startgame = new StartGame(main);
        LoadGame loadgame = new LoadGame(main, bo, breakobject, blockobject, levelobject);
        boolean b = checkfile(SaveGame.savePath);
        load.setVisible(b);
        boolean h = checkfile(levelobject.getFilePath());
        high.setVisible(h);
        load.setOnAction(event -> {
            startgame.startGame();
            main.clearBlocks();
            loadgame.loadGame();
            main.showPauseMenu.resumeGame(main);
        });

        newGame.setOnAction(event -> {
            main.clearBlocks();
            startgame.startGame();
            GameEngine.restartGameEngine(main);
            main.showPauseMenu.resumeGame(main);
        });

        instruction.setOnAction(event -> {
            InstructionMenu instructionmenu = new InstructionMenu();
            Pane instructionLayout = instructionmenu.createInstructionLayout(main, bo, breakobject, blockobject, levelobject);
            main.getMainScene().setRoot(instructionLayout);
        });

        high.setOnAction(event -> {
            HighScorePage highscorepage = new HighScorePage();
            highscorepage.show(main, bo, breakobject, blockobject, levelobject);
        });
        exit.setOnAction(event ->{main.showPauseMenu.exitGame();});
    }


    /**
     * Creates the layout for the main menu.
     *
     * @return A VBox representing the main menu layout.
     */

    public VBox createMainMenuLayout() {
        VBox mainMenuLayout = new VBox(20, load, newGame, instruction, high,exit);
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenuLayout.setSpacing(30);
        return mainMenuLayout;
    }
}
