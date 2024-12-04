package attilaprojects.gamestephandler.winhandler.computer;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CheckComputerWinTest {

    private GameField gameField;
    private CheckComputerWin checkComputerWin;

    @BeforeEach
    public void setUp() {
        gameField = mock(GameField.class);
        checkComputerWin = new CheckComputerWin(gameField);
    }

    @Test
    public void testCheckComputerWinStateHorizontalWin() {
        // Setup: Mock a game field with a horizontal win for the computer
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'P' in a horizontal line
        field[2][0] = 'P';
        field[2][1] = 'P';
        field[2][2] = 'P';
        field[2][3] = 'P';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the computer has won
        boolean result = checkComputerWin.checkComputerWinState();

        // Assert: The method should return true as there's a horizontal win
        assertTrue(result, "The computer should win horizontally.");
    }

    @Test
    public void testCheckComputerWinStateVerticalWin() {
        // Setup: Mock a game field with a vertical win for the computer
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'P' in a vertical line
        field[0][2] = 'P';
        field[1][2] = 'P';
        field[2][2] = 'P';
        field[3][2] = 'P';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the computer has won
        boolean result = checkComputerWin.checkComputerWinState();

        // Assert: The method should return true as there's a vertical win
        assertTrue(result, "The computer should win vertically.");
    }

    @Test
    public void testCheckComputerWinStateFirstDiagonalWin() {
        // Setup: Mock a game field with a diagonal win for the computer
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'P' in a diagonal line (first diagonal)
        field[2][0] = 'P';
        field[3][1] = 'P';
        field[4][2] = 'P';
        field[5][3] = 'P';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the computer has won
        boolean result = checkComputerWin.checkComputerWinState();

        // Assert: The method should return true as there's a diagonal win
        assertTrue(result, "The computer should win in the first diagonal.");
    }

    @Test
    public void testCheckComputerWinStateSecondDiagonalWin() {
        // Setup: Mock a game field with a diagonal win for the computer (second diagonal)
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'P' in a diagonal line (second diagonal)
        field[5][3] = 'P';
        field[4][4] = 'P';
        field[3][5] = 'P';
        field[2][6] = 'P';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the computer has won
        boolean result = checkComputerWin.checkComputerWinState();

        // Assert: The method should return true as there's a diagonal win
        assertTrue(result, "The computer should win in the second diagonal.");
    }

    @Test
    public void testCheckComputerWinStateNoWin() {
        // Setup: Mock a game field with no winning condition
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the computer has won
        boolean result = checkComputerWin.checkComputerWinState();

        // Assert: The method should return false as there is no win
        assertFalse(result, "The computer should not have won.");
    }
}
