package attilaprojects.gamestate;

import attilaprojects.gamefield.GameField;

public class GameStateDisplayer implements GameStateDisplayerInterface{
    private final GameField gameField;
    private final char[] columns = {'A','B','C','D','E','F','G'};

    public GameStateDisplayer(GameField gameField) {
        this.gameField = GameField.getInstance();
    }
    @Override
    public void displayGameState(String playerName) {
        System.out.println(playerName + " vs. Computer");
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                System.out.print(gameField.getField()[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < columns.length; i++) {
            System.out.print(columns[i]+" ");
        }
        System.out.println();
    }
}
