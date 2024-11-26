package attilaprojects.player.namereader;

import java.util.Scanner;

public class PlayerNameReader implements PlayerNameReaderInterface {

    @Override
    public String inputPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Player Name: ");
        return scanner.nextLine();
    }
}
