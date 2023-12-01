package Menu;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import Level.LevelObject;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import brickGame.Main;


public class InstructionMenu {

    public Pane createInstructionLayout(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        root.getStyleClass().add("instruction");

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(160);
        backButton.setTranslateY(630);
        backButton.setOnAction(event -> main.getMainScene().setRoot(new MainMenu(main, bo, breakobject, blockobject, levelobject).createMainMenuLayout()));

        root.getChildren().add(backButton);
        return root;
    }
}
