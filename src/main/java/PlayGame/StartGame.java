package PlayGame;

import Ball.InitBall;
import Block.Block;
import Block.InitBlock;
import Break.InitBreak;
import Score.Score;
import Sound.Bgm;
import brickGame.GameEngine;
import brickGame.Main;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The StartGame class handles the initialization and starting of the game, including creating game elements,
 * setting up the game scene, and managing the game state.
 */
public class StartGame {
    private final Main main;

    /**
     * Constructs a new StartGame instance with the specified main game class.
     *
     * @param main The main game class.
     */
    public StartGame(Main main) {
        this.main = main;
    }

    /**
     * Starts the game by initializing game elements, setting up the scene, and managing the game state.
     */
    public void startGame() {
        InitBall initball = new InitBall();
        InitBlock initblock = new InitBlock();
        InitBreak initbreak = new InitBreak();
        new Bgm();
        if (!main.getLevelobject().isLoadFromSave()) {
            if (main.getLevelobject().isFromRestartGame()) {
                if (main.getLevelobject().getLevel() < 18) {
                    main.getLevelobject().setLevel(main.getLevelobject().getLevel() + 1);
                    if (main.getLevelobject().getLevel() > 1) {
                        Platform.runLater(() -> new Score().showMessage("Level Up :)", main));
                    }
                    if (main.getLevelobject().getLevel() == 18) {

                        Platform.runLater(() -> new Score().showWin(main.getPrimaryStage(), main, main.getBo(), main.getBlockobject(), main.getLevelobject()));

                        System.out.println("You win the game!");
                        System.out.println("Do you want to play bonus level ?");
                        return;
                    }
                }
            }
            main.getBo().setBall(new Circle());
            initball.initBall(main.getBo());
            main.getBo().getBall().setCenterX(main.getBo().getxBall());
            main.getBo().getBall().setCenterY(main.getBo().getyBall());

            main.getBreakobject().setRect(new Rectangle());
            main.getBreakobject().setxBreak(185);
            initbreak.initBreak(main.getBreakobject().getRect(), main.getBreakobject().getxBreak());

            initblock.initBlock(main.getLevelobject().getLevel(), main.getBlockobject().getBlocks());

        }

        Pane root = new Pane();
        main.setRoot(root);

        main.getLevelobject().setScoreLabel(new Label("Score: " + main.getLevelobject().getScore()));
        main.getLevelobject().setLevelLabel(new Label("Level: " + main.getLevelobject().getLevel()));
        main.getLevelobject().getLevelLabel().setTranslateY(20);
        main.getLevelobject().setHeartLabel(new Label("Heart : " + main.getLevelobject().getHeart()));
        main.getLevelobject().getHeartLabel().setTranslateX(main.getLevelobject().getSceneWidth() - 70);

        main.clearBlocks();
        Platform.runLater(() -> main.getRoot().getChildren().addAll(main.getBreakobject().getRect(), main.getBo().getBall(), main.getLevelobject().getScoreLabel(), main.getLevelobject().getHeartLabel(), main.getLevelobject().getLevelLabel()));

        for (Block block : main.getBlockobject().getBlocks()) {
            Platform.runLater(() -> main.getRoot().getChildren().add(block.rect));
        }

        Scene scene = new Scene(main.getRoot(), main.getLevelobject().getSceneWidth(), main.getLevelobject().getSceneHeight());
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(main);

        main.getPrimaryStage().setScene(scene);
        main.getPrimaryStage().setTitle("Game");
        main.getPrimaryStage().show();

        if (!main.getLevelobject().isLoadFromSave() && main.getLevelobject().isFromRestartGame()) {
            if (main.getLevelobject().getLevel() > 1 && main.getLevelobject().getLevel() <= 18) {
                GameEngine.restartGameEngine(main);
            }
        } else if (main.getLevelobject().isLoadFromSave()) {
            main.setEngine(new GameEngine());
            main.getEngine().setOnAction(main);
            //main.getEngine().setFps();
            main.getEngine().start();
            main.getLevelobject().setLoadFromSave(false);
        }
        main.getLevelobject().setFromRestartGame(false);
    }
}