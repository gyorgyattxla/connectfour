package attilaprojects.logic;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.reader.GameFieldReader;
import attilaprojects.gamefield.saver.GameFieldSaver;
import attilaprojects.gamestate.GameStateDisplayer;
import attilaprojects.gamestephandler.GameStepHandler;
import attilaprojects.player.PlayerNameReader;

import java.util.Scanner;

public class CommandHandler {

    GameFieldReader gameFieldReader;
    GameFieldSaver gameFieldSaver;
    GameStepHandler gameStepHandler;
    GameStateDisplayer gameStateDisplayer;
    PlayerNameReader playerNameReader;
    GameField gameField = GameField.getInstance();

    public CommandHandler(GameField gameField) {
        this.gameFieldReader = new GameFieldReader(gameField);
        this.gameFieldSaver = new GameFieldSaver(gameField);
        this.gameStepHandler = new GameStepHandler(gameField);
        this.gameStateDisplayer = new GameStateDisplayer(gameField);
        this.playerNameReader = new PlayerNameReader();
    }

    public void commandExecuter(){
        Scanner commandScanner = new Scanner(System.in);
        String command = commandScanner.nextLine();
        boolean validCommand = false;
        while (!validCommand){
            switch (command){
                case ("place"):{
                    System.out.println("PLACING MOVE:");
                    Scanner scanner = new Scanner(System.in);
                    // Player makes a move
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
                    gameStepHandler.applyMove(translatedMove, "player");
                    validCommand = true;
                    break;
                }
                case ("save"):{
                    System.out.println("SAVING MAP:");
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter filename: ");
                    String inputFilename = scanner.nextLine();
                    gameFieldSaver.saveFieldToFile(gameField, inputFilename);
                    validCommand = true;
                    break;
                }
                case ("load"):{
                    System.out.println("LOADING MAP:");
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter file name: ");
                    gameFieldReader.loadFieldFromFile(scanner.nextLine());
                    validCommand = true;
                    break;
                }
                default:{
                    System.err.println("Not valid command.\n [USE 'place'/'save'/'load'] ");
                    command = commandScanner.nextLine();
                }
            }
        }
    }
}
