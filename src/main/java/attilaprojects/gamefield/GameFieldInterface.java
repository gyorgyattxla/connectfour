package attilaprojects.gamefield;

public interface GameFieldInterface {
    /**
     * Creates a deep copy of the provided game field array.
     *
     * @param field the 2D array representing the current game field that needs
     *   to be copied
     * @return a new 2D array that is a deep copy of the provided field
     */
    char[][] deepCopyFieldArray(char[][] field);
}
