import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Main {
    public static void main(String[] args) throws Exception {

        Game game = new Game();
        game.initiateGame();

        KeyStroke keyStroke;
        while (true) {
            keyStroke = null;

            do {
                Thread.sleep (200);
                keyStroke = game.terminal.pollInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();

            switch (type) {
                case ArrowUp -> game.movePlayer(KeyType.ArrowUp);
                case ArrowDown -> game.movePlayer(KeyType.ArrowDown);
                case ArrowRight -> game.movePlayer(KeyType.ArrowRight);
                case ArrowLeft -> game.movePlayer(KeyType.ArrowLeft);
            }
            game.terminal.flush();
        }
    }
}
