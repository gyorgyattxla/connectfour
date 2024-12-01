package attilaprojects.gamefield.saver;

import attilaprojects.gamefield.GameField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class GameFieldSaver implements GameFieldSaverInterface {
    private final GameField gameField;

    public GameFieldSaver(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public boolean saveFieldToFile(GameField gameField, String filename) {
        try {
            // Get the path to the resources directory
            String resourcePath = Paths.get("src", "main", "resources", filename).toString();
            File file = new File(resourcePath);

            // Write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int row = 0; row < gameField.getField().length; row++) {
                    for (int col = 0; col < gameField.getField()[row].length; col++) {
                        writer.write(gameField.getField()[row][col]);
                    }
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving field: " + e.getMessage());
            return false;
        }
    }
}