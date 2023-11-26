package UI;

import User.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import sun.plugin2.message.Message;

public class Score {

    private static final int ANIMATION_DURATION = 21;
    private static final int ANIMATION_DELAY = 15;

    public void show(final double x, final double y, int score, final Main main) {
        String sign;
        if (score >= 0) {
            sign = "+";
        } else {
            sign = "";
        }
        final Label label = new Label(sign + score);
        label.setTranslateX(x);
        label.setTranslateY(y);

        Platform.runLater(() -> {
        main.root.getChildren().add(label);
        });

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

        Platform.runLater(()-> {

            main.root.getChildren().add(label);

        });

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

    public void showGameOver(final Main main) {
        Platform.runLater(() -> {
            Label label = new Label("Game Over :(");
            label.setTranslateX(200);
            label.setTranslateY(250);
            label.setScaleX(2);
            label.setScaleY(2);

            main.root.getChildren().clear();

            Button restart = new Button("Restart");
            restart.setTranslateX(220);
            restart.setTranslateY(300);
            restart.setOnAction(event -> {
                main.restartGame();
            });

            if (main.root != null) {
            main.root.getChildren().addAll(label, restart);
            }

        });
    }

    public void showWin(final Main main) {
        Platform.runLater(() -> {
            Label label = new Label("You Win :)");
            label.setTranslateX(200);
            label.setTranslateY(250);
            label.setScaleX(2);
            label.setScaleY(2);

            Button bonusLevel = new Button("Bonus Level");
            bonusLevel.setTranslateX(200);
            bonusLevel.setTranslateY(300);
            bonusLevel.setOnAction(event -> main.startGame());

            Button exit = new Button("Exit");
            exit.setTranslateX(220);
            exit.setTranslateY(350);
            exit.setOnAction(event -> main.exitGame());
            if (main.root != null) {
                main.root.getChildren().addAll(label, bonusLevel, exit);
            }
        });
    }

    public void showCongrat(final Main main) {
        Platform.runLater(() -> {
            Label label = new Label("Congratulations!");
            label.setTranslateX(200);
            label.setTranslateY(40);
            label.setScaleX(2);
            label.setScaleY(2);

            main.root.getChildren().clear();

            Button exit = new Button("Exit Game");
            exit.setTranslateX(220);
            exit.setTranslateY(600);
            exit.setOnAction(event -> {
                    main.exitGame();

            });

            if (main.root != null) {
                main.root.getChildren().addAll(label, exit);
            }

        });

    }
}