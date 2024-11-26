package attilaprojects.gamestephandler.verifier;

import attilaprojects.gamefield.GameField;
import attilaprojects.gamestephandler.applier.MoveApplier;

public class VerifyNotFull{

    private final GameField gameField;

    public VerifyNotFull(GameField gameField) {
        this.gameField = gameField;
    }

    public boolean isFieldFilled(){
        if(MoveApplier.getMovesMade() >= (gameField.getRowCount() * gameField.getColCount())){
            return true;
        }
        return false;
    }
}
