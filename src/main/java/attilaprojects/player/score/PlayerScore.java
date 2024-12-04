package attilaprojects.player.score;

public class PlayerScore {
    private String playerName;
    private int winAmount;

    public PlayerScore(String playerName, int winAmount) {
        this.playerName = playerName;
        this.winAmount = winAmount;
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public int getWinAmount() { return winAmount; }

    // Setters

    public void incrementWinCount() {
        this.winAmount++;
    }

    @Override
    public String toString() {
        return "Player: " + playerName + " , Wins: " + winAmount;
    }
}
