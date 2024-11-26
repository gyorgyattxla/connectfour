package attilaprojects.gamefield.saver;


import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.saver.GameFieldSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GameFieldSaverTest {

    private GameField mockGameField;
    private GameFieldSaver gameFieldSaver;

    @BeforeEach
    void setUp() {
        mockGameField = mock(GameField.class);
        gameFieldSaver = new GameFieldSaver(mockGameField);
    }

    @Test
    void testSaveFieldToFile_Success() throws IOException {
        // given
        String filename = "test_field_save.txt";
        char[][] field = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        when(mockGameField.getField()).thenReturn(field);

        // when
        boolean result = gameFieldSaver.saveFieldToFile(mockGameField, filename);

        // then
        assertTrue(result, "Field should save successfully to the file.");

        // Verify that the file was created and contains the expected content
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (char[] row : field) {
                String line = reader.readLine();
                String expectedLine = new String(row);
                assertTrue(expectedLine.equals(line), "Line content should match the field.");
            }
        }

        // Clean up the test file
        new File(filename).delete();
    }

    @Test
    void testSaveFieldToFile_Failure() throws IOException {
        // given
        String invalidFilename = "/invalid_path/test_field_save.txt"; // Invalid path
        char[][] field = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        when(mockGameField.getField()).thenReturn(field);

        // when
        boolean result = gameFieldSaver.saveFieldToFile(mockGameField, invalidFilename);

        // then
        assertFalse(result, "Field saving should fail for an invalid file path.");
    }
}