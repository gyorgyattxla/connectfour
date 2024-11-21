package attilaprojects.gamestephandler;

import attilaprojects.gamefield.GameField;

import java.util.Random;

public class GameStepHandler implements GameStepHandlerInterface{

    private final GameField gameField;
    /** Using a kind-of unnecessary step to convert a char into integer to better user experience **/
    private int translatedInput;
    private int madeMoves = 0;

    public GameStepHandler(GameField gameField) {
        this.gameField = gameField;
    }

    /** Translates a character userInput into an integer that other methods can use **/
    @Override
    public int translatePlayerMove(String userInput) {
        switch (userInput){
            case ("A"):
                return translatedInput = 0;
            case ("B"):
                return translatedInput = 1;
            case ("C"):
                return translatedInput = 2;
            case ("D"):
                return translatedInput = 3;
            case ("E"):
                return translatedInput = 4;
            case ("F"):
                return translatedInput = 5;
            case ("G"):
                return translatedInput = 6;
            default:
                System.out.println("Not valid character input. [USE A-G]");
                return -1;
        }
    }

    /** Uses the 'translatedInput' to apply the move to the field **/
    @Override
    public void applyMove(int translatedInput, String player) {
        if(player.equals("player")) gameField.setField(translatedInput,'S');
        if(player.equals("computer")) gameField.setField(translatedInput,'P');
    }

    @Override
    public int computerMadeMove(){
        Random random = new Random();
        return random.nextInt(7);
    }

    /** Before applying the move to the field, the method checks if the move is possible to be made **/
    @Override
    public boolean checkColumn(int translatedInput) {
        int spacesLeft = 0;
        for (int i = 0; i < gameField.getRowCount(); i++) {
            if (gameField.getField()[i][translatedInput] == '#') {
                spacesLeft += 1;
            }else break;
        }
        return spacesLeft > 0;
    }

    /** After every player move check if the player has won yet **/
    @Override
    public boolean checkPlayerWinState() {
        char player = 'S';
        /* check in horizontal direction */
        int count = 0;
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                if(gameField.getField()[i][j] == player){
                    count+=1;
                }else count = 0;
                if (count==4) return true;
            }
        }
        /* check in vertical direction */
        count = 0;
        for (int i = 0; i < gameField.getColCount(); i++) {
            for (int j = 0; j < gameField.getRowCount(); j++) {
                if(gameField.getField()[j][i] == player) count+=1;
                else count=0;
                if(count==4) return true;
            }
        }
        /* check in first diagonal direction */
        int max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = max-1;
            int j = 0;
            while(j < max){
                if (gameField.getField()[i][j] == player) count++;
                else count=0;
                if(count==4) return true;
                i--;
                j++;
            }
            max--;
        }
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = k;
            int j = 6;
            while(j > k){
                if (gameField.getField()[i][j] == player) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j--;
            }
            max--;
        }
        /* check in second diagonal direction */
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = k;
            int j = 0;
            while(j < max){
                if (gameField.getField()[i][j] == player) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j++;
            }
            max--;
        }
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = 0;
            int j = 1+k;
            while(i < max){
                if (gameField.getField()[i][j] == player) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j++;
            }
            max--;
        }
        return false;
    }

    /** After every computer move check if the computer has won yet
     *  These are separate because after a computer made move we don't
     *  need to check if the player has won yet, as this is impossible **/
    @Override
    public boolean checkComputerWinState() {
        char computer = 'P';
        /* check in horizontal direction */
        int count = 0;
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                if(gameField.getField()[i][j] == computer){
                    count+=1;
                }else count = 0;
                if (count==4) return true;
            }
        }
        /* check in vertical direction */
        count = 0;
        for (int i = 0; i < gameField.getColCount(); i++) {
            for (int j = 0; j < gameField.getRowCount(); j++) {
                if(gameField.getField()[j][i] == computer) count+=1;
                else count=0;
                if(count==4) return true;
            }
        }
        /* check in first diagonal direction */
        int max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = max-1;
            int j = 0;
            while(j < max){
                if (gameField.getField()[i][j] == computer) count++;
                else count=0;
                if(count==4) return true;
                i--;
                j++;
            }
            max--;
        }
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = k;
            int j = 6;
            while(j > k){
                if (gameField.getField()[i][j] == computer) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j--;
            }
            max--;
        }
        /* check in second diagonal direction */
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = k;
            int j = 0;
            while(j < max){
                if (gameField.getField()[i][j] == computer) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j++;
            }
            max--;
        }
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = 0;
            int j = 1+k;
            while(i < max){
                if (gameField.getField()[i][j] == computer) count++;
                else count=0;
                if(count==4) return true;
                i++;
                j++;
            }
            max--;
        }
        return false;
    }
    @Override
    public boolean isFieldFilled(){
        if(madeMoves >= (gameField.getRowCount()*gameField.getColCount())){
            return true;
        }
        return false;
    }
}
