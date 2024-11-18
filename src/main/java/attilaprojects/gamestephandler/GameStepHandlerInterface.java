package attilaprojects.gamestephandler;

public interface GameStepHandlerInterface {
    public int translatePlayerMove(String userInput);
    public void applyMove(int translatedInput, String player);
    public int computerMadeMove();
    public boolean checkColumn(int translatedInput);
    public boolean checkPlayerWinState();
    public boolean checkComputerWinState();
    public boolean isFieldFilled();
}
