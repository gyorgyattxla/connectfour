package attilaprojects.logic;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamefield.reader.GameFieldReader;
import attilaprojects.gamefield.saver.GameFieldSaver;
import attilaprojects.gamestephandler.applier.MoveApplier;
import attilaprojects.gamestephandler.translator.MoveTranslator;

import java.util.Scanner;

public class CommandHandler {
    private final GameFieldReader gameFieldReader;
    private final GameFieldSaver gameFieldSaver;
    private final MoveApplier moveApplier;
    private final MoveTranslator moveTranslator;
    private final GameField gameField = GameField.getInstance();

    public CommandHandler(GameField gameField) {
        this.gameFieldReader = new GameFieldReader(gameField);
        this.gameFieldSaver = new GameFieldSaver(gameField);
        this.moveApplier = new MoveApplier(gameField);
        this.moveTranslator = new MoveTranslator();
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
                    while (moveTranslator.translatePlayerMove(playerMove) == -1 ) {
                        System.out.println("[USE A - G]");
                        playerMove = scanner.nextLine();
                    }
                    int translatedMove = moveTranslator.translatePlayerMove(playerMove);

                    //Checking if the move is possible
                    while (!moveApplier.checkColumn(translatedMove)) {

                        System.out.println("Not a valid move! [USE A - G]");
                        playerMove = scanner.nextLine();
                        //Player redoes the move until it is in valid format
                        while (moveTranslator.translatePlayerMove(playerMove) < 0 && moveTranslator.translatePlayerMove(playerMove) > 6) {
                            System.out.println("[USE A - G]");
                            playerMove = scanner.nextLine();
                        }
                        translatedMove = moveTranslator.translatePlayerMove(playerMove);
                    }

                    //Applying the playerMove to gameField
                    moveApplier.applyMove(translatedMove, "player");
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
