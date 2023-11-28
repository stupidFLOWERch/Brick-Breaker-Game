module brickGame {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens brickGame to javafx.fxml;
    exports brickGame;
    exports User;
    opens User to javafx.fxml;
    exports Block;
    opens Block to javafx.fxml;
    exports Ball;
    opens Ball to javafx.fxml;
    exports Break;
    opens Break to javafx.fxml;
    exports Level;
    opens Level to javafx.fxml;
    exports Score;
    opens Score to javafx.fxml;
    exports Menu;
    opens Menu to javafx.fxml;
    exports LoadGameSaveGame;
    opens LoadGameSaveGame to javafx.fxml;
    exports Pause;
    opens Pause to javafx.fxml;
}