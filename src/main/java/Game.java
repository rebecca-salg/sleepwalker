import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Game {
    private Board board;
    private Terminal terminal;

    public Game() throws Exception {
        this.board = new Board(80, 24);
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

    public void displayPieces(Piece piece) throws IOException {
        terminal.setCursorPosition(piece.getxCoordinate(), piece.getyCoordinate());
        terminal.putCharacter(piece.displayPiece());
        terminal.flush();
    }

    public void movePlayer(KeyType keyInput) throws Exception{
        switch (keyInput) {

            case ArrowUp -> movePlayer(-1,0);
            case ArrowDown -> movePlayer(1,0);
            case ArrowRight -> movePlayer(0,1);
            case ArrowLeft -> movePlayer(0 ,-1);

        }
    }

    public void createLevel() throws Exception {
        //drawShape(2, 12, 0, 0); //playerposition
        drawShape(3, 1, 5, 0);
        drawShape(3, 7, 1, 5);
        drawShape(45, 8, 9, 0);
        drawShape(50, 7, 1, 5);
        drawShape(3, 20, 5, 0);
        drawShape(30, 20, 20, 2);
        drawShape(77, 15, 1, 8);
        drawShape(66, 20, 11, 0);
        drawShape(67, 11, 9, 0);
        drawShape(67, 3, 9, 0);
        drawShape(65, 7, 1, 0);
        drawShape(62, 16, 3, 0);
        drawShape(3, 19, 1, 5);
        drawShape(15, 7, 1, 3);
        drawShape(13, 17, 5, 0);
        drawShape(60, 20, 1, 0); //Goal
    }

    public void drawShape(int xStart, int yStart, int length, int height) throws Exception {
        for (int i = yStart - height; i <= yStart; i++) {
            for (int j = xStart; j <= xStart + length; j++) {
                Piece wall = new Wall(j, i);
                board.setPieceAtLocation(j, i, wall);
                displayPieces(wall);
            }
        }
    }

    private void movePlayer(int y, int x) throws Exception {
        Piece player = board.getPlayer();
        int currentY = player.getyCoordinate();
        int currentX = player.getxCoordinate();
        int destY = currentY + y;
        int destX = currentX + x;

        Piece destination = board.getPiece(destY, destX);

        while (destination == null) {

            board.setPieceAtLocation(destX, destY, player);

            erasePlayersLastPosition(currentX, currentY);

            board.setPieceAtLocation(currentX, currentY, null);

            player.setxCoordinate(destX);
            player.setyCoordinate(destY);
            displayPieces(player);

            currentX = destX;
            currentY = destY;
            destY = destY + y;
            destX = destX + x;

            destination = board.getPiece(destY, destX);
            if (y == 0){
                Thread.sleep(30);
            }
            else {
                Thread.sleep(60);
            }
        }
    }

    public void erasePlayersLastPosition(int xOld, int yOld) throws Exception {
        terminal.setCursorPosition(xOld, yOld);
        terminal.putCharacter(' ');
    }
}

