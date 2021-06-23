import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Board board;
    private Terminal terminal;

    public Game() throws Exception {
        this.board = new Board(24, 60);
        this.terminal = initiateTerminal();
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Terminal initiateTerminal() throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

    public void displayPieces (Piece piece) throws IOException {
        terminal.setCursorPosition(piece.getxCoordinate(), piece.getCoordinate());
        terminal.putCharacter(piece.displayPiece());
        terminal.flush();
    }

    public void movePlayer (KeyType keyInput) {
        switch (keyInput) {

            case ArrowUp -> movePlayer(KeyType.ArrowUp);
            case ArrowDown -> movePlayer(KeyType.ArrowDown);
            case ArrowRight -> movePlayer(KeyType.ArrowRight);
            case ArrowLeft -> movePlayer(KeyType.ArrowLeft);
        }
    }
}

