package attilaprojects.player.score.loader;

import attilaprojects.player.score.PlayerScore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PlayerScoreLoader {
    public List<PlayerScore> loadScoresFromFile(String filename) {
        List<PlayerScore> playerScores = new ArrayList<>();

        // Load from classpath resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            System.err.println("Error: File not found in classpath.");
            return playerScores; // Return empty list if file is not found
        }

        // Read and parse the file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(","); // No spaces handling since removed

                // Validate the line format
                if (parts.length != 2) {  // Expecting only playerName and winAmount
                    System.err.println("Invalid line format: " + line);
                    continue; // Skip malformed lines
                }

                try {
                    String playerName = parts[0];
                    int winAmount = Integer.parseInt(parts[1]);
                    // Add to the list
                    playerScores.add(new PlayerScore(playerName, winAmount));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numbers in line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return playerScores;
    }
}
