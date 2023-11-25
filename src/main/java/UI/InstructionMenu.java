package UI;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import User.Main;


public class InstructionMenu {

    public static Pane createInstructionLayout(Main main) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        root.getStyleClass().add("instruction");

        Button backButton = new Button("Back to Main Menu");
        backButton.setTranslateX(200);
        backButton.setTranslateY(630);
        backButton.setOnAction(event -> {
            main.getMainScene().setRoot(new MainMenu(main).createMainMenuLayout());
        });

        root.getChildren().add(backButton);
        return root;
    }
}
