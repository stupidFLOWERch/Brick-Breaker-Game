package User;

import brickGame.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
import static brickGame.LoadSave.check_mdds;





public class Main extends Application implements EventHandler<KeyEvent>, GameEngine.OnAction {


    private int level = 0;

    private double xBreak = 0.0f;
    private double centerBreakX;
    private double yBreak = 640.0f;

    private final int breakWidth     = 130;
    private final int breakHeight    = 30;
    private final int halfBreakWidth = breakWidth / 2;

    private final int sceneWidth = 500;
    private final int sceneHeight = 700;

    private static final int LEFT  = 1;
    private static final int RIGHT = 2;

    private Circle ball;
    private double xBall;
    private double yBall;

    private boolean isGoldStatus = false;
    private boolean isExistHeartBlock = false;

    private Rectangle rect;
    private final double ballRadius = 10;

    private int destroyedBlockCount = 0;

    //private double v = 1.000;

    private int  heart    = 3;
    private int  score    = 0;
    private long time     = 0;
    private long hitTime  = 0;
    private long goldTime = 0;

    private GameEngine engine;
    public static String savePath    = "C:/save/save.mdds";
    public static String savePathDir = "C:/save/";

    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Bonus> chocos = new ArrayList<Bonus>();
    private Color[]          colors = new Color[]{
            Color.MAGENTA,
            Color.RED,
            Color.GOLD,
            Color.CORAL,
            Color.AQUA,
            Color.VIOLET,
            Color.GREENYELLOW,
            Color.ORANGE,
            Color.PINK,
            Color.SLATEGREY,
            Color.YELLOW,
            Color.TOMATO,
            Color.TAN,
    };
    public  Pane             root;
    private Label            scoreLabel;
    private Label            heartLabel;
    private Label            levelLabel;
    private final Random random = new Random();

    private boolean loadFromSave = false;

    Stage  primaryStage;
    Button load    = null;
    Button newGame = null;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        if (!loadFromSave) {
            level++;
            if (level >1){
                Platform.runLater(()->{
                new Score().showMessage("Level Up :)", this);
                });
            }
            if (level == 18) {
                Platform.runLater(()->{
                new Score().showWin(this);
                });
                return ;
            }

            initBall();
            initBreak();
            initBoard();

            load = new Button("Load Game");
            newGame = new Button("Start New Game");
            load.setTranslateX(220);
            load.setTranslateY(300);
            newGame.setTranslateX(220);
            newGame.setTranslateY(340);

        }


