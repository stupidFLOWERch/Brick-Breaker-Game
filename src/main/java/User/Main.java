package User;

import Block.BlockSerializable;
import Ball.BallObject;
import Block.BlockObject;
import Block.Block;
import Block.InitBlock;
import Break.BreakObject;
import UI.MainMenu;
import UI.PauseMenu;
import UI.Score;
import brickGame.*;
import brickGame.LevelObject;
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
import java.util.Random;

import Sound.Sound;
import Sound.Bgm;
import Sound.Win;


public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {

    private final int sceneWidth = 500;
    private final int sceneHeight = 700;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private int restartFromLevel = 1;
    private int restartFromHeart = 3;
    private int restartFromScore = 0;
    private GameEngine engine;
    public static String savePath = "C:/save/save.mdds";
    public static String savePathDir = "C:/save/";
    public Pane root;
    private final Random random = new Random();
    private boolean loadFromSave = false;
    Stage primaryStage;
    private PauseMenu pauseMenu;
    private Scene mainScene;

    private LevelObject levelobject;
    private BallObject bo;
    private BreakObject breakobject;
    private BlockObject blockobject;
    private InitBlock initblock;
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
        levelobject.setScore(0);
        levelobject.setHeart(3);
        levelobject.setLevel(0);
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);
        levelobject.setGetHeart(false);
        levelobject.setDestroyedBlockCount(0);
        levelobject.setFromRestartGame(false);

        this.primaryStage = primaryStage;
        MainMenu mainMenu = new MainMenu(this);

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

    public Scene getMainScene() {
        return mainScene;
    }
    public void clearBlocks() {
        Platform.runLater(() -> root.getChildren().clear());
    }


    public void startGame() {
        new Bgm();
        if (!loadFromSave) {
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

            initBall();
            initBreak();
            initblock.initBlock(levelobject.getLevel(),blockobject.getBlocks());

        }


        root = new Pane();

        levelobject.setScoreLabel(new Label("Score: " + levelobject.getScore()));
        levelobject.setLevelLabel(new Label("Level: " + levelobject.getLevel()));
        levelobject.getLevelLabel().setTranslateY(20);
        levelobject.setHeartLabel(new Label("Heart : " + levelobject.getHeart()));
        levelobject.getHeartLabel().setTranslateX(sceneWidth - 70);

        clearBlocks();
        Platform.runLater(() -> root.getChildren().addAll(blockobject.getRect(), bo.getBall(), levelobject.getScoreLabel(), levelobject.getHeartLabel(), levelobject.getLevelLabel()));

        for (Block block : blockobject.getBlocks()) {
            Platform.runLater(() -> root.getChildren().add(block.rect));
        }

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();

        if (!loadFromSave && levelobject.isFromRestartGame()) {
            if (levelobject.getLevel() > 1 && levelobject.getLevel() <= 18) {
                restartGameEngine();
            }
        } else if (loadFromSave) {
            engine = new GameEngine();
            engine.setOnAction(this);
            engine.setFps(120);
            engine.start();
            loadFromSave = false;
        }
        levelobject.setFromRestartGame(false);
    }


    public void restartGameEngine() {
        engine = new GameEngine();
        engine.setOnAction(this);
        engine.setFps(120);
        engine.start();
    }


    private void initBall() {
        bo.setxBall(sceneWidth / 2.0);
        bo.setyBall(500);
        bo.setBall(new Circle());
        bo.getBall().setRadius(bo.getBallRadius());
        bo.getBall().setFill(new ImagePattern(new Image("ball.png")));
    }
    private void initBreak() {
        blockobject.setRect(new Rectangle());
        blockobject.getRect().setWidth(breakobject.getBreakWidth());
        blockobject.getRect().setHeight(breakobject.getBreakHeight());
        breakobject.setxBreak(sceneWidth / 2.0 - breakobject.getHalfBreakWidth());
        blockobject.getRect().setX(breakobject.getxBreak());
        blockobject.getRect().setY(breakobject.getyBreak());

        ImagePattern pattern = new ImagePattern(new Image("block.jpg"));
        blockobject.getRect().setFill(pattern);
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
        pauseMenu = new PauseMenu(primaryStage, this);
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

    public void restartLevel() {
        restartGame();
        levelobject.setLevel(restartFromLevel);
        levelobject.setHeart(restartFromHeart);
        levelobject.setScore(restartFromScore);
        loadFromSave = false;
        resumeGame();
        levelobject.setFromRestartGame(true);

        startGame();
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

    private boolean goDownBall = true;
    private boolean goRightBall = true;
    private boolean collideToBreak = false;
    private boolean collideToBreakAndMoveToRight = true;
    private boolean collideToRightWall = false;
    private boolean collideToLeftWall = false;
    private boolean collideToRightBlock = false;
    private boolean collideToBottomBlock = false;
    private boolean collideToLeftBlock = false;
    private boolean collideToTopBlock = false;

    private double vX = 1.000;
    private double vY = 1.000;


    private void resetCollideFlags() {

        collideToBreak = false;
        collideToBreakAndMoveToRight = false;
        collideToRightWall = false;
        collideToLeftWall = false;

        collideToRightBlock = false;
        collideToBottomBlock = false;
        collideToLeftBlock = false;
        collideToTopBlock = false;
    }

    private void setPhysicsToBall() {
        synchronized (this) {
            if (bo.getyBall() >= sceneHeight - bo.getBallRadius() && goDownBall) {
                resetCollideFlags();
                goDownBall = false;
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

            if (goDownBall) {
                bo.setyBall(bo.getyBall() + vY);
            } else {
                bo.setyBall(bo.getyBall() - vY);
            }

            if (goRightBall) {
                bo.setxBall(bo.getxBall() + vX);
            } else {
                bo.setxBall(bo.getxBall() - vX);
            }

            if (bo.getyBall() <= bo.getBallRadius()) {
                resetCollideFlags();
                goDownBall = true;
                return;
            }
        }

        if (bo.getyBall() >= breakobject.getyBreak() - bo.getBallRadius()) {
            //System.out.println("Colide1");
            if (bo.getxBall() >= breakobject.getxBreak() && bo.getxBall() <= breakobject.getxBreak() + breakobject.getBreakWidth()) {
                resetCollideFlags();
                collideToBreak = true;
                goDownBall = false;

                double relation = (bo.getxBall() - breakobject.getCenterBreakX()) / ((double) breakobject.getBreakWidth() / 2);

                if (Math.abs(relation) <= 0.3) {
                    //vX = 0;
                    vX = Math.abs(relation);
                } else if (Math.abs(relation) > 0.3 && Math.abs(relation) <= 0.7) {
                    vX = (Math.abs(relation) * 1.5) + (levelobject.getLevel() / 3.500);
                    //System.out.println("vX " + vX);
                } else {
                    vX = (Math.abs(relation) * 2) + (levelobject.getLevel() / 3.500);
                    //System.out.println("vX " + vX);
                }

                if (bo.getxBall() - breakobject.getCenterBreakX() > 0) {
                    collideToBreakAndMoveToRight = true;
                } else {
                    collideToBreakAndMoveToRight = false;
                }
                //System.out.println("Colide2");
            }
        }

        if (bo.getxBall() >= sceneWidth - bo.getBallRadius()) {
            resetCollideFlags();
            collideToRightWall = true;
        }

        if (bo.getxBall() <= bo.getBallRadius()) {
            resetCollideFlags();
            collideToLeftWall = true;
        }

        if (collideToBreak) {
            if (collideToBreakAndMoveToRight) {
                goRightBall = true;
            } else {
                goRightBall = false;
            }
        }

        //Wall Colide

        if (collideToRightWall) {
            goRightBall = false;
        }

        if (collideToLeftWall) {
            goRightBall = true;
        }

        //Block Colide

        if (collideToRightBlock) {
            goRightBall = true;
        }

        if (collideToLeftBlock) {
            goRightBall = false;
        }

        if (collideToTopBlock) {
            goDownBall = false;
        }

        if (collideToBottomBlock) {
            goDownBall = true;
        }

    }


    private void checkDestroyedCount() {
        if (levelobject.getDestroyedBlockCount() == blockobject.getBlocks().size()) {
            if (levelobject.isGetHeart() && levelobject.getHeart() > restartFromHeart) {
                System.out.println("Well done! You pass the level without losing any heart. +20 score for you");
                levelobject.setScore(levelobject.getScore()+20);
            }
            if (!levelobject.isGetHeart() && levelobject.getHeart() == restartFromHeart) {
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
                outputStream.writeInt(restartFromHeart);
                outputStream.writeInt(restartFromLevel);
                outputStream.writeInt(restartFromScore);
                outputStream.writeDouble(bo.getxBall());
                outputStream.writeDouble(bo.getyBall());
                outputStream.writeDouble(breakobject.getxBreak());
                outputStream.writeDouble(breakobject.getyBreak());
                outputStream.writeDouble(breakobject.getCenterBreakX());
                outputStream.writeLong(blockobject.getTime());
                outputStream.writeLong(blockobject.getGoldTime());
                outputStream.writeDouble(vX);


                outputStream.writeBoolean(levelobject.isExistHeartBlock());
                outputStream.writeBoolean(levelobject.isGoldStatus());
                outputStream.writeBoolean(goDownBall);
                outputStream.writeBoolean(goRightBall);
                outputStream.writeBoolean(collideToBreak);
                outputStream.writeBoolean(collideToBreakAndMoveToRight);
                outputStream.writeBoolean(collideToRightWall);
                outputStream.writeBoolean(collideToLeftWall);
                outputStream.writeBoolean(collideToRightBlock);
                outputStream.writeBoolean(collideToBottomBlock);
                outputStream.writeBoolean(collideToLeftBlock);
                outputStream.writeBoolean(collideToTopBlock);

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

    public void loadGame() {

        LoadSave loadSave = new LoadSave();
        loadSave.read();


        levelobject.setExistHeartBlock(loadSave.isExistHeartBlock);
        levelobject.setGoldStatus(loadSave.isGoldStatus);
        levelobject.setGetHeart(loadSave.getHeart);
        goDownBall = loadSave.goDownBall;
        goRightBall = loadSave.goRightBall;
        collideToBreak = loadSave.collideToBreak;
        collideToBreakAndMoveToRight = loadSave.collideToBreakAndMoveToRight;
        collideToRightWall = loadSave.collideToRightWall;
        collideToLeftWall = loadSave.collideToLeftWall;
        collideToRightBlock = loadSave.collideToRightBlock;
        collideToBottomBlock = loadSave.collideToBottomBlock;
        collideToLeftBlock = loadSave.collideToLeftBlock;
        collideToTopBlock = loadSave.collideToTopBlock;
        levelobject.setLevel(loadSave.level);
        levelobject.setScore(loadSave.score);
        levelobject.setHeart(loadSave.heart);
        restartFromLevel = loadSave.restartFromLevel;
        restartFromHeart = loadSave.restartFromHeart;
        restartFromScore = loadSave.restartFromScore;
        levelobject.setDestroyedBlockCount(loadSave.destroyedBlockCount);
        bo.setxBall(loadSave.xBall);
        bo.setyBall(loadSave.yBall);
        breakobject.setxBreak(loadSave.xBreak);
        breakobject.setyBreak(loadSave.yBreak);
        breakobject.setCenterBreakX(loadSave.centerBreakX);
        blockobject.setTime(loadSave.time);
        blockobject.setGoldTime(loadSave.goldTime);
        vX = loadSave.vX;

        clearBlocks();

        blockobject.getBlocks().clear();
        blockobject.getCheeses().clear();

        for (BlockSerializable ser : loadSave.blocks) {
            int r = random.nextInt(200);
            blockobject.getBlocks().add(new Block(ser.row, ser.column, blockobject.getColors()[r % blockobject.getColors().length], ser.type));
        }


        try {
            loadFromSave = true;
            startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void nextLevel() {
        restartFromScore = levelobject.getScore();
        restartFromHeart = levelobject.getHeart();
        restartFromLevel = levelobject.getLevel() + 1;
        vX = 1.000;
        // stop the engine
        engine.stop();
        // reset flags and game state
        resetCollideFlags();
        goDownBall = true;
        goRightBall = true;
        levelobject.setGoldStatus(false);
        levelobject.setExistHeartBlock(false);


        blockobject.setTime(0);
        blockobject.setGoldTime(0);
        Platform.runLater(() -> {
            root.getChildren().clear();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();
            levelobject.setDestroyedBlockCount(0);

            try {
                startGame();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (levelobject.getLevel() == 18) {
            restartFromScore = 0;
            restartFromHeart = 3;
            restartFromLevel = 1;
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
            vX = 1.000;
            levelobject.setDestroyedBlockCount(0);
            resetCollideFlags();
            goDownBall = true;
            goRightBall = true;
            levelobject.setGoldStatus(false);
            levelobject.setExistHeartBlock(false);
            levelobject.setGetHeart(false);
            blockobject.setTime(0);
            blockobject.setGoldTime(0);

            clearBlocks();
            blockobject.getBlocks().clear();
            blockobject.getCheeses().clear();

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

            blockobject.getRect().setX(breakobject.getxBreak());
            blockobject.getRect().setY(breakobject.getyBreak());
            bo.getBall().setCenterX(bo.getxBall());
            bo.getBall().setCenterY(bo.getyBall());
        });
        for (Bonus cheese : blockobject.getCheeses()) {
            cheese.cheese.setY(cheese.y);
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
                            cheese.timeCreated = blockobject.getTime();
                            Platform.runLater(() -> Platform.runLater(() -> {
                                root.getChildren().add(cheese.cheese);
                            }));
                            blockobject.getCheeses().add(cheese);
                        }

                        if (block.type == Block.BLOCK_STAR) {
                            blockobject.setGoldTime(blockobject.getTime());
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
                            collideToRightBlock = true;
                        } else if (hitCode == Block.HIT_BOTTOM) {
                            collideToBottomBlock = true;
                        } else if (hitCode == Block.HIT_LEFT) {
                            collideToLeftBlock = true;
                        } else if (hitCode == Block.HIT_TOP) {
                            collideToTopBlock = true;
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

        if (blockobject.getTime() - blockobject.getGoldTime() > 5000) {
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
            cheese.y += elapsedTime * ((blockobject.getTime() - cheese.timeCreated) / 1000.0) + 1.0;
        }
        blockobject.getCheeses().removeAll(cheesesToRemove);


        //System.out.println("time is:" + time + " goldTime is " + goldTime);

    }


    @Override
    public void onTime(long time) {
        this.blockobject.setTime(blockobject.getTime());
    }
}