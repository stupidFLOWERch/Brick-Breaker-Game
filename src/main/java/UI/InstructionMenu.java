package UI;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import brickGame.LevelObject;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import User.Main;


public class InstructionMenu {
    private Main main;
    private LevelObject levelobject;
    private BallObject bo;
    private BreakObject breakobject;
    private BlockObject blockobject;

    public Pane createInstructionLayout(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        this.main = main;
        this.bo = bo;
        this.breakobject = breakobject;
        this.blockobject = blockobject;
        this.levelobject = levelobject;
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        root.getStyleClass().add("instruction");

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(200);
        backButton.setTranslateY(630);
        backButton.setOnAction(event -> {
            main.getMainScene().setRoot(new MainMenu(main, bo, breakobject, blockobject, levelobject).createMainMenuLayout());
        });

        root.getChildren().add(backButton);
        return root;
    }
}
