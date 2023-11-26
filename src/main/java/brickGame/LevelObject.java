package brickGame;

public class LevelObject {
    private int level;
    private int heart;
    private int score;
    private boolean isGoldStatus;
    private boolean isExistHeartBlock;
    private int destroyedBlockCount;
    private boolean getHeart;

    private boolean fromRestartGame;

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
}
