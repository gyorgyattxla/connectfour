package attilaprojects.gamestate;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameStateDisplayerTest {

    private GameField mockGameField;
    private GameStateDisplayer gameStateDisplayer;

    @BeforeEach
    void setUp() {
        mockGameField = mock(GameField.class);
        gameStateDisplayer = new GameStateDisplayer(mockGameField);
    }

    @Test
    void testDisplayGameState() {
        // given
        String playerName = "Player1";
        char[][] field = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        char[] expectedColumns = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        when(mockGameField.getField()).thenReturn(field);
        when(mockGameField.getRowCount()).thenReturn(6);
        when(mockGameField.getColCount()).thenReturn(7);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Capture System.out

        // when
        gameStateDisplayer.displayGameState(playerName);

        // then
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append(playerName).append(" vs. Computer\n");
        for (char[] row : field) {
            for (char cell : row) {
                expectedOutput.append(cell).append(" ");
            }
            expectedOutput.append("\n");
        }
        for (char column : expectedColumns) {
            expectedOutput.append(column).append(" ");
        }
        expectedOutput.append("\n");

        // Normalize outputs
        String actualOutput = outputStream.toString().trim().replaceAll("\\r\\n", "\n").replaceAll("\\s+$", "");
        String normalizedExpectedOutput = expectedOutput.toString().trim().replaceAll("\\r\\n", "\n").replaceAll("\\s+$", "");

        assertEquals(normalizedExpectedOutput, actualOutput, "Output should match the expected game state display.");

        // Reset System.out
        System.setOut(System.out);
    }
}