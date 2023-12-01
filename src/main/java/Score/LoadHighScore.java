package Score;

import PlayGame.LevelObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoadHighScore {

    LevelObject levelobject = new LevelObject();
    public int getHighScore() {
        int highestScore = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(levelobject.getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int score = parseScore(line);
                if (score > highestScore) {
                    highestScore = score;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return highestScore;
    }

    private int parseScore(String line) {
        String[] parts = line.split(" - ");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0; // Default to 0 if the line is not properly formatted or in case of an error
    }
    }


