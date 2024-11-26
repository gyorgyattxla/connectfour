package attilaprojects.gamefield.reader;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.reader.GameFieldReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameFieldReaderTest {

    private GameField mockGameField;
    private GameFieldReader gameFieldReader;

    @BeforeEach
    void setUp() {
        mockGameField = mock(GameField.class); // Mocking the GameField dependency
        gameFieldReader = new GameFieldReader(mockGameField); // Injecting the mocked GameField
    }

    @Test
    void testLoadFieldFromFile_FileExists() throws Exception {
        // given
        String inputFileName = "test_field.txt";
        String fileContent = "#######\n#######\n#######\n#######\n#######\n#######"; // Simulated file content

        // Create a temporary file in the test resources directory
        File tempFile = new File(getClass().getClassLoader().getResource("").getPath(), inputFileName);
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(fileContent);
        }

        when(mockGameField.getRowCount()).thenReturn(6);
        when(mockGameField.getColCount()).thenReturn(7);

        GameFieldReader gameFieldReader = new GameFieldReader(mockGameField);

        // when
        boolean result = gameFieldReader.loadFieldFromFile(inputFileName);

        // then
        assertTrue(result, "Field should load successfully from file");
        verify(mockGameField, times(1)).setField(any(char[][].class));

        // cleanup
        tempFile.delete();
    }

    @Test
    void testLoadFieldFromFile_FileNotFound() {
        // given
        String inputFileName = "nonexistent_field.txt";
        ClassLoader classLoader = mock(ClassLoader.class);

        when(classLoader.getResourceAsStream(inputFileName)).thenReturn(null);

        // when
        boolean result = gameFieldReader.loadFieldFromFile(inputFileName);

        // then
        assertFalse(result, "Field loading should fail when file is not found");
        verify(mockGameField, times(1)).setField(any(char[][].class));
    }

    @Test
    void testLoadFieldFromFile_FileCorrupted() {
        // given
        String inputFileName = "corrupted_field.txt";
        String corruptedContent = "###\n#"; // Simulated corrupted content
        InputStream inputStream = new ByteArrayInputStream(corruptedContent.getBytes());
        ClassLoader classLoader = mock(ClassLoader.class);

        when(classLoader.getResourceAsStream(inputFileName)).thenReturn(inputStream);
        when(mockGameField.getRowCount()).thenReturn(6);
        when(mockGameField.getColCount()).thenReturn(7);

        // when
        boolean result = gameFieldReader.loadFieldFromFile(inputFileName);

        // then
        assertFalse(result, "Field loading should fail when file content is invalid");
        verify(mockGameField, times(1)).setField(any(char[][].class));
    }

    @Test
    void testSetEmptyField() {
        // given
        when(mockGameField.getRowCount()).thenReturn(6);
        when(mockGameField.getColCount()).thenReturn(7);

        // when
        gameFieldReader.setEmptyField();

        // then
        verify(mockGameField, times(1)).setField(any(char[][].class));
    }
}