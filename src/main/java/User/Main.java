package User;


import Ball.BallObject;
import Ball.InitBall;
import Block.BlockSerializable;
import Block.BlockObject;
import Block.Block;
import Block.InitBlock;
import Block.Bonus;
import Block.Trap;
import Break.BreakObject;
import Break.InitBreak;
import UI.MainMenu;
import UI.PauseMenu;
import UI.Score;
import brickGame.*;
import Level.LevelObject;
import Sound.Sound;
import Sound.Bgm;
import Sound.Win;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    private final int sceneWidth = 500;
    private final int sceneHeight = 700;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private long goldTime = 0;
    private long time = 0;
    private GameEngine engine;
    public static String savePath = "C:/save/save.mdds";
    public static String savePathDir = "C:/save/";
    public Pane root;
    Stage primaryStage;
    private PauseMenu pauseMenu;
    private Scene mainScene;

    private LevelObject levelobject;
    private BallObject bo;
    private BreakObject breakobject;
    private BlockObject blockobject;
    private InitBlock initblock;
    private InitBall initball;
    private InitBreak initbreak;
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        levelobject = new LevelObject();
        bo = new BallObject();
        breakobject = new BreakObject();
        blockobject = new BlockObject();
        initblock = new InitBlock();
        initball = new InitBall();
        initbreak = new InitBreak();
        levelobject.setScore(0);
        levelobject.setHeart(3);
        levelobject.setLevel(0);
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);
        levelobject.setGetHeart(false);
        levelobject.setDestroyedBlockCount(0);
        levelobject.setFromRestartGame(false);

        this.primaryStage = primaryStage;
        MainMenu mainMenu = new MainMenu(this, bo, breakobject, blockobject, levelobject);

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
    
    public void startGame() {
        new Bgm();
        if (!levelobject.isLoadFromSave()) {
            if (levelobject.isFromRestartGame()) {
                if (levelobject.getLevel() < 18) {
                    levelobject.setLevel(levelobject.getLevel()+1);
                    if (levelobject.getLevel() > 1) {
                        Platform.runLater(() -> {
                            new Score().showMessage("Level Up :)", this);
                        });
                    }
                    if (levelobject.getLevel() == 18) {

                        Platform.runLater(() -> {
                            new Score().showWin(this);
                        });

                        System.out.println("You win the game!");
                        System.out.println("Do you want to play bonus level ?");
                        return;
                    }
                }
            }
            bo.setBall(new Circle());
            bo.setxBall(250);
            bo.setyBall(500);
            initball.initBall(bo.getBall(), bo.getxBall(), bo.getyBall());

            breakobject.setRect(new Rectangle());
            breakobject.setxBreak(185);
            initbreak.initBreak(breakobject.getRect(), breakobject.getxBreak());

            initblock.initBlock(levelobject.getLevel(),blockobject.getBlocks());

        }


        root = new Pane();

        levelobject.setScoreLabel(new Label("Score: " + levelobject.getScore()));
        levelobject.setLevelLabel(new Label("Level: " + levelobject.getLevel()));
        levelobject.getLevelLabel().setTranslateY(20);
        levelobject.setHeartLabel(new Label("Heart : " + levelobject.getHeart()));
        levelobject.getHeartLabel().setTranslateX(sceneWidth - 70);

        clearBlocks();
        Platform.runLater(() -> root.getChildren().addAll(breakobject.getRect(), bo.getBall(), levelobject.getScoreLabel(), levelobject.getHeartLabel(), levelobject.getLevelLabel()));

        for (Block block : blockobject.getBlocks()) {
            Platform.runLater(() -> root.getChildren().add(block.rect));
        }

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();

        if (!levelobject.isLoadFromSave() && levelobject.isFromRestartGame()) {
            if (levelobject.getLevel() > 1 && levelobject.getLevel() <= 18) {
                restartGameEngine();
            }
        } else if (levelobject.isLoadFromSave()) {
            engine = new GameEngine();
            engine.setOnAction(this);
            engine.setFps(120);
            engine.start();
            levelobject.setLoadFromSave(false);
        }
        levelobject.setFromRestartGame(false);
    }

    public Scene getMainScene() {
        return mainScene;
    }
    public void clearBlocks() {
        Platform.runLater(() -> root.getChildren().clear());
    }
    public void restartGameEngine() {
        engine = new GameEngine();
        engine.setOnAction(this);
        engine.setFps(120);
        engine.start();
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                move(LEFT);
                break;
            case RIGHT:
                move(RIGHT);
                break;
            case S:
                saveGame();
                break;
            case P:
                if (PauseGame.pauseGame()) {
                    Bgm.pause();
                    GameEngine.setPaused(true);
                    breakobject.setBreakMoveAllow(false);
                    showPauseMenu();
                }
                else {
                    Bgm.resume();
                    GameEngine.setPaused(false);
                    breakobject.setBreakMoveAllow(true);
                    hidePauseMenu();
                }
                break;
        }
    }

    // Method to show pause menu
    public void showPauseMenu() {
        pauseMenu = new PauseMenu(primaryStage,this, bo,breakobject, blockobject, levelobject);
        // Add the pause menu to your game root or scene
        root.getChildren().add(pauseMenu);
    }

    // Method to hide the pause menu
    public void hidePauseMenu() {
        // Remove the pause menu from your game root or scene
        root.getChildren().remove(pauseMenu);
    }
    public void resumeGame() {
        hidePauseMenu();
        breakobject.setBreakMoveAllow(true);
        GameEngine.setPaused(false);  // Resume the game engine
        Bgm.resume();  // Resume background music if applicable

        PauseGame.resetState();
    }

    public void exitGame() {
        Platform.exit();
    }

    private void move(final int direction) {
        if (breakobject.isBreakMoveAllow()) {
            new Thread(() -> {
                int sleepTime = 4;
                for (int i = 0; i < 30; i++) {
                    synchronized (this) {
                        if (!breakobject.isBreakMoveAllow()) {
                            return;
                        }
                        if (breakobject.getxBreak() == (sceneWidth - breakobject.getBreakWidth()) && direction == RIGHT) {
                            return;
                        }
                        if (breakobject.getxBreak() == 0 && direction == LEFT) {
                            return;
                        }
                        if (direction == RIGHT && breakobject.getxBreak() < (sceneWidth - breakobject.getBreakWidth())) {
                            breakobject.setxBreak(breakobject.getxBreak() + 1);
                        }
                        if (direction == LEFT && breakobject.getxBreak() > 0) {
                            breakobject.setxBreak(breakobject.getxBreak() - 1);
                        }
                        breakobject.setCenterBreakX(breakobject.getxBreak() + breakobject.getHalfBreakWidth());
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i >= 20) {
                        sleepTime = i;
                    }
                }
            }).start();
        }
    }

    public void resetCollideFlags() {

        bo.setCollideToBreak(false);
        bo.setCollideToBreakAndMoveToRight(false);
        bo.setCollideToRightWall(false);
        bo.setCollideToLeftWall(false);

        bo.setCollideToRightBlock(false);
        bo.setCollideToBottomBlock(false);
        bo.setCollideToLeftBlock(false);
        bo.setCollideToTopBlock(false);
    }

    private void setPhysicsToBall() {
        synchronized (this) {
            if (bo.getyBall() >= sceneHeight - bo.getBallRadius() && bo.isGoDownBall()) {
                resetCollideFlags();
                bo.setGoDownBall(false);
                if (levelobject.getLevel() == 18) {
                    levelobject.setGoldStatus(true);
                }
                if (!levelobject.isGoldStatus()) {
                    //TODO gameover
                    levelobject.setHeart(levelobject.getHeart()-1);
                    new Score().show(sceneWidth / 2.0, sceneHeight / 2.0, -1, this);

                    if (levelobject.getHeart() == 0) {
                        new Score().showGameOver(this);
                        System.out.println("Lol so noob loss the game");
                        engine.stop();
                        return;
                    }
                }
            }

            if (bo.isGoDownBall()) {
                bo.setyBall(bo.getyBall() + bo.getvY());
            } else {
                bo.setyBall(bo.getyBall() - bo.getvY());
            }

            if (bo.isGoRightBall()) {
                bo.setxBall(bo.getxBall() + bo.getvX());
            } else {
                bo.setxBall(bo.getxBall() - bo.getvX());
            }

            if (bo.getyBall() <= bo.getBallRadius()) {
                resetCollideFlags();
                bo.setGoDownBall(true);
                return;
            }
        }

        if (bo.getyBall() >= breakobject.getyBreak() - bo.getBallRadius()) {
            //System.out.println("Colide1");
            if (bo.getxBall() >= breakobject.getxBreak() && bo.getxBall() <= breakobject.getxBreak() + breakobject.getBreakWidth()) {
                resetCollideFlags();
                bo.setCollideToBreak(true);
                bo.setGoDownBall(false);

                double relation = (bo.getxBall() - breakobject.getCenterBreakX()) / ((double) breakobject.getBreakWidth() / 2);

                if (Math.abs(relation) <= 0.3) {
                    bo.setvX(Math.abs(relation));
                } else if (Math.abs(relation) > 0.3 && Math.abs(relation) <= 0.7) {
                    bo.setvX((Math.abs(relation) * 1.5) + (levelobject.getLevel() / 3.500));
                } else {
                    bo.setvX((Math.abs(relation) * 2) + (levelobject.getLevel() / 3.500));
                }

                if (bo.getxBall() - breakobject.getCenterBreakX() > 0) {
                    bo.setCollideToBreakAndMoveToRight(true);
                } else {
                    bo.setCollideToBreakAndMoveToRight(false);
                }
                //System.out.println("Colide2");
            }
        }

        if (bo.getxBall() >= sceneWidth - bo.getBallRadius()) {
            resetCollideFlags();
            bo.setCollideToRightWall(true);
        }

        if (bo.getxBall() <= bo.getBallRadius()) {
            resetCollideFlags();
            bo.setCollideToLeftWall(true);
        }

        if (bo.isCollideToBreak()) {
            if (bo.isCollideToBreakAndMoveToRight()) {
                bo.setGoRightBall(true);
            } else {
                bo.setGoRightBall(false);
            }
        }

        //Wall Colide

        if (bo.isCollideToRightWall()) {
            bo.setGoRightBall(false);
        }

        if (bo.isCollideToLeftWall()) {
            bo.setGoRightBall(true);
        }

        //Block Colide

        if (bo.isCollideToRightBlock()) {
            bo.setGoRightBall(true);
        }

        if (bo.isCollideToLeftBlock()) {
            bo.setGoRightBall(false);
        }

        if (bo.isCollideToTopBlock()) {
            bo.setGoDownBall(false);
        }

        if (bo.isCollideToBottomBlock()) {
            bo.setGoDownBall(true);
        }

    }


    private void checkDestroyedCount() {
        if (levelobject.getDestroyedBlockCount() == blockobject.getBlocks().size()) {
            if (levelobject.isGetHeart() && levelobject.getHeart() > levelobject.getRestartFromHeart()) {
                System.out.println("Well done! You pass the level without losing any heart. +20 score for you");
                levelobject.setScore(levelobject.getScore()+20);
            }
            if (!levelobject.isGetHeart() && levelobject.getHeart() == levelobject.getRestartFromHeart()) {
                System.out.println("Well done! You pass the level without losing any heart. +20 score for you");
                levelobject.setScore(levelobject.getScore()+20);
            }
            //TODO win level todo...
            //System.out.println("You Win");
            levelobject.setGetHeart(false);
            if (levelobject.getLevel() <= 18) {
                nextLevel();
            }

        }
    }


    private void saveGame() {
        new Thread(() -> {
            new File(savePathDir).mkdirs();
            File file = new File(savePath);
            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream(file));

                outputStream.writeInt(levelobject.getLevel());
                outputStream.writeInt(levelobject.getScore());
                outputStream.writeInt(levelobject.getHeart());
                outputStream.writeBoolean(levelobject.isGetHeart());
                outputStream.writeInt(0);
                outputStream.writeInt(levelobject.getRestartFromHeart());
                outputStream.writeInt(levelobject.getRestartFromLevel());
                outputStream.writeInt(levelobject.getRestartFromScore());
                outputStream.writeDouble(bo.getxBall());
                outputStream.writeDouble(bo.getyBall());
                outputStream.writeDouble(breakobject.getxBreak());
                outputStream.writeDouble(breakobject.getyBreak());
                outputStream.writeDouble(breakobject.getCenterBreakX());
                outputStream.writeLong(time);
                outputStream.writeLong(goldTime);
                outputStream.writeDouble(bo.getvX());


                outputStream.writeBoolean(levelobject.isExistHeartBlock());
                outputStream.writeBoolean(levelobject.isGoldStatus());
                outputStream.writeBoolean(bo.isGoDownBall());
                outputStream.writeBoolean(bo.isGoRightBall());
                outputStream.writeBoolean(bo.isCollideToBreak());
                outputStream.writeBoolean(bo.isCollideToBreakAndMoveToRight());
                outputStream.writeBoolean(bo.isCollideToRightWall());
                outputStream.writeBoolean(bo.isCollideToLeftWall());
                outputStream.writeBoolean(bo.isCollideToRightBlock());
                outputStream.writeBoolean(bo.isCollideToBottomBlock());
                outputStream.writeBoolean(bo.isCollideToLeftBlock());
                outputStream.writeBoolean(bo.isCollideToTopBlock());

                ArrayList<BlockSerializable> blockSerializables = new ArrayList<BlockSerializable>();
                if (!blockobject.getBlocks().isEmpty()) {
                    for (Block block : blockobject.getBlocks()) {
                        if (block.isDestroyed) {
                            continue;
                        }
                        blockSerializables.add(new BlockSerializable(block.row, block.column, block.type));
                    }
                }
                outputStream.writeObject(blockSerializables);


                Platform.runLater(() -> {
                    Main main = Main.this;
                    new Score().showMessage("Game Saved", main);
                });


            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();

    }

    private void nextLevel() {
        levelobject.setRestartFromLevel(levelobject.getLevel() + 1);
        levelobject.setRestartFromHeart(levelobject.getHeart());
        levelobject.setRestartFromScore(levelobject.getScore());

        bo.setvX(1.000);
        // stop the engine
        engine.stop();
        // reset flags and game state
        resetCollideFlags();
        bo.setGoDownBall(true);
        bo.setGoRightBall(true);
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);


        time = 0;
        goldTime = 0;
        Platform.runLater(() -> {
            root.getChildren().clear();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();
            blockobject.getTraps().clear();
            levelobject.setDestroyedBlockCount(0);

            try {
                startGame();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (levelobject.getLevel() == 18) {
            levelobject.setRestartFromScore(0);
            levelobject.setRestartFromHeart(3);
            levelobject.setRestartFromLevel(1);
            Platform.runLater(() -> {
                new Score().showCongrat(this);
                new Win();
                root.getStyleClass().add("win");
                System.out.println("You pass all the levels!");
                GameEngine.setPaused(true);
            });
        }
    }


    public void restartGame() {

        try {
            levelobject.setLevel(0);
            levelobject.setHeart(3);
            levelobject.setScore(0);
            bo.setvX(1.000);
            levelobject.setDestroyedBlockCount(0);
            resetCollideFlags();
            bo.setGoDownBall(true);
            bo.setGoRightBall(true);
            levelobject.setGoldStatus(false);
            levelobject.setExistHeartBlock(false);
            levelobject.setGetHeart(false);
            time = 0;
            goldTime = 0;

            clearBlocks();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();
            blockobject.getTraps().clear();

            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    public void onUpdate() {
        Platform.runLater(() -> {
            levelobject.getScoreLabel().setText("Score: " + levelobject.getScore());
            levelobject.getHeartLabel().setText("Heart : " + levelobject.getHeart());

            breakobject.getRect().setX(breakobject.getxBreak());
            breakobject.getRect().setY(breakobject.getyBreak());
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
                        resetCollideFlags();

                        if (block.type == Block.BLOCK_CHEESE) {
                            final Bonus cheese = new Bonus(block.row, block.column);
                            cheese.timeCreated = time;
                            Platform.runLater(() -> Platform.runLater(() -> {
                                root.getChildren().add(cheese.cheese);
                            }));
                            blockobject.getCheeses().add(cheese);
                        }

                        if (block.type == Block.BLOCK_TRAP) {
                            final Trap mousetrap = new Trap(block.row, block.column);
                            mousetrap.timeCreated = time;
                            Platform.runLater(() -> Platform.runLater(() -> {
                                root.getChildren().add(mousetrap.mousetrap);
                            }));
                            blockobject.getTraps().add(mousetrap);
                        }

                        if (block.type == Block.BLOCK_STAR) {
                            goldTime = time;
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
        long currentTime = System.currentTimeMillis();
        double elapsedTime = (currentTime - lastUpdateTime) / 1000.0;  // Convert to seconds
        lastUpdateTime = currentTime;

        checkDestroyedCount();
        setPhysicsToBall();

        if (time - goldTime > 5000) {
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

            if (cheese.y > sceneHeight || cheese.taken) {
                cheesesToRemove.add(cheese);
                continue;
            }
            if (cheese.y >= breakobject.getyBreak() && cheese.y <= breakobject.getyBreak() + breakobject.getBreakHeight() && cheese.x >= breakobject.getxBreak() && cheese.x <= breakobject.getxBreak() + breakobject.getBreakWidth()) {
                System.out.println("You Got the cheese! +3 score for you");
                cheese.taken = true;
                cheese.cheese.setVisible(false);
                levelobject.setScore(levelobject.getScore()+3);
                Platform.runLater(() -> {
                    new Score().show(cheese.x, cheese.y, 3, this);
                });
            }
            cheese.y += elapsedTime * ((time - cheese.timeCreated) / 1000.0) + 1.0;
        }
        blockobject.getCheeses().removeAll(cheesesToRemove);


        List<Trap> trapsToRemove = new ArrayList<>();
        Iterator<Trap> trapIterator = blockobject.getTraps().iterator();
        while (trapIterator.hasNext()) {
            Trap mousetrap = trapIterator.next();

            if (mousetrap.y > sceneHeight || mousetrap.taken) {
                trapsToRemove.add(mousetrap);
                continue;
            }
            if (mousetrap.y >= breakobject.getyBreak() && mousetrap.y <= breakobject.getyBreak() + breakobject.getBreakHeight() && mousetrap.x >= breakobject.getxBreak() && mousetrap.x <= breakobject.getxBreak() + breakobject.getBreakWidth()) {
                System.out.println("You Got the trap! -3 score for you");
                mousetrap.taken = true;
                mousetrap.mousetrap.setVisible(false);
                levelobject.setScore(levelobject.getScore()-3);
                Platform.runLater(() -> new Score().show(mousetrap.x, mousetrap.y, -3, this));
            }
            mousetrap.y += elapsedTime * ((time - mousetrap.timeCreated) / 1000.0) + 1.0;
        }
        blockobject.getTraps().removeAll(trapsToRemove);

    }


    @Override
    public void onTime(long time) {
        this.time = time;
    }
}