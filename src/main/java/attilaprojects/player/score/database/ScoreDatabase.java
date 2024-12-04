package attilaprojects.player.score.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/playerscores";
    private static final String USER = "root";
    private static final String PASSWORD = "U7e5rjBuXRRICcRZ";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveOrUpdateData(Connection conn, String playerName, int score) throws SQLException {
        String sql = "INSERT INTO scores (playername, score) VALUES (?, ?) ON DUPLICATE KEY UPDATE score = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerName);
            stmt.setInt(2, score);
            stmt.setInt(3, score);  // Update clause
            stmt.executeUpdate();
            System.out.println("Data saved or updated successfully!");
        }
    }

    public void loadData(Connection conn) throws SQLException {
        String sql = "SELECT playername, score FROM scores";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             displayData(rs);  // Call the new method to display data
        }
    }

    // New method to display player names and scores
    private void displayData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String playerName = rs.getString("playername");
            int score = rs.getInt("score");
            System.out.println("Player: " + playerName + ", Score: " + score);
        }
    }
    public int loadPlayerStats(Connection conn, String playerName) throws SQLException {
        int score;
        String sql = "SELECT playername, score FROM scores WHERE playername = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerName);  // Set the player name in the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {  // If the player is found
                    score = rs.getInt("score");
                    System.out.println("Player: " + playerName + ", Score: " + score);
                } else {
                    System.out.println("No data found for player: " + playerName);
                    return 0;
                }
            }
        }
        return score;
    }
}
