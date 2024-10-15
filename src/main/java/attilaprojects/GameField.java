package attilaprojects;

import java.util.Arrays;

public class GameField implements GameFieldInterface{
    /** Establishing the field's row count **/
    private final int rowCount = 7;
    /** Establishing the field's column count **/
    private final int colCount = 6;

    /** Establishing the playing field **/
    private final char[][] field;

    /** Constructor
     * -- makes the field empty on default,
     * making it easier to get the fieldReader and fieldWriter working more efficiently later **/
    public GameField(char[][] field) {
        this.field = new char[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                this.field[i][j] = '#';
            }
        }
    }

    /** Made getField a deep-copy, so that the original array stays independent **/
    public char[][] getField() {
        return deepcopyFieldArray(this.field);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameField)) return false;
        GameField gameField = (GameField) o;
        return Arrays.deepEquals(getField(), gameField.getField());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getField());
    }

    /** deepcopyFieldArray method copies the data from itself into result **/
    public char[][] deepcopyFieldArray(char[][] field) {
        char[][] result = new char[rowCount][colCount];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                result[i][j] = field[i][j];
            }
        }
        return result;
    }
}
