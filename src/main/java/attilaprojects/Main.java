package attilaprojects;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.GameFieldReader;

import attilaprojects.gamefield.GameFieldSaver;
import attilaprojects.gamestate.GameStateDisplayer;
import attilaprojects.gamestephandler.GameStepHandler;
import attilaprojects.player.PlayerNameReader;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args ) {
        for (int i = 0; i < 3; i++) {
            System.out.println("PLAYING CONNECT FOUR");
        }

        /** Initialize gameField **/
        GameField gameField = new GameField();

        GameFieldReader gameFieldReader = new GameFieldReader(gameField);
        GameFieldSaver gameFieldSaver = new GameFieldSaver(gameField);
        GameStepHandler gameStepHandler = new GameStepHandler(gameField);
        GameStateDisplayer gameStateDisplayer = new GameStateDisplayer(gameField);
        PlayerNameReader playerNameReader = new PlayerNameReader();

        //Read gameField or set an empty one.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to load a custom map? [USE Y / N]");
        String wantToLoad = " ";
        while (!(wantToLoad.equals("Y")) && !(wantToLoad.equals("N"))) wantToLoad = scanner.nextLine();
        if (wantToLoad.equals("Y")) {
            System.out.print("Enter file name: ");
            gameFieldReader.loadFieldFromFile(scanner.nextLine());
        } else gameFieldReader.setEmptyField();

        //Get player name
        String playerName = playerNameReader.inputPlayerName();

        //The player always makes the first move
        System.out.println(playerName + " starts!");

        //The game
        boolean playerWon = gameStepHandler.checkPlayerWinState();
        boolean computerWon = gameStepHandler.checkComputerWinState();
        boolean isFilled = gameStepHandler.isFieldFilled();
        while ( (!playerWon && !computerWon) && !isFilled) {

            //Display the field
            gameStateDisplayer.displayGameState(playerName);

            //Player makes a move
            System.out.println("Make a move! [USE A - G]");
            String playerMove = scanner.nextLine();
            //Player redoes the move until it is in valid format
            while (gameStepHandler.translatePlayerMove(playerMove) == -1 ) {
                System.out.println("[USE A - G]");
                playerMove = scanner.nextLine();
            }
            int translatedMove = gameStepHandler.translatePlayerMove(playerMove);

            //Checking if the move is possible
            while (!gameStepHandler.checkColumn(translatedMove)) {

                //Player makes a move
                System.out.println("Not a valid move! [USE A - G]");
                playerMove = scanner.nextLine();
                //Player redoes the move until it is in valid format
                while (gameStepHandler.translatePlayerMove(playerMove) < 0 && gameStepHandler.translatePlayerMove(playerMove) > 6) {
                    System.out.println("[USE A - G]");
                    playerMove = scanner.nextLine();
                }
                translatedMove = gameStepHandler.translatePlayerMove(playerMove);
            }

            //Applying the playerMove to gameField
            gameStepHandler.applyMove(translatedMove);

            //Check if the player has won yet
            playerWon = gameStepHandler.checkPlayerWinState();

            //Computer makes a move
            System.out.println("Computer makes a move!");
            int computerMove = gameStepHandler.computerMadeMove();
            while (!gameStepHandler.checkColumn(computerMove)) {
                computerMove = gameStepHandler.computerMadeMove();
            }

            //Applying the computerMove to gameField
            gameStepHandler.applyMove(computerMove);

            //Check if the computer has won yet
            computerWon = gameStepHandler.checkComputerWinState();
        }

        if (playerWon) System.out.println(playerName + " has won!");
        if (computerWon) System.out.println("Computer has won!");
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

