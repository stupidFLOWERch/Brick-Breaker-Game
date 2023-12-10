package brickGame;

import Ball.SetPhysicsToBall;
import Block.Block;
import Block.Bonus;
import Block.Trap;
import Block.CheckDestroyedCount;
import Score.Score;
import Sound.Sound;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The {@code OnAction} class implements the {@link GameEngine.OnAction} interface
 * to define actions that occur during the game updates, physics calculations, and
 * time progression.
 * <p>
 * It manages UI updates, block collisions, physics interactions, and bonus/trap
 * behaviors.
 * </p>
 */
public class OnAction implements GameEngine.OnAction {
    private final Main main;

    /**
     * Constructs an {@code OnAction} instance with a reference to the main game object.
     *
     * @param main The main game object.
     */
    public OnAction(Main main) {
        this.main = main;
    }

    /**
     * Handles UI updates, block collisions, and specific block interactions during
     * each game update.
     */
    @Override
    public void onUpdate() {
        if (main.getBo().isBallMovementAllowed()) {
            Platform.runLater(() -> {
                main.getLevelobject().getScoreLabel().setText("Score: " + main.getLevelobject().getScore());
                main.getLevelobject().getHeartLabel().setText("Heart : " + main.getLevelobject().getHeart());

                main.getBreakobject().getRect().setX(main.getBreakobject().getxBreak());
                main.getBreakobject().getRect().setY(main.getBreakobject().getyBreak());
                main.getBo().getBall().setCenterX(main.getBo().getxBall());
                main.getBo().getBall().setCenterY(main.getBo().getyBall());
            });
            for (Bonus cheese : main.getBlockobject().getCheeses()) {
                cheese.cheese.setY(cheese.y);
            }
            for (Trap mousetrap : main.getBlockobject().getTraps()) {
                mousetrap.mousetrap.setY(mousetrap.y);
            }
            List<Block> blocksCopy = new ArrayList<Block>(main.getBlockobject().getBlocks());


            if (main.getBo().getyBall() >= Block.getPaddingTop() && main.getBo().getyBall() <= (Block.getHeight() * (main.getLevelobject().getLevel() + 1)) + Block.getPaddingTop()) {
                for (final Block block : blocksCopy) {
                    try {
                        int hitCode = block.checkHitToBlock(main.getBo().getxBall(), main.getBo().getyBall());
                        if (hitCode != Block.NO_HIT) {
                            main.getLevelobject().setScore(main.getLevelobject().getScore() + 1);

                            new Score().show(block.x, block.y, 1, main);

                            block.rect.setVisible(false);
                            block.isDestroyed = true;
                            main.getLevelobject().setDestroyedBlockCount(main.getLevelobject().getDestroyedBlockCount() + 1);
                            new Sound();
                            //System.out.println("size is " + blocks.size());
                            main.getResetcollideflags().resetCollideFlags(main.getBo());

                            if (block.type == Block.BLOCK_CHEESE) {
                                final Bonus cheese = new Bonus(block.row, block.column);
                                cheese.timeCreated = main.getBo().getTime();
                                Platform.runLater(() -> Platform.runLater(() -> main.getRoot().getChildren().add(cheese.cheese)));
                                main.getBlockobject().getCheeses().add(cheese);
                            }

                            if (block.type == Block.BLOCK_TRAP) {
                                final Trap mousetrap = new Trap(block.row, block.column);
                                mousetrap.timeCreated = main.getBo().getTime();
                                Platform.runLater(() -> Platform.runLater(() -> main.getRoot().getChildren().add(mousetrap.mousetrap)));
                                main.getBlockobject().getTraps().add(mousetrap);
                            }

                            if (block.type == Block.BLOCK_STAR) {
                                main.getBo().setGoldTime(main.getBo().getTime());
                                main.getBo().getBall().setFill(new ImagePattern(new Image("goldball.png")));
                                System.out.println("gold ball");
                                main.getRoot().getStyleClass().add("goldRoot");
                                main.getLevelobject().setGoldStatus(true);
                            }

                            if (block.type == Block.BLOCK_HEART) {
                                main.getLevelobject().setHeart(main.getLevelobject().getHeart() + 1);
                                main.getLevelobject().setGetHeart(true);
                            }

                            if (hitCode == Block.HIT_RIGHT) {
                                main.getBo().setCollideToRightBlock(true);
                            } else if (hitCode == Block.HIT_BOTTOM) {
                                main.getBo().setCollideToBottomBlock(true);
                            } else if (hitCode == Block.HIT_LEFT) {
                                main.getBo().setCollideToLeftBlock(true);
                            } else if (hitCode == Block.HIT_TOP) {
                                main.getBo().setCollideToTopBlock(true);
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
    }


    /**
     * Handles physics updates, bonus/trap interactions, and goldball effects
     * during each physics update.
     */
    @Override
    public void onPhysicsUpdate() {
        if (main.getBo().isBallMovementAllowed()) {
            SetPhysicsToBall setphysicstoball = new SetPhysicsToBall();
            CheckDestroyedCount checkdestroyedcount = new CheckDestroyedCount();
            long currentTime = System.currentTimeMillis();
            double elapsedTime = (currentTime - main.getLastUpdateTime()) / 1000.0;  // Convert to seconds
            main.setLastUpdateTime(currentTime);

            checkdestroyedcount.checkDestroyedCount(main, main.getEngine(), main.getBo(), main.getBlockobject(), main.getLevelobject());
            setphysicstoball.setPhysicsToBall(main.getPrimaryStage(), main, main.getEngine(), main.getBo(), main.getBreakobject(), main.getBlockobject(), main.getLevelobject());

            if (main.getBo().getTime() - main.getBo().getGoldTime() > 5000) {
                Platform.runLater(() -> {
                    main.getBo().getBall().setFill(new ImagePattern(new Image("ball.png")));
                    main.getRoot().getStyleClass().remove("goldRoot");
                });
                main.getLevelobject().setGoldStatus(false);
            }

            List<Bonus> cheesesToRemove = new ArrayList<Bonus>();
            Iterator<Bonus> cheeseIterator = main.getBlockobject().getCheeses().iterator();
            while (cheeseIterator.hasNext()) {
                Bonus cheese = cheeseIterator.next();

                if (cheese.y > main.getLevelobject().getSceneHeight() || cheese.taken) {
                    cheesesToRemove.add(cheese);
                    continue;
                }
                if (cheese.y >= main.getBreakobject().getyBreak() && cheese.y <= main.getBreakobject().getyBreak() + main.getBreakobject().getBreakHeight() && cheese.x >= main.getBreakobject().getxBreak() && cheese.x <= main.getBreakobject().getxBreak() + main.getBreakobject().getBreakWidth()) {
                    System.out.println("You Got the cheese! +3 score for you");
                    cheese.taken = true;
                    cheese.cheese.setVisible(false);
                    main.getLevelobject().setScore(main.getLevelobject().getScore() + 3);
                    Platform.runLater(() -> new Score().show(cheese.x, cheese.y, 3, main));
                }
                cheese.y += elapsedTime * ((main.getBo().getTime() - cheese.timeCreated) / 1000.0) + 1.0;
            }
            main.getBlockobject().getCheeses().removeAll(cheesesToRemove);


            List<Trap> trapsToRemove = new ArrayList<Trap>();
            Iterator<Trap> trapIterator = main.getBlockobject().getTraps().iterator();
            while (trapIterator.hasNext()) {
                Trap mousetrap = trapIterator.next();

                if (mousetrap.y > main.getLevelobject().getSceneHeight() || mousetrap.taken) {
                    trapsToRemove.add(mousetrap);
                    continue;
                }
                if (mousetrap.y >= main.getBreakobject().getyBreak() && mousetrap.y <= main.getBreakobject().getyBreak() + main.getBreakobject().getBreakHeight() && mousetrap.x >= main.getBreakobject().getxBreak() && mousetrap.x <= main.getBreakobject().getxBreak() + main.getBreakobject().getBreakWidth()) {
                    System.out.println("You Got the trap! -3 score ");
                    mousetrap.taken = true;
                    mousetrap.mousetrap.setVisible(false);
                    main.getLevelobject().setScore(main.getLevelobject().getScore() - 3);
                    Platform.runLater(() -> new Score().show(mousetrap.x, mousetrap.y, -3, main));
                }
                mousetrap.y += elapsedTime * ((main.getBo().getTime() - mousetrap.timeCreated) / 1000.0) + 1.0;
            }
            main.getBlockobject().getTraps().removeAll(trapsToRemove);

        }
    }

    /**
     * Handles the progression of game time.
     *
     * @param time The current game time.
     */
    @Override
    public void onTime(long time) {
        main.getBo().setTime(time);
    }
}