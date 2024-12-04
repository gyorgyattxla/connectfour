package attilaprojects;

import attilaprojects.gamefield.GameField;

import attilaprojects.logic.GameLoop;
import attilaprojects.player.score.database.ScoreDatabase;

import java.sql.Connection;
import java.sql.SQLException;


public class Main
{
    public static void main(String[] args ) throws SQLException {
        //Initialize gameField
        GameField gameField = new GameField();

        GameLoop gameLoop = new GameLoop(gameField);
        ScoreDatabase db = new ScoreDatabase();

        try (Connection conn = db.getConnection()) {
            db.loadData(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        gameLoop.gameLoop();
    }
}

