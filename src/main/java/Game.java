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

            case ArrowUp -> movePlayerUp();
            case ArrowDown -> movePlayerDown();
            case ArrowRight -> movePlayerRight();
            case ArrowLeft -> movePlayerLeft();

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
        Piece player = board.getPlayer();
        int xRight = player.getxCoordinate();
        int yRight = player.getyCoordinate();

        int destX = xRight + 1;

        //Kolla framåt. Skriv ej över andra objekt. "Krocka"! -Done!
        Piece destination = board.getPiece(yRight, destX);

        while (destination == null) {
            //Loopa rörelse
            board.setPieceAtLocation(destX, yRight, player);
            erasePlayersLastPosition(xRight, yRight); //Raderar inte sig själv

            board.setPieceAtLocation(xRight, yRight, null);

            player.setxCoordinate(destX);
            displayPieces(player);


            xRight = destX;
            destX += 1;

            destination = board.getPiece(yRight, destX);
            Thread.sleep(500);
        }
    }

    private void movePlayerLeft() throws Exception {
        Piece position = board.getPlayer();
        int xLeft = position.getxCoordinate();
        int yLeft = position.getyCoordinate();
        erasePlayersLastPosition(xLeft, yLeft);
        position.setxCoordinate(xLeft - 1);
    }

    public void erasePlayersLastPosition(int xOld, int yOld) throws Exception {
        terminal.setCursorPosition(xOld, yOld);
        terminal.putCharacter(' ');
    }
}

