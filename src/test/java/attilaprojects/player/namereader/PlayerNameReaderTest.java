package attilaprojects.player.namereader;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PlayerNameReaderTest {

    @Test
    public void testInputPlayerName() {
        // Simulate user input by redirecting System.in
        String simulatedInput = "JohnDoe\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        PlayerNameReader playerNameReader = new PlayerNameReader();
        String playerName = playerNameReader.inputPlayerName();

        // Assert that the player name is correctly read
        assertEquals("JohnDoe", playerName);

        // Restore original System.in
        System.setIn(originalSystemIn);
    }
}
