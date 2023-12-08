package brickGame;

import Break.BreakObject;
import LoadGameSaveGame.SaveGame;
import Pause.PauseGame;
import Sound.Bgm;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * The Handle class is an event handler for processing keyboard events in the game.
 * It implements the {@link EventHandler} interface for handling key events.
 */
public class Handle implements EventHandler<KeyEvent> {
    private final Main main;

    /**
     * Constructs a new Handle instance with the specified {@link Main} instance.
     *
     * @param main The {@link Main} instance.
     */
    public Handle(Main main) {
        this.main = main;
    }

    /**
     * Handles the specified key event, performing actions based on pressed key.
     *
     * @param event The key event to handle.
     */
    public void handle(KeyEvent event) {
        SaveGame savegame = new SaveGame();

        switch (event.getCode()) {
            case LEFT:
                main.getMove().move(BreakObject.getLEFT());
                break;
            case RIGHT:
                main.getMove().move(BreakObject.getRIGHT());
                break;
            case S:
                savegame.saveGame(main, main.getBo(), main.getBlockobject(), main.getLevelobject(), main.getBreakobject());
                break;
            case P:
                if (PauseGame.pauseGame()) {
                    Bgm.pause();
                    GameEngine.setPaused(true);
                    main.getBreakobject().setBreakMoveAllow(false);
                    main.getShowPauseMenu().showPauseMenu();
                } else {
                    Bgm.resume();
                    GameEngine.setPaused(false);
                    main.getBreakobject().setBreakMoveAllow(true);
                    main.getShowPauseMenu().hidePauseMenu();
                }
                break;
        }
    }
}