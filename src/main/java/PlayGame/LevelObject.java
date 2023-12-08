package PlayGame;

import javafx.scene.control.Label;

/**
 * The LevelObject class represents the state and properties of the game level.
 * It includes information such as the current level, heart count, score, and game status.
 */
public class LevelObject {
    private int level;
    private int heart;
    private int score;
    private boolean isGoldStatus;
    private boolean isExistHeartBlock;
    private int destroyedBlockCount;
    private boolean getHeart;
    private boolean fromRestartGame = false;
    private Label scoreLabel;
    private Label heartLabel;
    private Label levelLabel;
    private boolean loadFromSave = false;
    private int restartFromLevel = 1;
    private int restartFromHeart = 3;
    private int restartFromScore = 0;
    private int HighScore = 0;
    private int CurrentScore = 0;

    /**
     * Gets the current score of the player.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score of the player.
     *
     * @param score The new score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the remaining heart count of the player.
     *
     * @return The remaining heart count.
     */
    public int getHeart() {
        return heart;
    }

    /**
     * Sets the remaining heart count of the player.
     *
     * @param heart The new heart count to set.
     */
    public void setHeart(int heart) {
        this.heart = heart;
    }

    /**
     * Gets the current level of the game.
     *
     * @return The current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level of the game.
     *
     * @param level The new level to set.
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Checks whether the player has achieved gold status.
     *
     * @return True if gold status is achieved, false otherwise.
     */
    public boolean isGoldStatus() {
        return isGoldStatus;
    }

    /**
     * Sets whether the player has achieved gold status.
     *
     * @param goldStatus The new gold status.
     */
    public void setGoldStatus(boolean goldStatus) {
        isGoldStatus = goldStatus;
    }


    /**
     * Checks whether there is a block containing a heart in the game.
     *
     * @return True if a heart block exists, false otherwise.
     */
    public boolean isExistHeartBlock() {
        return isExistHeartBlock;
    }

    /**
     * Sets whether there is a block containing a heart in the game.
     *
     * @param existHeartBlock The new value indicating the existence of a heart block.
     */
    public void setExistHeartBlock(boolean existHeartBlock) {
        isExistHeartBlock = existHeartBlock;
    }

    /**
     * Gets the count of destroyed blocks in the game.
     *
     * @return The count of destroyed blocks.
     */
    public int getDestroyedBlockCount() {
        return destroyedBlockCount;
    }

    /**
     * Sets the count of destroyed blocks in the game.
     *
     * @param destroyedBlockCount The new count of destroyed blocks.
     */
    public void setDestroyedBlockCount(int destroyedBlockCount) {
        this.destroyedBlockCount = destroyedBlockCount;
    }

    /**
     * Checks whether the player has obtained a heart in the current level.
     *
     * @return True if the player obtained a heart, false otherwise.
     */
    public boolean isGetHeart() {
        return getHeart;
    }

    /**
     * Sets whether the player has obtained a heart in the current level.
     *
     * @param getHeart The new value indicating whether the player obtained a heart.
     */
    public void setGetHeart(boolean getHeart) {
        this.getHeart = getHeart;
    }


    /**
     * Indicates whether the game is from a restart.
     *
     * @return True if the game is from a restart, false otherwise.
     */
    public boolean isFromRestartGame() {
        return !fromRestartGame;
    }

    /**
     * Sets whether the game is from a restart.
     *
     * @param fromRestartGame True to indicate the game is from a restart, false otherwise.
     */
    public void setFromRestartGame(boolean fromRestartGame) {
        this.fromRestartGame = fromRestartGame;
    }

    /**
     * Gets the label for displaying the score in the UI.
     *
     * @return The score label.
     */
    public Label getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Sets the label for displaying the score in the UI.
     *
     * @param scoreLabel The score label to set.
     */
    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    /**
     * Gets the label for displaying the heart count in the UI.
     *
     * @return The heart count label.
     */
    public Label getHeartLabel() {
        return heartLabel;
    }

    /**
     * Sets the label for displaying the heart count in the UI.
     *
     * @param heartLabel The heart count label to set.
     */
    public void setHeartLabel(Label heartLabel) {
        this.heartLabel = heartLabel;
    }

    /**
     * Gets the label for displaying the current level in the UI.
     *
     * @return The level label.
     */
    public Label getLevelLabel() {
        return levelLabel;
    }

    /**
     * Sets the label for displaying the current level in the UI.
     *
     * @param levelLabel The level label to set.
     */
    public void setLevelLabel(Label levelLabel) {
        this.levelLabel = levelLabel;
    }

    /**
     * Checks whether the game is loaded from a save file.
     *
     * @return True if loaded from a save, false otherwise.
     */
    public boolean isLoadFromSave() {
        return loadFromSave;
    }

    /**
     * Sets whether the game is loaded from a save file.
     *
     * @param loadFromSave The new value indicating whether the game is loaded from a save.
     */
    public void setLoadFromSave(boolean loadFromSave) {
        this.loadFromSave = loadFromSave;
    }

    /**
     * Gets the level to restart from in case of a game restart.
     *
     * @return The level to restart from.
     */
    public int getRestartFromLevel() {
        return restartFromLevel;
    }

    /**
     * Sets the level to restart from in case of a game restart.
     *
     * @param restartFromLevel The new level to restart from.
     */
    public void setRestartFromLevel(int restartFromLevel) {
        this.restartFromLevel = restartFromLevel;
    }

    /**
     * Gets the heart count to restart from in case of a game restart.
     *
     * @return The heart count to restart from.
     */
    public int getRestartFromHeart() {
        return restartFromHeart;
    }

    /**
     * Sets the heart count to restart from in case of a game restart.
     *
     * @param restartFromHeart The new heart count to restart from.
     */
    public void setRestartFromHeart(int restartFromHeart) {
        this.restartFromHeart = restartFromHeart;
    }

    /**
     * Gets the score to restart from in case of a game restart.
     *
     * @return The score to restart from.
     */
    public int getRestartFromScore() {
        return restartFromScore;
    }


    /**
     * Sets the score to restart from in case of a game restart.
     *
     * @param restartFromScore The new score to restart from.
     */
    public void setRestartFromScore(int restartFromScore) {
        this.restartFromScore = restartFromScore;
    }

    /**
     * Gets the scene width.
     *
     * @return game scene width.
     */
    public int getSceneWidth() {
        return 500;
    }

    /**
     * Gets the scene height.
     *
     * @return game scene height.
     */
    public int getSceneHeight() {
        return 700;
    }

    /**
     * Gets the highest score.
     *
     * @return The highest score.
     */
    public int getHighScore() {
        return HighScore;
    }

    /**
     * Sets the high score achieved in the game.
     *
     * @param highScore The high score achieved.
     */
    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    /**
     * Gets the current score achieved in the game.
     *
     * @return The current score achieved.
     */
    public int getCurrentScore() {
        return CurrentScore;
    }

    /**
     * Sets the current score achieved in the game.
     *
     * @param currentScore The current score achieved.
     */
    public void setCurrentScore(int currentScore) {
        CurrentScore = currentScore;
    }

    /**
     * Gets the file path for storing high scores.
     *
     * @return The file path for storing high scores.
     */
    public String getFilePath() {
        return "C:/tch_BrickGame_HighScore_File/Your_saved_HighScore_Is_Here.txt";
    }
}
