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


    public static void main(String[] args) {
        launch(args);
    }
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

       @Override
    public void handle(KeyEvent event) {
        Handle handle = new Handle(this);
        handle.handle(event);
       }

    @Override
    public void onUpdate() {
        onAction.onUpdate();
    }

    private long lastUpdateTime = 0;

    @Override
    public void onPhysicsUpdate() {
        onAction.onPhysicsUpdate();
    }

    @Override
    public void onTime(long time) {
        onAction.onTime(time);
    }

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
    public void clearBlocks() {
        Platform.runLater(() -> root.getChildren().clear());
    }
    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }
    public GameEngine getEngine(){
        return engine;
    }
    public Move getMove() {
        return move;
    }
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
    public ResetCollideFlags getResetcollideflags() {
        return resetcollideflags;
    }
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}