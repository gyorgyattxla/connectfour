package attilaprojects.gamefield;

import java.util.Arrays;
import java.util.Objects;

public class GameField implements GameFieldInterface{
    private static GameField instance;

    /** Establishing the field's row count **/
    private final int rowCount = 6;
    /** Establishing the field's column count **/
    private final int colCount = 7;

    /** Establishing the playing field **/
    private final char[][] field;

    /** Constructor **/
    public GameField() {
        this.field = new char[rowCount][colCount];
    }

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }

    /** Made getField a deep-copy, so that the original array stays independent **/
    public char[][] getField() {
        return deepCopyFieldArray(this.field);
    }

    public void setField(char[][] field) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                this.field[i][j] = field[i][j];
            }
        }
    }

    public void setField(int column, char player){
        boolean placed = false;
        int i = rowCount-1;
        while(!placed){
            if(this.field[i][column] == '#'){
                this.field[i][column] = player;
                placed = true;
            }else{
                i--;
            }
        }
    }

    /** rowCount & colCount getter methods **/
    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    /** Equals method **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameField)) return false;
        GameField gameField = (GameField) o;
        return getRowCount() == gameField.getRowCount() && getColCount() == gameField.getColCount() && Arrays.deepEquals(getField(), gameField.getField());
    }

    /** hashCode method **/
    @Override
    public int hashCode() {
        int result = Objects.hash(getRowCount(), getColCount());
        result = 31 * result + Arrays.deepHashCode(getField());
        return result;
    }

    /** deepCopyFieldArray method copies the data from itself into result **/
    public char[][] deepCopyFieldArray(char[][] field) {
        char[][] result = new char[rowCount][colCount];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                result[i][j] = field[i][j];
            }
        }
        return result;
    }
}
