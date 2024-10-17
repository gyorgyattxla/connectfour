package attilaprojects.gamefield;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameFieldReader implements GameFieldReaderInterface{
    private final GameField gameField;

    public GameFieldReader(GameField gameField){
        this.gameField = gameField;
    }

    public boolean loadFieldFromFile(String inputFileName){
        File file = new File(inputFileName);
        char[][] loadedField = new char[gameField.getRowCount()][gameField.getColCount()];
        try (Scanner scanner = new Scanner(file)){
            for (int i = 0; i < gameField.getRowCount(); i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < gameField.getColCount(); j++) {
                    loadedField[i][j] = line.charAt(j);
                }
            }
            gameField.setField(loadedField);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
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
