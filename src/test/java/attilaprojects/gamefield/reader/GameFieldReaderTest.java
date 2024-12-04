package attilaprojects.gamefield.reader;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GameFieldReaderTest {

    private GameField gameField;
    private GameFieldReader gameFieldReader;

    @BeforeEach
    public void setUp() {
        // Arrange: Set up the game field and reader for each test
        gameField = GameField.getInstance();
        gameFieldReader = new GameFieldReader(gameField);
    }

    @Test
    public void testLoadFieldFromFileSuccess(){
        // Arrange: Provide absolute path to file in src/test/resources
        String inputFileName = "src/test/resources/validField.txt";
        File file = new File(inputFileName);

        assertTrue(file.exists(), "Test file does not exist");

        // Act: Load the field from the file
        boolean result = gameFieldReader.loadFieldFromFile(inputFileName);

        // Assert: Ensure the field is loaded correctly
        assertTrue(result, "Field loading should succeed");
        assertNotNull(gameField.getField(), "Game field should not be null after loading");
    }



    @Test
    public void testLoadFieldFromFileFailure() {
        // Arrange: Use a non-existing file to test the failure scenario
        String inputFileName = "nonExistingField.txt";

        // Act: Try loading a non-existing file
        boolean result = gameFieldReader.loadFieldFromFile(inputFileName);

        // Assert: Ensure the field loading fails and the field is set to empty
        assertFalse(result, "Field loading should fail for a non-existing file");
        char[][] field = gameField.getField();
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                assertEquals('#', field[i][j], "Field should be empty after loading failure");
            }
        }
    }

    @Test
    void testSetEmptyField() {
        // Arrange: Create a real GameField instance
        GameField gameField = new GameField();

        // Create the game field reader using the actual game field
        GameFieldReader gameFieldReader = new GameFieldReader(gameField);

        // Act: Call setEmptyField to initialize the empty field
        gameFieldReader.setEmptyField();

        // Assert: Verify that the field was set to the expected empty state
        char[][] field = gameField.getField();

        // Loop through each row and column of the field to assert that all values are '#'
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                assertEquals('#', field[i][j], "Field should be empty and filled with '#' characters");
            }
        }
    }
}
