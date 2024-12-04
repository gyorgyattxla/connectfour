package attilaprojects.gamestephandler.applier;

import attilaprojects.gamefield.GameField;

import java.util.Random;

public class MoveApplier {
    private final GameField gameField;
    private static int movesMade;
    private static int playerMovesMade;

    public MoveApplier(GameField gameField) {
        this.gameField = gameField;
        movesMade = 0;
        playerMovesMade = 0;
    }

    public static void setMovesMade(int movesMade) {
        MoveApplier.movesMade = movesMade;
    }

    public static int getMovesMade() {
        return movesMade;
    }
    public static int getPlayerMovesMade() {
        return playerMovesMade;
    }

    /** Uses the 'translatedInput' to apply the move to the field **/
    public void applyMove(int translatedInput, String player) {
        if(player.equals("player")){
            gameField.setField(translatedInput,'S');
            playerMovesMade++;
        }
        if(player.equals("computer")) gameField.setField(translatedInput,'P');
        movesMade++;
    }

    public int computerMadeMove(){
        Random random = new Random();
        return random.nextInt(7);
    }

    /** Before applying the move to the field, the method checks if the move is possible to be made **/
    public boolean checkColumn(int translatedInput) {
        int spacesLeft = 0;
        for (int i = 0; i < gameField.getRowCount(); i++) {
            if (gameField.getField()[i][translatedInput] == '#') {
                spacesLeft += 1;
            }else break;
        }
        return spacesLeft > 0;
    }
}
