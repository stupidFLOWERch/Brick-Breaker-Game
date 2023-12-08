package Score;

import PlayGame.LevelObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * The SaveHighScore class is responsible for saving player names and scores to a high scores file.
 */
public class SaveHighScore {
    LevelObject levelobject = new LevelObject();

    /**
     * Saves the player name and score to the high scores file.
     *
     * @param name         The player name.
     * @param currentScore The player's current score.
     */
    public void saveHighScore(String name, int currentScore) {
        File directory = new File("C:\\tch_BrickGame_HighScore_File");
        if (!directory.exists()) {
            directory.mkdirs(); // This will create the directory if it doesn't exist
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(levelobject.getFilePath(), true))) {
            String scoreEntry = name + " - " + currentScore + "\n";
            writer.write(scoreEntry);
        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    }

