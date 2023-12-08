package brickGame;

import Ball.BallObject;
import Ball.ResetCollideFlags;
import Block.BlockObject;
import Break.BreakObject;
import Menu.MainMenu;
import Pause.PauseMenu;
import Pause.ShowPauseMenu;
import PlayGame.LevelObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Brick Game application.
 * It extends the JavaFX Application and implements event handlers and
 * callbacks for game actions. The application includes features such as
 * a main menu, game scene, and various game elements like the ball, blocks,
 * and levels.
 *
 * @author tch
 */

public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    private final Move move = new Move(this);
    public final ShowPauseMenu showPauseMenu = new ShowPauseMenu(this);
    private final OnAction onAction = new OnAction(this);
    private GameEngine engine;
    public Pane root;
    Stage primaryStage;
    private PauseMenu pauseMenu;
    private Scene mainScene;

    private LevelObject levelobject;
    private BallObject bo;
    private ResetCollideFlags resetcollideflags;
    private BreakObject breakobject;
    private BlockObject blockobject;

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and starts the JavaFX application.
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        levelobject = new LevelObject();
        bo = new BallObject();
        resetcollideflags = new ResetCollideFlags();
        breakobject = new BreakObject();
        blockobject = new BlockObject();
        engine = new GameEngine();
        root = new Pane();
        setRoot(root);
        levelobject.setScore(0);
        levelobject.setHeart(3);
        levelobject.setLevel(0);
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);
        levelobject.setGetHeart(false);
        levelobject.setDestroyedBlockCount(0);
        levelobject.setFromRestartGame(false);

        this.primaryStage = primaryStage;
        MainMenu mainMenu = new MainMenu(this, bo, getBreakobject(), blockobject, levelobject);

        // Create the main menu scene
        mainScene = new Scene(mainMenu.createMainMenuLayout(), 500, 700);
        mainScene.getStylesheets().add("style.css");

        // Disable the resizeable button
        primaryStage.setResizable(false);

        // Show the stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    /**
     * Handles KeyEvents, delegating the handling to the Handle class.
     *
     * @param event The KeyEvent to be handled.
     */
       @Override
    public void handle(KeyEvent event) {
        Handle handle = new Handle(this);
        handle.handle(event);
       }

    /**
     * Callback method for updating game logic. Delegates to the OnAction class.
     */
    @Override
    public void onUpdate() {
        onAction.onUpdate();
    }

    private long lastUpdateTime = 0;

    /**
     * Callback method for physics-related updates. Delegates to the OnAction class.
     */
    @Override
    public void onPhysicsUpdate() {
        onAction.onPhysicsUpdate();
    }

    /**
     * Callback method for handling time-related events. Delegates to the OnAction class.
     *
     * @param time The current time in milliseconds.
     */
    @Override
    public void onTime(long time) {
        onAction.onTime(time);
    }


    /**
     * Retrieves the main scene of the application.
     *
     * @return The main Scene object.
     */
    public Scene getMainScene() {
        return mainScene;
    }
    public LevelObject getLevelobject() {
        return levelobject;
    }
    public BreakObject getBreakobject() {
        return breakobject;
    }
    public BallObject getBo() {
        return bo;
    }
    public BlockObject getBlockobject() {
        return blockobject;
    }
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
    public ShowPauseMenu getShowPauseMenu() {
        return showPauseMenu;
    }
    public Pane getRoot(){
        return root;
    }
    public void setRoot(Pane root){
        this.root = root;
    }
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public void setPauseMenu(PauseMenu pauseMenu) {
        this.pauseMenu = pauseMenu;
    }

    /**
     * Clears blocks from the game scene in a JavaFX thread-safe manner.
     */
    public void clearBlocks() {
        Platform.runLater(() -> root.getChildren().clear());
    }
    /**
     * Sets a new GameEngine for the application.
     *
     * @param engine The new GameEngine.
     */
    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    /**
     * Retrieves the current GameEngine instance.
     *
     * @return current GameEngine.
     */
    public GameEngine getEngine(){
        return engine;
    }

    /**
     * Retrieves the Move instance associated with this application.
     *
     * @return The Move instance.
     */
    public Move getMove() {
        return move;
    }

    /**
     * Retrieves the last update time.
     *
     * @return The last update time.
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Retrieves the ResetCollideFlags instance associated with this application.
     *
     * @return The ResetCollideFlags instance.
     */
    public ResetCollideFlags getResetcollideflags() {
        return resetcollideflags;
    }

    /**
     * Sets the last update time.
     *
     * @param lastUpdateTime The new last update time.
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}