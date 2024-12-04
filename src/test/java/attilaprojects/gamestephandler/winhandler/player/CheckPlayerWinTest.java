package attilaprojects.gamestephandler.winhandler.player;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CheckPlayerWinTest {

    private GameField gameField;
    private CheckPlayerWin checkPlayerWin;

    @BeforeEach
    public void setUp() {
        gameField = mock(GameField.class);
        checkPlayerWin = new CheckPlayerWin(gameField);
    }

    @Test
    public void testCheckPlayerWinStateHorizontalWin() {
        // Setup: Mock a game field with a horizontal win for the player
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'S' in a horizontal line
        field[2][0] = 'S';
        field[2][1] = 'S';
        field[2][2] = 'S';
        field[2][3] = 'S';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the player has won
        boolean result = checkPlayerWin.checkPlayerWinState();

        // Assert: The method should return true as there's a horizontal win
        assertTrue(result, "The player should win horizontally.");
    }

    @Test
    public void testCheckPlayerWinStateVerticalWin() {
        // Setup: Mock a game field with a vertical win for the player
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'S' in a vertical line
        field[0][2] = 'S';
        field[1][2] = 'S';
        field[2][2] = 'S';
        field[3][2] = 'S';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the player has won
        boolean result = checkPlayerWin.checkPlayerWinState();

        // Assert: The method should return true as there's a vertical win
        assertTrue(result, "The player should win vertically.");
    }

    @Test
    public void testCheckPlayerWinStateFirstDiagonalWin() {
        // Setup: Mock a game field with a diagonal win for the player
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'S' in a diagonal line (first diagonal)
        field[2][0] = 'S';
        field[3][1] = 'S';
        field[4][2] = 'S';
        field[5][3] = 'S';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the player has won
        boolean result = checkPlayerWin.checkPlayerWinState();

        // Assert: The method should return true as there's a diagonal win
        assertTrue(result, "The player should win in the first diagonal.");
    }

    @Test
    public void testCheckPlayerWinStateSecondDiagonalWin() {
        // Setup: Mock a game field with a diagonal win for the player (second diagonal)
        char[][] field = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = '#';  // Fill with empty spaces
            }
        }
        // Place 'S' in a diagonal line (second diagonal)
        field[5][3] = 'S';
        field[4][4] = 'S';
        field[3][5] = 'S';
        field[2][6] = 'S';

        when(gameField.getField()).thenReturn(field);
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Act: Check if the player has won
        boolean result = checkPlayerWin.checkPlayerWinState();

        // Assert: The method should return true as there's a diagonal win
        assertTrue(result, "The player should win in the second diagonal.");
    }

    @Test
    public void testCheckPlayerWinStateNoWin() {
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

        // Act: Check if the player has won
        boolean result = checkPlayerWin.checkPlayerWinState();

        // Assert: The method should return false as there is no win
        assertFalse(result, "The player should not have won.");
    }
}
