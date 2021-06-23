import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.concurrent.ExecutionException;

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

    public void movePlayer(KeyType keyInput) {
        switch (keyInput) {

            case ArrowUp -> movePlayerUp();
            case ArrowDown -> movePlayerDown();
            case ArrowRight -> movePlayerRight();
            case ArrowLeft -> movePlayerLeft();

//            case ArrowUp -> movePlayer(KeyType.ArrowUp);
//            case ArrowDown -> movePlayer(KeyType.ArrowDown);
//            case ArrowRight -> movePlayer(KeyType.ArrowRight);
//            case ArrowLeft -> movePlayer(KeyType.ArrowLeft);
        }
    }

    private void movePlayerUp() throws Exception {
        Piece position = board.getPlayer();
        int xUp = position.getxCoordinate();
        int yUp = position.getyCoordinate();
        erasePlayersLastPosition(xUp, yUp);
        position.setyCoordinate(yUp - 1);
    }

    private void movePlayerDown() throws Exception {
        Piece position = board.getPlayer();
        int xDown = position.getxCoordinate();
        int yDown = position.getyCoordinate();
        erasePlayersLastPosition(xDown, yDown);
        position.setyCoordinate(yDown + 1);
    }

    private void movePlayerRight() throws Exception {
        Piece position = board.getPlayer();
        int xRight = position.getxCoordinate();
        int yRight = position.getyCoordinate();
        erasePlayersLastPosition(xRight, yRight);
        position.setxCoordinate(xRight + 1);
    }

    private void movePlayerLeft() throws Exception {
        Piece position = board.getPlayer();
        int xLeft = position.getxCoordinate();
        int yLeft = position.getyCoordinate();
        erasePlayersLastPosition(xLeft, yLeft);
        position.setxCoordinate(xLeft - 1);
    }

    public void erasePlayersLastPosition(int xOld, int yOld) throws Exception {
        terminal.setCursorPosition(xOld , yOld);
        terminal.putCharacter(' ');
    }
}

