package attilaprojects.player.score;

import attilaprojects.gamestephandler.applier.MoveApplier;

public class PlayerScore {
    private final int startingPlayerScore = 25; //42 possible moves/2 +4 = 25, making 21 the perfect score

    /** Every time a player makes a move the score decreases **/
    private int decreaseScore(){
        return startingPlayerScore - MoveApplier.getPlayerMovesMade();
    }
}
