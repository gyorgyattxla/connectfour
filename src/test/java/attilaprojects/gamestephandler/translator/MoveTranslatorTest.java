package attilaprojects.gamestephandler.translator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTranslatorTest {

    private final MoveTranslator moveTranslator = new MoveTranslator();

    @Test
    public void testTranslatePlayerMoveValidInput() {
        // Test valid input cases (A-G)
        assertEquals(0, moveTranslator.translatePlayerMove("A"));
        assertEquals(1, moveTranslator.translatePlayerMove("B"));
        assertEquals(2, moveTranslator.translatePlayerMove("C"));
        assertEquals(3, moveTranslator.translatePlayerMove("D"));
        assertEquals(4, moveTranslator.translatePlayerMove("E"));
        assertEquals(5, moveTranslator.translatePlayerMove("F"));
        assertEquals(6, moveTranslator.translatePlayerMove("G"));
    }

    @Test
    public void testTranslatePlayerMoveInvalidInput() {
        // Test invalid input (non A-G values)
        assertEquals(-1, moveTranslator.translatePlayerMove("H"));  // Out of range
        assertEquals(-1, moveTranslator.translatePlayerMove("X"));  // Random character
        assertEquals(-1, moveTranslator.translatePlayerMove(""));   // Empty input
    }

    @Test
    public void testTranslatePlayerMoveEmptyString() {
        // Test for empty string input
        assertEquals(-1, moveTranslator.translatePlayerMove(""));  // Empty input
    }
}
