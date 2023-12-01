package Score;

import PlayGame.LevelObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class SaveHighScore {
    LevelObject levelobject = new LevelObject();
    public void saveHighScore(String name, int currentScore) {
        File directory = new File("C:\\high");
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

