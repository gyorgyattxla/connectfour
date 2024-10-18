package attilaprojects.gamefield;

import java.io.InputStream;
import java.util.Scanner;

public class GameFieldReader implements GameFieldReaderInterface{
    private final GameField gameField;

    public GameFieldReader(GameField gameField){
        this.gameField = gameField;
    }

    public boolean loadFieldFromFile(String inputFileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(inputFileName);

        // Check if the file was found
        if (inputStream == null) {
            System.out.println("Error: File not found in classpath.");
            setEmptyField();
            return false;
        }

        try (Scanner scanner = new Scanner(inputStream)) {
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
            System.out.println("Error: Could not load field from file.");
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
