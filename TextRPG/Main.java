package TextRPG;

import TextRPG.Util.GameFunctions;
import TextRPG.Util.Library;
import TextRPG.Util.Property.Player;

public class Main {

    static Player player = Library.player;

    static public void main(String[] args) {
        System.out.println("Always answer 'Y' or 'N' for yes or no");

        System.out.println("-----TEST-----");

        while (Library.player.getCurrentRoom() != Library.exitRoom) {
            if (!GameFunctions.render(player)) {
            } else {
                break;
            }
        }
    }
}
