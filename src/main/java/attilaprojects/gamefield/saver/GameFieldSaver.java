package attilaprojects.gamefield.saver;

import attilaprojects.gamefield.GameField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameFieldSaver implements GameFieldSaverInterface{
    private final GameField gameField;

    public GameFieldSaver(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public boolean saveFieldToFile(GameField gameField, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < gameField.getField().length; row++) {
                for (int col = 0; col < gameField.getField()[row].length; col++) {
                    writer.write(gameField.getField()[row][col]);
                }
                writer.newLine();
            }
            System.out.println("Field saved to " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving field.");
            return false;
        }
    }
}
