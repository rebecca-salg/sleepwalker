import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Main {
    public static void main(String[] args) throws Exception {

        Game game = new Game();
        game.createLevel();

        KeyStroke keyStroke;
        while (true) {
            keyStroke = null;

            do {
                Thread.sleep(200);
                keyStroke = game.getTerminal().pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();


            game.movePlayer(type);
            game.getTerminal().flush();
        }
    }
}
