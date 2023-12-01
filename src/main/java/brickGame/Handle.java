package brickGame;

import Break.BreakObject;
import LoadGameSaveGame.SaveGame;
import Pause.PauseGame;
import Sound.Bgm;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Handle implements EventHandler<KeyEvent> {
    private final Main main;

    public Handle(Main main) {
        this.main = main;
    }

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