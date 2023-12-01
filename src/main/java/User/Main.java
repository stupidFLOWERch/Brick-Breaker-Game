package User;

import Ball.BallObject;
import Ball.ResetCollideFlags;
import Ball.SetPhysicsToBall;
import Block.BlockObject;
import Block.Block;
import Block.Bonus;
import Block.Trap;
import Block.CheckDestroyedCount;
import Break.BreakObject;
import LoadGameSaveGame.SaveGame;
import Menu.MainMenu;
import Pause.PauseGame;
import Pause.PauseMenu;
import Pause.ShowPauseMenu;
import Score.Score;
import brickGame.*;
import Level.LevelObject;
import Sound.Sound;
import Sound.Bgm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    private final Move move = new Move(this);
    public final ShowPauseMenu showPauseMenu = new ShowPauseMenu(this);
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
        SaveGame savegame = new SaveGame();

        switch (event.getCode()) {
            case LEFT:
                move.move(BreakObject.getLEFT());
                break;
            case RIGHT:
                move.move(BreakObject.getRIGHT());
                break;
            case S:
                savegame.saveGame(this, bo, blockobject, levelobject, getBreakobject());
                break;
            case P:
                if (PauseGame.pauseGame()) {
                    Bgm.pause();
                    GameEngine.setPaused(true);
                    getBreakobject().setBreakMoveAllow(false);
                    showPauseMenu.showPauseMenu();
                }
                else {
                    Bgm.resume();
                    GameEngine.setPaused(false);
                    getBreakobject().setBreakMoveAllow(true);
                    showPauseMenu.hidePauseMenu();
                }
                break;
        }
    }


    @Override
    public void onUpdate() {
        Platform.runLater(() -> {
            levelobject.getScoreLabel().setText("Score: " + levelobject.getScore());
            levelobject.getHeartLabel().setText("Heart : " + levelobject.getHeart());

            getBreakobject().getRect().setX(getBreakobject().getxBreak());
            getBreakobject().getRect().setY(getBreakobject().getyBreak());
            bo.getBall().setCenterX(bo.getxBall());
            bo.getBall().setCenterY(bo.getyBall());
        });
        for (Bonus cheese : blockobject.getCheeses()) {
            cheese.cheese.setY(cheese.y);
        }
        for (Trap mousetrap : blockobject.getTraps()){
            mousetrap.mousetrap.setY(mousetrap.y);
        }
        List<Block> blocksCopy = new ArrayList<>(blockobject.getBlocks());


        if (bo.getyBall() >= Block.getPaddingTop() && bo.getyBall() <= (Block.getHeight() * (levelobject.getLevel() + 1)) + Block.getPaddingTop()) {
            for (final Block block : blocksCopy) {
                try {
                    int hitCode = block.checkHitToBlock(bo.getxBall(), bo.getyBall());
                    if (hitCode != Block.NO_HIT) {
                        levelobject.setScore(levelobject.getScore()+1);

                        new Score().show(block.x, block.y, 1, Main.this);

                        block.rect.setVisible(false);
                        block.isDestroyed = true;
                        levelobject.setDestroyedBlockCount(levelobject.getDestroyedBlockCount()+1);
                        new Sound();
                        //System.out.println("size is " + blocks.size());
                        resetcollideflags.resetCollideFlags(bo);

                        if (block.type == Block.BLOCK_CHEESE) {
                            final Bonus cheese = new Bonus(block.row, block.column);
                            cheese.timeCreated = bo.getTime();
                            Platform.runLater(() -> Platform.runLater(() -> root.getChildren().add(cheese.cheese)));
                            blockobject.getCheeses().add(cheese);
                        }

                        if (block.type == Block.BLOCK_TRAP) {
                            final Trap mousetrap = new Trap(block.row, block.column);
                            mousetrap.timeCreated = bo.getTime();
                            Platform.runLater(() -> Platform.runLater(() -> root.getChildren().add(mousetrap.mousetrap)));
                            blockobject.getTraps().add(mousetrap);
                        }

                        if (block.type == Block.BLOCK_STAR) {
                            bo.setGoldTime(bo.getTime());
                            bo.getBall().setFill(new ImagePattern(new Image("goldball.png")));
                            System.out.println("gold ball");
                            root.getStyleClass().add("goldRoot");
                            levelobject.setGoldStatus(true);
                        }

                        if (block.type == Block.BLOCK_HEART) {
                            levelobject.setHeart(levelobject.getHeart()+1);
                            levelobject.setGetHeart(true);
                        }

                        if (hitCode == Block.HIT_RIGHT) {
                            bo.setCollideToRightBlock(true);
                        } else if (hitCode == Block.HIT_BOTTOM) {
                            bo.setCollideToBottomBlock(true);
                        } else if (hitCode == Block.HIT_LEFT) {
                            bo.setCollideToLeftBlock(true);
                        } else if (hitCode == Block.HIT_TOP) {
                            bo.setCollideToTopBlock(true);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //TODO hit to break and some work here....
                //System.out.println("Break in row:" + block.row + " and column:" + block.column + " hit");
            }
        }
    }

    private long lastUpdateTime = 0;

    @Override
    public void onPhysicsUpdate() {
        SetPhysicsToBall setphysicstoball = new SetPhysicsToBall();
        CheckDestroyedCount checkdestroyedcount = new CheckDestroyedCount();
        long currentTime = System.currentTimeMillis();
        double elapsedTime = (currentTime - lastUpdateTime) / 1000.0;  // Convert to seconds
        lastUpdateTime = currentTime;

        checkdestroyedcount.checkDestroyedCount(this, engine, bo, blockobject, levelobject);
        setphysicstoball.setPhysicsToBall(primaryStage,this, engine, bo, getBreakobject(), blockobject, levelobject);

        if (bo.getTime() - bo.getGoldTime() > 5000) {
            Platform.runLater(() -> {
                bo.getBall().setFill(new ImagePattern(new Image("ball.png")));
                root.getStyleClass().remove("goldRoot");
            });
            levelobject.setGoldStatus(false);
        }

        List<Bonus> cheesesToRemove = new ArrayList<>();
        Iterator<Bonus> cheeseIterator = blockobject.getCheeses().iterator();
        while (cheeseIterator.hasNext()) {
            Bonus cheese = cheeseIterator.next();

            if (cheese.y > levelobject.getSceneHeight() || cheese.taken) {
                cheesesToRemove.add(cheese);
                continue;
            }
            if (cheese.y >= getBreakobject().getyBreak() && cheese.y <= getBreakobject().getyBreak() + getBreakobject().getBreakHeight() && cheese.x >= getBreakobject().getxBreak() && cheese.x <= getBreakobject().getxBreak() + getBreakobject().getBreakWidth()) {
                System.out.println("You Got the cheese! +3 score for you");
                cheese.taken = true;
                cheese.cheese.setVisible(false);
                levelobject.setScore(levelobject.getScore()+3);
                Platform.runLater(() -> new Score().show(cheese.x, cheese.y, 3, this));
            }
            cheese.y += elapsedTime * ((bo.getTime() - cheese.timeCreated) / 1000.0) + 1.0;
        }
        blockobject.getCheeses().removeAll(cheesesToRemove);


        List<Trap> trapsToRemove = new ArrayList<>();
        Iterator<Trap> trapIterator = blockobject.getTraps().iterator();
        while (trapIterator.hasNext()) {
            Trap mousetrap = trapIterator.next();

            if (mousetrap.y > levelobject.getSceneHeight() || mousetrap.taken) {
                trapsToRemove.add(mousetrap);
                continue;
            }
            if (mousetrap.y >= getBreakobject().getyBreak() && mousetrap.y <= getBreakobject().getyBreak() + getBreakobject().getBreakHeight() && mousetrap.x >= getBreakobject().getxBreak() && mousetrap.x <= getBreakobject().getxBreak() + getBreakobject().getBreakWidth()) {
                System.out.println("You Got the trap! -3 score ");
                mousetrap.taken = true;
                mousetrap.mousetrap.setVisible(false);
                levelobject.setScore(levelobject.getScore()-3);
                Platform.runLater(() -> new Score().show(mousetrap.x, mousetrap.y, -3, this));
            }
            mousetrap.y += elapsedTime * ((bo.getTime() - mousetrap.timeCreated) / 1000.0) + 1.0;
        }
        blockobject.getTraps().removeAll(trapsToRemove);

    }

    @Override
    public void onTime(long time) {
        this.bo.setTime(time);
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
}