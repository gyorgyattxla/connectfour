package attilaprojects.gamestephandler.winhandler.computer;

import attilaprojects.gamefield.GameField;

public class CheckComputerWin{
    private final GameField gameField;

    public CheckComputerWin(GameField gameField) {
        this.gameField = gameField;
    }

    /** After every computer move check if the computer has won yet **/
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
}
