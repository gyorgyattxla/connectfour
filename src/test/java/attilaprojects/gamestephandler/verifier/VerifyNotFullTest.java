package attilaprojects.gamestephandler.verifier;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamestephandler.applier.MoveApplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VerifyNotFullTest {

    private GameField gameField;
    private VerifyNotFull verifyNotFull;

    @BeforeEach
    public void setUp() {
        // Mock the GameField
        gameField = mock(GameField.class);
        verifyNotFull = new VerifyNotFull(gameField);
    }

    @Test
    public void testIsFieldFilledWhenFieldIsNotFull() {
        // Mock the row and column counts
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Set moves made to a value less than total cells
        MoveApplier.setMovesMade(30); // Set a custom move count (less than 42)
        assertFalse(verifyNotFull.isFieldFilled(), "Field should not be full when moves made are less than total cells");
    }

    @Test
    public void testIsFieldFilledWhenFieldIsFull() {
        // Mock the row and column counts
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Set moves made to the total number of cells (42 for 6x7 field)
        MoveApplier.setMovesMade(42);
        assertTrue(verifyNotFull.isFieldFilled(), "Field should be full when moves made are equal to total cells");
    }

    @Test
    public void testIsFieldFilledWhenNoMovesMade() {
        // Mock the row and column counts
        when(gameField.getRowCount()).thenReturn(6);
        when(gameField.getColCount()).thenReturn(7);

        // Set moves made to 0
        MoveApplier.setMovesMade(0);
        assertFalse(verifyNotFull.isFieldFilled(), "Field should not be full when no moves are made");
    }
}
