package Menu;

import Ball.BallObject;
import Block.BlockObject;
import Break.BreakObject;
import PlayGame.LevelObject;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import brickGame.Main;

/**
 * The InstructionMenu class represents the layout for the instruction page in the game.
 * It provides a back button to return to the main menu.
 */
public class InstructionMenu {

    /**
     * Creates the layout for the instruction page.
     *
     * @param main          The main application instance.
     * @param bo            The BallObject instance used in the game.
     * @param breakobject   The BreakObject instance used in the game.
     * @param blockobject   The BlockObject instance used in the game.
     * @param levelobject   The LevelObject instance used in the game.
     * @return A Pane representing the instruction page layout.
     */
    public Pane createInstructionLayout(Main main, BallObject bo, BreakObject breakobject, BlockObject blockobject, LevelObject levelobject) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        root.getStyleClass().add("instruction");

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(160);
        backButton.setTranslateY(630);

        backButton.setOnAction(event -> {
                main.getPrimaryStage().setTitle("Main Menu");
                main.getMainScene().setRoot(new MainMenu(main, bo, breakobject, blockobject, levelobject).createMainMenuLayout());
        });
        main.getPrimaryStage().setTitle("Instruction Page");
        root.getChildren().add(backButton);
        return root;
    }
}
