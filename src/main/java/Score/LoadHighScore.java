package Score;

import PlayGame.LevelObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * The LoadHighScore class is responsible for loading the highest score and associated player name
 * from a file containing high scores.
 */
public class LoadHighScore {

    static LevelObject levelobject = new LevelObject();

    /**
     * Retrieves the highest score from the high scores file.
     *
     * @return The highest score.
     */
    public static int getHighScore() {
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

    /**
     * Retrieves the name associated with the highest score from the high scores file.
     *
     * @return The player name with the highest score.
     */
    public static String getName() {
        int highestScore = 0;
        String playername = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(levelobject.getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int score = parseScore(line);
                String name = parseName(line);
                if (score > highestScore) {
                    highestScore = score;
                    playername = name;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playername;
    }

    /**
     * Retrieves the name associated with the highest score from the high scores file.
     *
     * @return The player name with the highest score.
     */
    private static int parseScore(String line) {
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

    /**
     * Parses the player name from a line in the high scores file.
     *
     * @param line The line containing player name information.
     * @return The parsed player name.
     */
    private static String parseName(String line) {
        String[] parts = line.split(" - ");
        if (parts.length >= 1) {
            return parts[0].trim();
        }

        return line;
    }
}


