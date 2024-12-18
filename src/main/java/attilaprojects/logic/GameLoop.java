package attilaprojects.logic;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.reader.GameFieldReader;
import attilaprojects.gamefield.saver.GameFieldSaver;
import attilaprojects.gamestate.GameStateDisplayer;
import attilaprojects.gamestephandler.applier.MoveApplier;
import attilaprojects.gamestephandler.verifier.VerifyNotFull;
import attilaprojects.gamestephandler.winhandler.computer.CheckComputerWin;
import attilaprojects.gamestephandler.winhandler.player.CheckPlayerWin;
import attilaprojects.player.namereader.PlayerNameReader;
import attilaprojects.player.score.PlayerScore;
import attilaprojects.player.score.database.ScoreDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GameLoop {
    private final GameFieldReader gameFieldReader;
    //private final GameFieldSaver gameFieldSaver;
    private final VerifyNotFull verifyNotFull;
    private final MoveApplier moveApplier;
    private final CheckPlayerWin checkPlayerWin;
    private final CheckComputerWin checkComputerWin;
    private final GameStateDisplayer gameStateDisplayer;
    private final PlayerNameReader playerNameReader;
    private final CommandHandler commandHandler;
    private final GameField gameField = GameField.getInstance();
    private static String playerName;
    private boolean playerWin;

    public GameLoop(GameField gameField) {
        this.gameFieldReader = new GameFieldReader(gameField);
        this.verifyNotFull = new VerifyNotFull(gameField);
        this.gameStateDisplayer = new GameStateDisplayer(gameField);
        this.playerNameReader = new PlayerNameReader();
        this.commandHandler = new CommandHandler(gameField);
        this.moveApplier = new MoveApplier(gameField);
        this.checkPlayerWin = new CheckPlayerWin(gameField);
        this.checkComputerWin = new CheckComputerWin(gameField);
    }

    public static String getPlayerName() {
        return playerName;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public void gameLoop() throws SQLException {

        for (int i = 0; i < 3; i++) {
            System.out.println("PLAYING CONNECT FOUR");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to load a map? [USE Y / N]");
        String wantToLoad = " ";
        while (!(wantToLoad.equals("Y")) && !(wantToLoad.equals("N"))) wantToLoad = scanner.nextLine();
        if (wantToLoad.equals("Y")) {
            System.out.print("Enter file name: ");
            gameFieldReader.loadFieldFromFile(scanner.nextLine());
        } else gameFieldReader.setEmptyField();

        // Get player name
        playerName = playerNameReader.inputPlayerName();
        // The player always makes the first move
        System.out.println(playerName + " starts!");

        boolean playerWon = checkPlayerWin.checkPlayerWinState();
        boolean computerWon = checkComputerWin.checkComputerWinState();
        boolean isFilled = verifyNotFull.isFieldFilled();

        while ( (!playerWon && !computerWon) && !isFilled){
            System.out.println("Valid Commands: 'place' / 'save' / 'load'");
            // Display the field
            gameStateDisplayer.displayGameState(playerName);

            commandHandler.commandExecuter();
            //Check if the player has won yet
            playerWon = checkPlayerWin.checkPlayerWinState();
            if (playerWon) break;   //if the player won exit the game loop.
            System.out.println("Computer makes a move!");
            int computerMove = moveApplier.computerMadeMove();
            while (!moveApplier.checkColumn(computerMove)) {
                computerMove = moveApplier.computerMadeMove();
            }

            //Applying the computerMove to gameField
            moveApplier.applyMove(computerMove, "computer");

            //Check if the computer has won yet
            computerWon = checkComputerWin.checkComputerWinState();
        }
        ScoreDatabase scoreDatabase = new ScoreDatabase();
        Connection connection = scoreDatabase.getConnection();
        if (playerWon){
            System.out.println(playerName + " has won!");

            int score = scoreDatabase.loadPlayerStats(scoreDatabase.getConnection(), playerName);
            PlayerScore playerScore = new PlayerScore(playerName, score);
            playerScore.incrementWinCount();
            scoreDatabase.saveOrUpdateData(connection,
                    playerName,
                    playerScore.getWinAmount());
        }
        else if (computerWon) System.out.println("Computer has won!");
        if (isFilled) System.out.println("The board has been filled...");
        gameStateDisplayer.displayGameState(playerName);
        try {
            String query = "SELECT * FROM scores";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            scoreDatabase.displayData(resultSet);

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Want to save this field? [USE Y / N]");
        String wantToSave = scanner.nextLine();
        while( !(wantToSave.equals("Y")) && !(wantToSave.equals("N"))){
            System.out.println("[USE Y / N]");
            wantToSave = scanner.nextLine();
        }
        if (wantToSave.equals("Y")){
            GameFieldSaver gameFieldSaver = new GameFieldSaver(gameField);
            System.out.print("Enter filename: ");
            String inputFilename = scanner.nextLine();
            gameFieldSaver.saveFieldToFile(inputFilename);
        }
    }
}
