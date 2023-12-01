package Score;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import Menu.MainMenu;
import PlayGame.LevelObject;
import brickGame.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HighScorePage {

    public HighScorePage(LevelObject levelobject, LoadHighScore loadhighscore) {
    }

    public void show(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        Pane root = new Pane();

        Label titleLabel = new Label("High Score Page");
        titleLabel.setTranslateX(200);
        titleLabel.setTranslateY(50);

        Label playerNameLabel = new Label("Player Name: " + LoadHighScore.getName());
        playerNameLabel.setTranslateX(200);
        playerNameLabel.setTranslateY(100);

        Label highScoreLabel = new Label("High Score: " + LoadHighScore.getHighScore());
        highScoreLabel.setTranslateX(200);
        highScoreLabel.setTranslateY(150);

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(160);
        backButton.setTranslateY(630);
        backButton.setOnAction(event -> {
            MainMenu mainMenu = new MainMenu(main, bo, breakobject, blockobject, levelobject);
            main.getMainScene().setRoot(mainMenu.createMainMenuLayout());
        });

        root.getChildren().addAll(titleLabel, playerNameLabel, highScoreLabel, backButton);



        main.getMainScene().setRoot(root);
    }
}
