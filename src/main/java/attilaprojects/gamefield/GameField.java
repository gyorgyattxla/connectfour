package attilaprojects.gamefield;

import java.util.Arrays;
import java.util.Objects;

/**
 * Contains classes related to the game field, including
 * the representation of the field,
 * manipulation of the field, and accessing its properties.
 */
public class GameField implements GameFieldInterface {
    private static GameField instance;

    /** Establishing the field's row count. */
    private final int rowCount = 6;

    /** Establishing the field's column count. */
    private final int colCount = 7;

    /** Establishing the playing field. */
    private final char[][] field;

    /**
     * Constructor to initialize the game field with specified dimensions.
     */
    public GameField() {
        this.field = new char[rowCount][colCount];
    }

    /**
     * Returns the singleton instance of the GameField.
     *
     * @return the single instance of the GameField.
     */
    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }

    /**
     * Returns a deep copy of the game field, ensuring the original array
     * remains independent.
     * @return a deep copy of the field.
     */
    public char[][] getField() {
        return deepCopyFieldArray(this.field);
    }

    /**
     * Sets the game field to the specified 2D array.
     *
     * @param newField the 2D char array representing the game field.
     */
    public void setField(final char[][] newField) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                this.field[i][j] = newField[i][j];
            }
        }
    }

    /**
     * Places a player's piece in the specified column on the game field.
     *
     * @param column the column in which to place the player's piece.
     * @param player the character representing the player (e.g., 'P').
     */
    public void setField(final int column, final char player) {
        boolean placed = false;
        int i = rowCount - 1;
        while (!placed) {
            if (this.field[i][column] == '#') {
                this.field[i][column] = player;
                placed = true;
            } else {
                i--;
            }
        }
    }

    /**
     * Gets the number of rows in the game field.
     *
     * @return the number of rows in the field.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Gets the number of columns in the game field.
     *
     * @return the number of columns in the field.
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * Compares the current game field with another object.
     * Two game fields are considered equal if they have
     * the same dimensions and field data.
     * @param o the object to compare this game field with.
     * @return true if the fields are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameField)) {
            return false;
        }
        GameField gameField = (GameField) o;
        return getRowCount() == gameField.getRowCount()
                && getColCount() == gameField.getColCount()
                && Arrays.deepEquals(getField(), gameField.getField());
    }

    /**
     * Generates a hash code for the game field based
     * on its dimensions and field data.
     ** @return the hash code for the game field.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getRowCount(), getColCount());
        result = 31 * result + Arrays.deepHashCode(getField());
        return result;
    }

    /**
     * Creates a deep copy of the given 2D char array.
     *
     * @param field the 2D char array to be copied.
     * @return a new 2D char array with the same content.
     */
    public char[][] deepCopyFieldArray(final char[][] field) {
        char[][] result = new char[rowCount][colCount];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                result[i][j] = field[i][j];
            }
        }
        return result;
    }
}
