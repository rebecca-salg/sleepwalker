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
    public void createLevel() throws Exception {
        drawShape(3,5,5,1);
        drawShape(3,21,5,1);
        drawShape(50,2,1,5);
        drawShape(50,21,1,5);

    }
     public void drawShape(int xStart, int yStart, int length, int height) throws Exception {
        for (int i = yStart - height; i <= yStart; i++) {
            for (int j = xStart; j <= xStart + length; j++){
                Piece wall = new Wall(i,j);
                board.setPieceAtLocation(j,i, wall);
                drawCharacter(wall);
            }
        }
    }
}

