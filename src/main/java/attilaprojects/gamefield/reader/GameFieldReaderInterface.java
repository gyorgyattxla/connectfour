package attilaprojects.gamefield.reader;

public interface GameFieldReaderInterface {
    boolean loadFieldFromFile(String inputFileName);
    void setEmptyField();
}
