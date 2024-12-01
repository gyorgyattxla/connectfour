package attilaprojects.player.score.saver;

import attilaprojects.player.score.PlayerScore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PlayerScoreSaver {
    private final String filename;

    public PlayerScoreSaver(String filename) {
        this.filename = Paths.get("src", "main", "resources", filename).toString(); // Target resources folder
    }

    public boolean savePlayerScore(String playerName) {
        Map<String, PlayerScore> playerScores = loadExistingScores();

        // Check if player exists and compare scores
        if (playerScores.containsKey(playerName)) {
            PlayerScore existingScore = playerScores.get(playerName);
                existingScore.incrementWinCount();
        } else {
            // Add new player
            playerScores.put(playerName, new PlayerScore(playerName, 1));
            System.out.println("New player " + playerName + " added.");
        }

        // Write the updated data back to the file
        return writeScoresToFile(playerScores);
    }

    private Map<String, PlayerScore> loadExistingScores() {
        Map<String, PlayerScore> playerScores = new HashMap<>();
        File file = new File(filename);

        if (!file.exists()) {
            return playerScores;  // Return empty if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int winAmount = Integer.parseInt(parts[1]);
                    playerScores.put(playerName, new PlayerScore(playerName, winAmount));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading scores: " + e.getMessage());
        }
        return playerScores;
    }

    private boolean writeScoresToFile(Map<String, PlayerScore> playerScores) {
        try {
            // Ensure parent directory exists
            Path resourcePath = Paths.get("src", "main", "resources");
            Files.createDirectories(resourcePath);

            // Write to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (PlayerScore score : playerScores.values()) {
                    writer.write(score.getPlayerName() + "," + score.getWinAmount());
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
            return false;
        }
    }
}
