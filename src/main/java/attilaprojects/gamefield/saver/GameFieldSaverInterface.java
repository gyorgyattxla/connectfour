package attilaprojects.gamefield.saver;

import attilaprojects.gamefield.GameField;

public interface GameFieldSaverInterface {
    public boolean saveFieldToFile(GameField gameField, String filename);
}
