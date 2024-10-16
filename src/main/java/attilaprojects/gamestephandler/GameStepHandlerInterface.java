package attilaprojects.gamestephandler;

public interface GameStepHandlerInterface {
    public boolean translatePlayerMove(char userInput);
    public void applyMove(int translatedInput);
    public int computerMadeMove();
    public boolean checkColumn(int translatedInput);
    public boolean checkPlayerWinState();
    public boolean checkComputerWinState();
}
