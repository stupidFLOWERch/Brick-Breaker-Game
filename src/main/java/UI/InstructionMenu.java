package UI;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InstructionMenu {

    public static void showInstructionMenu(Stage primaryStage) {
        // Create a Pane as the root
        Pane root = new Pane();

        // Load the image
        Image backgroundImage = new Image("instruction.jpg");

        // Set the background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(background));

        // Create the scene
        Scene scene = new Scene(root, 500, 700); // Adjust the size as needed

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Instruction Menu");
        primaryStage.show();
    }
}
