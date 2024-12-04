package attilaprojects.gamefield.saver;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameFieldSaverTest {

    private GameField gameField;
    private GameFieldSaver gameFieldSaver;

    @BeforeEach
    public void setUp() {
        // Arrange: Create a mock GameField
        gameField = mock(GameField.class);
        gameFieldSaver = new GameFieldSaver(gameField);
    }

    @Test
    public void testSaveFieldToFileFailure(){
        // Arrange: Mock the data that will be returned by the game field
        char[][] mockField = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mockField[i][j] = '#';  // Mock the field with empty values
            }
        }

        when(gameField.getField()).thenReturn(mockField);

        // Use an invalid file path to simulate failure
        String invalidFilePath = "invalid/path/to/file.txt";

        // Act: Call the saveFieldToFile method
        boolean result = gameFieldSaver.saveFieldToFile(invalidFilePath);

        // Assert: Ensure the method returns false when the path is invalid
        assertFalse(result, "The field saving should fail due to invalid path.");
    }
}
