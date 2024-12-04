package attilaprojects.player.score.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreDatabaseTest {

    private ScoreDatabase scoreDatabase;

    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        scoreDatabase = new ScoreDatabase();
    }

    @Test
    public void testSaveOrUpdateData() throws SQLException {
        // Arrange
        String playerName = "Player1";
        int score = 1000;

        // Configure mocks
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);  // Simulate successful database update

        // Act
        scoreDatabase.saveOrUpdateData(conn, playerName, score);

        // Assert
        verify(conn, times(1)).prepareStatement(anyString());
        verify(stmt, times(1)).setString(1, playerName);
        verify(stmt, times(1)).setInt(2, score);
        verify(stmt, times(1)).setInt(3, score);  // Update clause should be called
        verify(stmt, times(1)).executeUpdate();
        verify(stmt, times(1)).close();  // Ensure PreparedStatement is closed
    }

    @Test
    public void testLoadData() throws SQLException {
        // Arrange
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        // Mock ResultSet to return one row and then stop
        when(rs.next()).thenReturn(true, false);  // Simulate one row of data
        when(rs.getString("playername")).thenReturn("Player1");
        when(rs.getInt("score")).thenReturn(1000);

        // Act
        scoreDatabase.loadData(conn);

        // Assert
        verify(conn, times(1)).prepareStatement(anyString());
        verify(stmt, times(1)).executeQuery();
        verify(rs, times(2)).next();  // next() called once for data and once for end
        verify(stmt, times(1)).close();
        verify(rs, times(1)).close();
    }

    @Test
    public void testLoadPlayerStats_Found() throws SQLException {
        // Arrange
        String playerName = "Player1";
        int expectedScore = 1000;

        // Mock ResultSet to simulate the database result
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);  // Simulate a match
        when(rs.getInt("score")).thenReturn(expectedScore);

        // Act
        int actualScore = scoreDatabase.loadPlayerStats(conn, playerName);

        // Assert
        assertEquals(expectedScore, actualScore);
        verify(stmt, times(1)).setString(1, playerName);
        verify(stmt, times(1)).executeQuery();
    }

    @Test
    public void testLoadPlayerStats_NotFound() throws SQLException {
        // Arrange
        String playerName = "NonExistentPlayer";

        // Mock ResultSet to simulate no matching data
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);  // Simulate no data found

        // Act
        int actualScore = scoreDatabase.loadPlayerStats(conn, playerName);

        // Assert
        assertEquals(0, actualScore);  // Method returns 0 when player is not found
        verify(stmt, times(1)).setString(1, playerName);
        verify(stmt, times(1)).executeQuery();
    }
}
