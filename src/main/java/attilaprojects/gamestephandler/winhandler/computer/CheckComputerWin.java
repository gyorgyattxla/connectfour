package attilaprojects.gamestephandler.winhandler.computer;

import attilaprojects.gamefield.GameField;

/**
 * This class checks if the computer has won the game.
 */
public class CheckComputerWin {
    private final GameField gameField;

    /**
     * Constructor that initializes the CheckComputerWin with a game field.
     *
     * @param gameField the GameField object representing the current game state.
     */
    public CheckComputerWin(GameField gameField) {
        this.gameField = gameField;
    }

    /**
     * After every computer move, check if the computer has won yet.
     * This method checks all possible winning directions: horizontal, vertical, and both diagonals.
     *
     * @return true if the computer has won, otherwise false.
     */
    public boolean checkComputerWinState() {
        char computer = 'P';
        int count;

        /* Check in horizontal direction */
        count = 0;
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                if (gameField.getField()[i][j] == computer) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
            }
        }

        /* Check in vertical direction */
        count = 0;
        for (int i = 0; i < gameField.getColCount(); i++) {
            for (int j = 0; j < gameField.getRowCount(); j++) {
                if (gameField.getField()[j][i] == computer) {
                    count += 1;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
            }
        }

        /* Check in first diagonal direction */
        int max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = max - 1;
            int j = 0;
            while (j < max) {
                if (gameField.getField()[i][j] == computer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
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
            while (j > k) {
                if (gameField.getField()[i][j] == computer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
                i++;
                j--;
            }
            max--;
        }

        /* Check in second diagonal direction */
        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = k;
            int j = 0;
            while (j < max) {
                if (gameField.getField()[i][j] == computer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
                i++;
                j++;
            }
            max--;
        }

        max = gameField.getField().length;
        for (int k = 0; k < gameField.getField().length; k++) {
            count = 0;
            int i = 0;
            int j = 1 + k;
            while (i < max) {
                if (gameField.getField()[i][j] == computer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) return true;
                i++;
                j++;
            }
            max--;
        }

        return false;
    }
}