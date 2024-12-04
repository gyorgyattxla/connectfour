package attilaprojects.gamestephandler.applier;

import attilaprojects.gamefield.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoveApplierTest {

    private GameField gameField;
    private MoveApplier moveApplier;

    @BeforeEach
    public void setUp() {
        // Create a mock GameField for testing
        gameField = mock(GameField.class);
        moveApplier = new MoveApplier(gameField);
    }

    @Test
    public void testApplyMovePlayer() {
        // Arrange: Set up mock field and method behavior
        int translatedInput = 3;  // Example column
        when(gameField.getField()).thenReturn(new char[6][7]);

        // Act: Apply a move for player
        moveApplier.applyMove(translatedInput, "player");

        // Assert: Check that the player's move is applied correctly
        verify(gameField).setField(translatedInput, 'S');
        assertEquals(1, MoveApplier.getPlayerMovesMade(), "Player's move count should increase by 1");
        assertEquals(1, MoveApplier.getMovesMade(), "Total moves count should increase by 1");
    }

    @Test
    public void testApplyMoveComputer() {
        // Arrange: Set up mock field and method behavior
        int translatedInput = 3;  // Example column
        when(gameField.getField()).thenReturn(new char[6][7]);

        // Act: Apply a move for computer
        moveApplier.applyMove(translatedInput, "computer");

        // Assert: Check that the computer's move is applied correctly
        verify(gameField).setField(translatedInput, 'P');
        assertEquals(1, MoveApplier.getMovesMade(), "Total moves count should increase by 1");
    }

    @Test
    public void testCheckColumnWithNoEmptySpaces() {
        // Arrange: Set up mock field with no empty spaces in column
        char[][] mockField = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mockField[i][j] = 'S';  // Set all spaces filled with 'S'
            }
        }
        when(gameField.getField()).thenReturn(mockField);

        // Act: Check if the column is available for a move
        boolean result = moveApplier.checkColumn(3); // Column 3
        assertFalse(result, "Column should not have empty spaces for a move.");
    }

    @Test
    public void testComputerMadeMove() {
        // Act: Get a random move for the computer
        int move = moveApplier.computerMadeMove();

        // Assert: Ensure the move is within the valid range of columns (0 to 6)
        assertTrue(move >= 0 && move < 7, "The computer's move should be between 0 and 6.");
    }
}
