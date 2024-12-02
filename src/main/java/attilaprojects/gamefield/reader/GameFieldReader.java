package attilaprojects.gamefield.reader;

import attilaprojects.gamefield.GameField;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameFieldReader implements GameFieldReaderInterface {
    private final GameField gameField;

    public GameFieldReader(final GameField gameField) {
        this.gameField = gameField;
    }

    final public boolean loadFieldFromFile(String inputFileName) {

        String filePath = new File(System.getProperty("user.dir"), inputFileName).toString();

        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
             Scanner scanner = new Scanner(inputStream)) {
            char[][] loadedField = new char[gameField.getRowCount()][gameField.getColCount()];
            for (int i = 0; i < gameField.getRowCount(); i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < gameField.getColCount(); j++) {
                    loadedField[i][j] = line.charAt(j);
                }
            }
            gameField.setField(loadedField);
            return true;
        } catch (Exception e) {
            System.err.println("Error: Could not load field from file.");
            setEmptyField();
            return false;
        }
    }
    @Override
    public void setEmptyField() {
        char[][] emptyField = new char[gameField.getRowCount()][gameField.getColCount()];
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                emptyField[i][j] = '#';
            }
        }
        gameField.setField(emptyField);
    }
}
