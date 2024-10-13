package attilaprojects;

import java.util.Arrays;

public class GameField implements GameFieldInterface{
    /** The playing field **/
    private final int[][] field;
    /** Making a separate two-dimensional array to check if a space is filled **/
    private final boolean[][] fieldIsSet;

    public GameField(int[][] field, boolean[][] fieldIsSet) {
        this.field = field;
        this.fieldIsSet = fieldIsSet;
    }
    /** Made getField a deep-copy, so that the original array stays independent **/
    public int[][] getField() {
        return deepcopyFieldArray();
    }

    /** Made getFieldIsSet a deep-copy, so that the original array stays independent **/
    public boolean[][] getFieldIsSet() {
        return deepcopyFieldIsSetArray();
    }

    /** Equals method **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameField)) return false;
        GameField gameField = (GameField) o;
        return Arrays.deepEquals(field, gameField.field) && Arrays.deepEquals(fieldIsSet, gameField.fieldIsSet);
    }


    /** hashcode method **/
    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(field);
        result = 31 * result + Arrays.deepHashCode(fieldIsSet);
        return result;
    }

    /** deepcopyFieldArray method copies the data from itself into result **/
    @Override
    public int[][] deepcopyFieldArray() {
        int[][] result = new int[field.length][];
        for (int i = 0; i < field.length; i++) {
            result[i] = field[i];
            for (int j = 0; j < field[i].length; j++) {
                result[i][j] = field[i][j];
            }
        }
        return result;
    }

    @Override
    public boolean[][] deepcopyFieldIsSetArray() {
        boolean[][] result = new boolean[fieldIsSet.length][];
        for (int i = 0; i < fieldIsSet.length; i++) {
            result[i] = fieldIsSet[i];
            for (int j = 0; j < fieldIsSet[i].length; j++) {
                result[i][j] = fieldIsSet[i][j];
            }
        }
        return result;
    }
}
