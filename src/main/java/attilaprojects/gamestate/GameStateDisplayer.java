package attilaprojects.gamestate;

import attilaprojects.gamefield.GameField;

public class GameStateDisplayer implements GameStateDisplayerInterface{
    private final GameField gameField;

    public GameStateDisplayer(GameField gameField) {
        this.gameField = gameField;
    }
    @Override
    public void displayGameState() {
        for (int i = 0; i < gameField.getRowCount(); i++) {
            for (int j = 0; j < gameField.getColCount(); j++) {
                System.out.print(gameField.getField()[i][j] + " ");
            }
        }
    }
}
