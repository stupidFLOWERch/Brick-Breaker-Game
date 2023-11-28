package Level;

import javafx.scene.control.Label;

public class LevelObject {
    private int level;
    private int heart;
    private int score;
    private boolean isGoldStatus;
    private boolean isExistHeartBlock;
    private int destroyedBlockCount;
    private boolean getHeart;
    private boolean fromRestartGame;
    private Label scoreLabel;
    private Label heartLabel;
    private Label levelLabel;
    private boolean loadFromSave = false;
    private int restartFromLevel = 1;
    private int restartFromHeart = 3;
    private int restartFromScore = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGoldStatus() {
        return isGoldStatus;
    }

    public void setGoldStatus(boolean goldStatus) {
        isGoldStatus = goldStatus;
    }

    public boolean isExistHeartBlock() {
        return isExistHeartBlock;
    }

    public void setExistHeartBlock(boolean existHeartBlock) {
        isExistHeartBlock = existHeartBlock;
    }

    public int getDestroyedBlockCount() {
        return destroyedBlockCount;
    }

    public void setDestroyedBlockCount(int destroyedBlockCount) {
        this.destroyedBlockCount = destroyedBlockCount;
    }

    public boolean isGetHeart() {
        return getHeart;
    }

    public void setGetHeart(boolean getHeart) {
        this.getHeart = getHeart;
    }

    public boolean isFromRestartGame() {
        return !fromRestartGame;
    }

    public void setFromRestartGame(boolean fromRestartGame) {
        this.fromRestartGame = fromRestartGame;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public Label getHeartLabel() {
        return heartLabel;
    }

    public void setHeartLabel(Label heartLabel) {
        this.heartLabel = heartLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(Label levelLabel) {
        this.levelLabel = levelLabel;
    }

    public boolean isLoadFromSave() {
        return loadFromSave;
    }

    public void setLoadFromSave(boolean loadFromSave) {
        this.loadFromSave = loadFromSave;
    }

    public int getRestartFromLevel() {
        return restartFromLevel;
    }

    public void setRestartFromLevel(int restartFromLevel) {
        this.restartFromLevel = restartFromLevel;
    }

    public int getRestartFromHeart() {
        return restartFromHeart;
    }

    public void setRestartFromHeart(int restartFromHeart) {
        this.restartFromHeart = restartFromHeart;
    }

    public int getRestartFromScore() {
        return restartFromScore;
    }

    public void setRestartFromScore(int restartFromScore) {
        this.restartFromScore = restartFromScore;
    }
}