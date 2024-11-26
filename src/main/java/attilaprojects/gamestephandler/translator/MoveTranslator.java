package attilaprojects.gamestephandler.translator;

public class MoveTranslator{
    /** Translates a character userInput into an integer that other methods can use **/
    public int translatePlayerMove(String userInput) {
        switch (userInput){
            case ("A"):
                return 0;
            case ("B"):
                return 1;
            case ("C"):
                return 2;
            case ("D"):
                return 3;
            case ("E"):
                return 4;
            case ("F"):
                return 5;
            case ("G"):
                return 6;
            default:
                System.out.println("Not valid character input. [USE A-G]");
                return -1;
        }
    }
}
