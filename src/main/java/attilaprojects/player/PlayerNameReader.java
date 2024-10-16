package attilaprojects.player;

public class PlayerNameReader implements PlayerNameReaderInterface{
    private String playerName;

    public PlayerNameReader(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void inputPlayerName(String input) {
        this.playerName = input;
    }

    public String getPlayerName() {
        return playerName;
    }
}
