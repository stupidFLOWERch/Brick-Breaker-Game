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

    public void show(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        Pane root = new Pane();
        Label label = new Label("Congratulations !!!");
        label.setTranslateX(10);
        label.setTranslateY(30);
        label.getStyleClass().add("labelStyle");

        Label playerNameLabel = new Label( LoadHighScore.getName());
        playerNameLabel.setTranslateX(10);
        playerNameLabel.setTranslateY(110);
        playerNameLabel.getStyleClass().add("labelStyle");

        Label highScoreLabel = new Label("" + LoadHighScore.getHighScore());
        highScoreLabel.setTranslateX(115);
        highScoreLabel.setTranslateY(315);
        highScoreLabel.getStyleClass().add("labelStyle");

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(160);
        backButton.setTranslateY(560);
        backButton.setOnAction(event -> {
            MainMenu mainMenu = new MainMenu(main, bo, breakobject, blockobject, levelobject);
            main.getPrimaryStage().setTitle("Main Menu");
            main.getMainScene().setRoot(mainMenu.createMainMenuLayout());
        });

        root.getChildren().addAll( label,playerNameLabel,highScoreLabel, backButton);

        main.getPrimaryStage().setTitle("High Score");
        root.getStyleClass().add("highscore");
        main.getMainScene().setRoot(root);
    }
}