        root = new Pane();
        scoreLabel = new Label("Score: " + score);
        levelLabel = new Label("Level: " + level);
        levelLabel.setTranslateY(20);
        heartLabel = new Label("Heart : " + heart);
        heartLabel.setTranslateX(sceneWidth - 70);
        if (!loadFromSave) {
            root.getChildren().clear();
            Platform.runLater(()->root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel, newGame, load));
        } else {
            root.getChildren().clear();
            Platform.runLater(()->root.getChildren().addAll(rect, ball, scoreLabel, heartLabel, levelLabel));
        }
        boolean b=check_mdds(savePath);
        load.setVisible(b);
        for (Block block : blocks) {
            Platform.runLater(()->root.getChildren().add(block.rect));
        }
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(this);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();

        if (!loadFromSave) {
            if (level > 1 && level < 18) {
                load.setVisible(false);
                newGame.setVisible(false);
                engine = new GameEngine();
                engine.setOnAction(this);
                engine.setFps(120);
                engine.start();
            }

            load.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadGame();

                    load.setVisible(false);
                    newGame.setVisible(false);
                }
            });

            newGame.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    engine = new GameEngine();
                    engine.setOnAction(Main.this);
                    engine.setFps(120);
                    engine.start();

                    load.setVisible(false);
                    newGame.setVisible(false);
                }
            });
        } else {
            engine = new GameEngine();
            engine.setOnAction(this);
            engine.setFps(120);
            engine.start();
            loadFromSave = false;
        }


    }

    private void initBall() {
        xBall = sceneWidth/2.0;
        yBall = 500;
        //yBall = random.nextInt(sceneHeight - 200) + ((level + 1) * Block.getHeight()) + 15;
        ball = new Circle();
        ball.setRadius(ballRadius);
        ball.setFill(new ImagePattern(new Image("ball.png")));
    }
    private void initBreak() {
        rect = new Rectangle();
        rect.setWidth(breakWidth);
        rect.setHeight(breakHeight);
        xBreak = sceneWidth/2.0 - halfBreakWidth;
        rect.setX(xBreak);
        rect.setY(yBreak);

        ImagePattern pattern = new ImagePattern(new Image("block.jpg"));

        rect.setFill(pattern);
    }
    private void initBoard() {
        synchronized (this) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < level + 1; j++) {
                    int r = random.nextInt(500);
                    if (r % 5 == 0) {
                        continue;
                    }
                    int type;
                    if (r % 10 == 1) {
                        type = Block.BLOCK_CHOCO;
                    } else if (r % 10 == 2) {
                        if (!isExistHeartBlock) {
                            type = Block.BLOCK_HEART;
                            isExistHeartBlock = true;
                        } else {
                            type = Block.BLOCK_NORMAL;
                        }
                    } else if (r % 10 == 3) {
                        type = Block.BLOCK_STAR;
                    } else {
                        type = Block.BLOCK_NORMAL;
                    }
                    blocks.add(new Block(j, i, colors[r % (colors.length)], type));
                    //System.out.println("colors " + r % (colors.length));
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
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
            case DOWN:
                //setPhysicsToBall();
                break;
            case S:
                saveGame();
                break;
        }
    }

    private void move(final int direction) {
        new Thread(() -> {
            int sleepTime = 4;
            for (int i = 0; i < 30; i++) {
                    synchronized (this) {
                        if (xBreak == (sceneWidth - breakWidth) && direction == RIGHT) {
                            return;
                        }
                        if (xBreak == 0 && direction == LEFT) {
                            return;
                        }
                        if (direction == RIGHT && xBreak < (sceneWidth - breakWidth)) {
                            xBreak++;
                        }
                        if (direction == LEFT && xBreak > 0) {
                            xBreak--;
                        }
                        centerBreakX = xBreak + halfBreakWidth;
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


    private boolean goDownBall                  = true;
    private boolean goRightBall                 = true;
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
        //v = ((time - hitTime) / 1000.000) + 1.000;
        synchronized (this) {
        if (goDownBall) {
            yBall += vY;
        } else {
            yBall -= vY;
        }

        if (goRightBall) {
            xBall += vX;
        } else {
            xBall -= vX;
        }

        if (yBall <= ballRadius) {
            //vX = 1.000;
            resetCollideFlags();
            goDownBall = true;
            return;
        }
        if (yBall >= sceneHeight - ballRadius) {
            goDownBall = false;
            if (!isGoldStatus) {
                //TODO gameover
                heart--;
                new Score().show(sceneWidth / 2.0, sceneHeight / 2.0, -1, this);

                if (heart == 0) {
                    new Score().showGameOver(this);
                    engine.stop();
                }
            }
        }
            //return;
        }

        if (yBall >= yBreak - ballRadius) {
            //System.out.println("Colide1");
            if (xBall >= xBreak && xBall <= xBreak + breakWidth) {
                hitTime = time;
                resetCollideFlags();
                collideToBreak = true;
                goDownBall = false;

                double relation = (xBall - centerBreakX) / ((double) breakWidth / 2);

                if (Math.abs(relation) <= 0.3) {
                    //vX = 0;
                    vX = Math.abs(relation);
                } else if (Math.abs(relation) > 0.3 && Math.abs(relation) <= 0.7) {
                    vX = (Math.abs(relation) * 1.5) + (level / 3.500);
                    //System.out.println("vX " + vX);
                } else {
                    vX = (Math.abs(relation) * 2) + (level / 3.500);
                    //System.out.println("vX " + vX);
                }

                if (xBall - centerBreakX > 0) {
                    collideToBreakAndMoveToRight = true;
                } else {
                    collideToBreakAndMoveToRight = false;
                }
                //System.out.println("Colide2");
            }
        }

        if (xBall >= sceneWidth - ballRadius) {
            resetCollideFlags();
            //vX = 1.000;
            collideToRightWall = true;
        }

        if (xBall <= ballRadius) {
            resetCollideFlags();
            //vX = 1.000;
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
        if (destroyedBlockCount == blocks.size()) {
            //TODO win level todo...
            //System.out.println("You Win");

            nextLevel();
        }
    }

    private void saveGame() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new File(savePathDir).mkdirs();
                File file = new File(savePath);
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream(file));

                    outputStream.writeInt(level);
                    outputStream.writeInt(score);
                    outputStream.writeInt(heart);
                    outputStream.writeInt(0);


                    outputStream.writeDouble(xBall);
                    outputStream.writeDouble(yBall);
                    outputStream.writeDouble(xBreak);
                    outputStream.writeDouble(yBreak);
                    outputStream.writeDouble(centerBreakX);
                    outputStream.writeLong(time);
                    outputStream.writeLong(goldTime);
                    outputStream.writeDouble(vX);


                    outputStream.writeBoolean(isExistHeartBlock);
                    outputStream.writeBoolean(isGoldStatus);
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
                    if(!blocks.isEmpty()) {
                        for (Block block : blocks) {
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
            }

        }).start();

    }

    private void loadGame() {

        LoadSave loadSave = new LoadSave();
        loadSave.read();


        isExistHeartBlock = loadSave.isExistHeartBlock;
        isGoldStatus = loadSave.isGoldStatus;
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
        level = loadSave.level;
        score = loadSave.score;
        heart = loadSave.heart;
        destroyedBlockCount = loadSave.destroyedBlockCount;
        xBall = loadSave.xBall;
        yBall = loadSave.yBall;
        xBreak = loadSave.xBreak;
        yBreak = loadSave.yBreak;
        centerBreakX = loadSave.centerBreakX;
        time = loadSave.time;
        goldTime = loadSave.goldTime;
        vX = loadSave.vX;

        root.getChildren().clear();
        blocks.clear();
        chocos.clear();

        for (BlockSerializable ser : loadSave.blocks) {
            int r = random.nextInt(200);
            blocks.add(new Block(ser.row, ser.column, colors[r % colors.length], ser.type));
        }


        try {
            loadFromSave = true;
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void nextLevel() {
        vX = 1.000;
        // stop the engine
        engine.stop();
        // reset flags and game state
        resetCollideFlags();
        goDownBall = true;

        isGoldStatus = false;
        isExistHeartBlock = false;


         hitTime = 0;
         time = 0;
         goldTime = 0;
         Platform.runLater(()-> {
            root.getChildren().clear();
            blocks.clear();
            chocos.clear();
            destroyedBlockCount = 0;


            try {
                start(primaryStage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
    }

    public void restartGame() {

        try {
            level = 0;
            heart = 3;
            score = 0;
            vX = 1.000;
            destroyedBlockCount = 0;
            resetCollideFlags();
            goDownBall = true;

            isGoldStatus = false;
            isExistHeartBlock = false;
            hitTime = 0;
            time = 0;
            goldTime = 0;

            root.getChildren().clear();
            blocks.clear();
            chocos.clear();

            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    public void onUpdate() {
        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + score);
            heartLabel.setText("Heart : " + heart);

            rect.setX(xBreak);
            rect.setY(yBreak);
            ball.setCenterX(xBall);
            ball.setCenterY(yBall);
        });
                for (Bonus choco : chocos) {
                    choco.choco.setY(choco.y);
                }

                List<Block> blocksCopy = new ArrayList<>(blocks);


                if (yBall >= Block.getPaddingTop() && yBall <= (Block.getHeight() * (level + 1)) + Block.getPaddingTop()) {
                    for (final Block block : blocksCopy) {
                        try {
                            int hitCode = block.checkHitToBlock(xBall, yBall);
                            if (hitCode != Block.NO_HIT) {
                                score += 1;

                                new Score().show(block.x, block.y, 1, Main.this);

                                block.rect.setVisible(false);
                                block.isDestroyed = true;
                                destroyedBlockCount++;
                                new Sound();
                                //System.out.println("size is " + blocks.size());
                                resetCollideFlags();

                                if (block.type == Block.BLOCK_CHOCO) {
                                    final Bonus choco = new Bonus(block.row, block.column);
                                    choco.timeCreated = time;
                                    Platform.runLater(() -> Platform.runLater(()-> {
                                        root.getChildren().add(choco.choco);
                                    }));
                                    chocos.add(choco);
                                }

                                if (block.type == Block.BLOCK_STAR) {
                                    goldTime = time;
                                    ball.setFill(new ImagePattern(new Image("goldball.png")));
                                    System.out.println("gold ball");
                                    root.getStyleClass().add("goldRoot");
                                    isGoldStatus = true;
                                }

                                if (block.type == Block.BLOCK_HEART) {
                                    heart++;
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




    @Override
    public void onInit() {

    }

    @Override
    public void onPhysicsUpdate() {
        checkDestroyedCount();
        setPhysicsToBall();


        if (time - goldTime > 5000) {
            Platform.runLater(()->{
            ball.setFill(new ImagePattern(new Image("ball.png")));
            root.getStyleClass().remove("goldRoot");
        });
            isGoldStatus = false;
        }


        Iterator<Bonus> chocoIterator = chocos.iterator();
        while (chocoIterator.hasNext()) {
            Bonus choco = chocoIterator.next();

            //for (Bonus choco : chocos) {
            if (choco.y > sceneHeight || choco.taken) {
                continue;
            }
            if (choco.y >= yBreak && choco.y <= yBreak + breakHeight && choco.x >= xBreak && choco.x <= xBreak + breakWidth) {
                System.out.println("You Got it and +3 score for you");
                choco.taken = true;
                choco.choco.setVisible(false);
                score += 3;
                Platform.runLater(()->{new Score().show(choco.x, choco.y, 3, this);});
                // Remove the element using the iterator
                chocoIterator.remove();
            }
            choco.y += ((time - choco.timeCreated) / 1000.000) + 1.000;
        }

        //System.out.println("time is:" + time + " goldTime is " + goldTime);

    }


    @Override
    public void onTime(long time) {
        this.time = time;
    }
}
