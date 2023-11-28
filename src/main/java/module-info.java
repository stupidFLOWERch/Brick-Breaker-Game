module brickGame {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens brickGame to javafx.fxml;
    exports brickGame;
    exports User;
    opens User to javafx.fxml;
    exports UI;
    opens UI to javafx.fxml;
    exports Block;
    opens Block to javafx.fxml;
    exports Ball;
    opens Ball to javafx.fxml;
    exports Break;
    opens Break to javafx.fxml;
    exports Level;
    opens Level to javafx.fxml;
}