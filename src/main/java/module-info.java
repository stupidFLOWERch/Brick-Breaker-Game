module brickGame {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens brickGame to javafx.fxml;
    exports brickGame;
    opens Block to javafx.fxml;
    exports Block;
    opens Ball to javafx.fxml;
    exports Ball;
    opens Break to javafx.fxml;
    exports Break;
    opens Score to javafx.fxml;
    exports Score;
    opens Menu to javafx.fxml;
    exports Menu;
    opens LoadGameSaveGame to javafx.fxml;
    exports LoadGameSaveGame;
    opens Pause to javafx.fxml;
    exports Pause;
    opens PlayGame to javafx.fxml;
    exports PlayGame;

}