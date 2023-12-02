package Score;

import Ball.BallObject;
import Block.BlockObject;
import PlayGame.RestartGame;
import brickGame.Main;
import PlayGame.LevelObject;

import PlayGame.StartGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import static LoadGameSaveGame.LoadSave.checkfile;


public class Score {

    private static final int ANIMATION_DURATION = 21;
    private static final int ANIMATION_DELAY = 15;

    SaveHighScore savehighscore = new SaveHighScore();
    RestartGame restartgame = new RestartGame();
    public void show(final double x, final double y, int score, final Main main) {
        LevelObject levelobject = new LevelObject();
        levelobject.setScore(score);
        String sign;
        if (levelobject.getScore() >= 0) {
            sign = "+";
        } else {
            sign = "";
        }
        final Label label = new Label(sign + levelobject.getScore());
        label.setTranslateX(x);
        label.setTranslateY(y);

        Platform.runLater(() -> main.root.getChildren().add(label));

        new Thread(() -> {
            for (int i = 0; i < ANIMATION_DURATION; i++) {
                try {
                    final int currentIndex = i;
                    Platform.runLater(() -> {
                        label.setScaleX(currentIndex);
                        label.setScaleY(currentIndex);
                        label.setOpacity((20 - currentIndex) / 20.0);
                    });
                    Thread.sleep(ANIMATION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void showMessage(String message, final Main main) {
        final Label label = new Label(message);
        label.setTranslateX(220);
        label.setTranslateY(340);

        Platform.runLater(()-> main.root.getChildren().add(label));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ANIMATION_DURATION; i++) {
                    try {
                        final int currentIndex = i;
                        Platform.runLater(() -> {
                            label.setScaleX(Math.abs(currentIndex-10));
                            label.setScaleY(Math.abs(currentIndex-10));
                            label.setOpacity((20 - currentIndex) / 20.0);
                        });

                        Thread.sleep(ANIMATION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void showGameOver(Stage stage, Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        boolean b = checkfile(levelobject.getFilePath());
        levelobject.setCurrentScore(levelobject.getScore());
        if(b) {
            levelobject.setHighScore(LoadHighScore.getHighScore());
        }else{
            levelobject.setHighScore(0);
        }
        if (levelobject.getCurrentScore() > levelobject.getHighScore()) {
            Platform.runLater(() -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New High Score!");
                dialog.setHeaderText("You've reach a new high score!");
                dialog.setContentText("Please enter your name:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> {
                    // Save the new high score with the user's name
                    savehighscore.saveHighScore(name, levelobject.getCurrentScore());
                });
            });
        }

        Platform.runLater(() -> {
            Label label = new Label("Game Over :(");
            label.setTranslateX(200);
            label.setTranslateY(180);
            label.setScaleX(2);
            label.setScaleY(2);

            main.root.getChildren().clear();

            Button restart = new Button("Restart");
            restart.setTranslateX(160);
            restart.setTranslateY(300);
            restart.setOnAction(event -> restartgame.restartGame(stage, main, bo, blockobject, levelobject));

            if (main.root != null) {
            main.root.getChildren().addAll(label, restart);
            }

        });
    }

    public void showWin(Stage stage, Main main, BallObject bo, BlockObject blockobject, LevelObject levelobject) {
        StartGame startgame = new StartGame(main);
        boolean b = checkfile(levelobject.getFilePath());
        levelobject.setCurrentScore(levelobject.getScore());
        if(b) {
            levelobject.setHighScore(LoadHighScore.getHighScore());
        }else{
            levelobject.setHighScore(0);
        }
        if (levelobject.getCurrentScore() > levelobject.getHighScore()) {
            Platform.runLater(() -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New High Score!");
                dialog.setHeaderText("You've reach a new high score!");
                dialog.setContentText("Please enter your name:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> {
                    // Save the new high score with the user's name
                    savehighscore.saveHighScore(name, levelobject.getCurrentScore());
                });
            });
        }

        Platform.runLater(() -> {
            Label label = new Label("You Win :)");
            label.setTranslateX(210);
            label.setTranslateY(180);
            label.setScaleX(2);
            label.setScaleY(2);

            Button bonusLevel = new Button("Bonus Level");
            bonusLevel.setTranslateX(160);
            bonusLevel.setTranslateY(310);
            bonusLevel.setOnAction(event -> startgame.startGame());

            Button restart = new Button("Restart");
            restart.setTranslateX(160);
            restart.setTranslateY(390);
            restart.setOnAction(event -> restartgame.restartGame(stage, main, bo, blockobject, levelobject));

            if (main.root != null) {
                main.root.getChildren().addAll(label, bonusLevel, restart);
            }
        });
    }

    public void showCongrat(final Main main, LevelObject levelobject) {
        boolean b = checkfile(levelobject.getFilePath());
        levelobject.setCurrentScore(levelobject.getScore());
        if(b) {
            levelobject.setHighScore(LoadHighScore.getHighScore());
        }else{
            levelobject.setHighScore(0);
        }
        if (levelobject.getCurrentScore() > levelobject.getHighScore()) {
            Platform.runLater(() -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New High Score!");
                dialog.setHeaderText("You've reach a new high score!");
                dialog.setContentText("Please enter your name:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> {
                    // Save the new high score with the user's name
                    savehighscore.saveHighScore(name, levelobject.getCurrentScore());
                });
            });
        }

        Platform.runLater(() -> {
            Label label = new Label("Congratulations!");
            label.setTranslateX(200);
            label.setTranslateY(70);
            label.setScaleX(2);
            label.setScaleY(2);


            main.root.getChildren().clear();

            Button exit = new Button("Exit Game");
            exit.setTranslateX(160);
            exit.setTranslateY(560);
            exit.setOnAction(event -> main.showPauseMenu.exitGame());

            if (main.root != null) {
                main.root.getChildren().addAll(label, exit);
            }

        });

    }
}