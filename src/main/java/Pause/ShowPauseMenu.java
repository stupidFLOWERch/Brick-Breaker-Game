package Pause;

import Sound.Bgm;
import brickGame.Main;
import brickGame.GameEngine;
import javafx.application.Platform;

/**
 * The ShowPauseMenu class provides methods to show, hide, resume, and exit the pause menu in the game.
 * It interacts with the main game class to control the game state and UI components during pausing.
 */
public class ShowPauseMenu {
    private final Main main;

    /**
     * Constructs a new ShowPauseMenu instance.
     *
     * @param main The main application instance.
     */

    public ShowPauseMenu(Main main) {
        this.main = main;
    }

    // Method to show pause menu
    /**
     * Shows the pause menu in the game.
     */
    public void showPauseMenu() {
        main.setPauseMenu(new PauseMenu(main, main.getBo(), main.getBlockobject(), main.getLevelobject(), main.getBreakobject()));
        // Add the pause menu to your game root or scene
        main.root.getChildren().add(main.getPauseMenu());
    }// Method to hide the pause menu

    /**
     * Hides the pause menu in the game.
     */
    public void hidePauseMenu() {
        // Remove the pause menu from your game root or scene
        main.root.getChildren().remove(main.getPauseMenu());
    }

    /**
     * Resumes the game by hiding the pause menu, allowing break movement, and resuming the game engine and background music.
     *
     * @param main The main application instance.
     */
    public void resumeGame(Main main) {
        hidePauseMenu();
        main.getBreakobject().setBreakMoveAllow(true);
        GameEngine.setPaused(false);  // Resume the game engine
        Bgm.resume();  // Resume background music if applicable

        PauseGame.resetState();
    }

    /**
     * Exits the game by closing the application.
     */
    public void exitGame() {
        Platform.exit();
    }
}