package Pause;

import Sound.Bgm;
import brickGame.Main;
import brickGame.GameEngine;
import javafx.application.Platform;

public class ShowPauseMenu {
    private final Main main;

    public ShowPauseMenu(Main main) {
        this.main = main;
    }

    // Method to show pause menu
    public void showPauseMenu() {
        main.setPauseMenu(new PauseMenu(main, main.getBo(), main.getBlockobject(), main.getLevelobject()));
        // Add the pause menu to your game root or scene
        main.root.getChildren().add(main.getPauseMenu());
    }// Method to hide the pause menu

    public void hidePauseMenu() {
        // Remove the pause menu from your game root or scene
        main.root.getChildren().remove(main.getPauseMenu());
    }

    public void resumeGame(Main main) {
        hidePauseMenu();
        main.getBreakobject().setBreakMoveAllow(true);
        GameEngine.setPaused(false);  // Resume the game engine
        Bgm.resume();  // Resume background music if applicable

        PauseGame.resetState();
    }

    public void exitGame() {
        Platform.exit();
    }
}