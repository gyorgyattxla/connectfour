package attilaprojects.logic;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.reader.GameFieldReader;
import attilaprojects.gamefield.saver.GameFieldSaver;
import attilaprojects.gamestate.GameStateDisplayer;
import attilaprojects.gamestephandler.GameStepHandler;
import attilaprojects.player.PlayerNameReader;

import java.util.Scanner;

public class GameLoop {
    GameFieldReader gameFieldReader;
    GameFieldSaver gameFieldSaver;
    GameStepHandler gameStepHandler;
    GameStateDisplayer gameStateDisplayer;
    PlayerNameReader playerNameReader;
    CommandHandler commandHandler;
    GameField gameField = GameField.getInstance();

    public GameLoop(GameField gameField) {
        this.gameFieldReader = new GameFieldReader(gameField);
        this.gameFieldSaver = new GameFieldSaver(gameField);
        this.gameStepHandler = new GameStepHandler(gameField);
        this.gameStateDisplayer = new GameStateDisplayer(gameField);
        this.playerNameReader = new PlayerNameReader();
        this.commandHandler = new CommandHandler(gameField);
    }

    public void gameLoop(){

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
        String playerName = playerNameReader.inputPlayerName();
        // The player always makes the first move
        System.out.println(playerName + " starts!");

        boolean playerWon = gameStepHandler.checkPlayerWinState();
        boolean computerWon = gameStepHandler.checkComputerWinState();
        boolean isFilled = gameStepHandler.isFieldFilled();

        while ( (!playerWon && !computerWon) && !isFilled){
            System.out.println("Valid Commands: 'place' / 'save' / 'load'");
            // Display the field
            gameStateDisplayer.displayGameState(playerName);

            commandHandler.commandExecuter();
            //Check if the player has won yet
            playerWon = gameStepHandler.checkPlayerWinState();
            if (playerWon) break;   //if the player won exit the game loop.
            System.out.println("Computer makes a move!");
            int computerMove = gameStepHandler.computerMadeMove();
            while (!gameStepHandler.checkColumn(computerMove)) {
                computerMove = gameStepHandler.computerMadeMove();
            }

            //Applying the computerMove to gameField
            gameStepHandler.applyMove(computerMove, "computer");

            //Check if the computer has won yet
            computerWon = gameStepHandler.checkComputerWinState();
        }
        if (playerWon) System.out.println(playerName + " has won!");
        else if (computerWon) System.out.println("Computer has won!");
        if (isFilled) System.out.println("The board has been filled...");
        gameStateDisplayer.displayGameState(playerName);
        System.out.println("Want to save this field? [USE Y / N]");
        String wantToSave = scanner.nextLine();
        while( !(wantToSave.equals("Y")) && !(wantToSave.equals("N"))){
            System.out.println("[USE Y / N]");
            wantToSave = scanner.nextLine();
        }
        if (wantToSave.equals("Y")){
            System.out.print("Enter filename: ");
            String inputFilename = scanner.nextLine();
            gameFieldSaver.saveFieldToFile(gameField, inputFilename);
        }

    }
}
