import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Game {
    private Board board;
    private Terminal terminal;
    private Life life;
    private Score score;

    public Game() throws Exception {
        this.board = new Board(81, 25);
        this.terminal = initiateTerminal();
        this.score = new Score();
        this.life = new Life(1, 60);
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

    public Score getScore() {
        return score;
    }

    public void displayPieces(Piece piece) throws IOException {
        terminal.setCursorPosition(piece.getxCoordinate(), piece.getyCoordinate());
        terminal.setForegroundColor(piece.getTextColor());
        terminal.putCharacter(piece.displayPiece());
        terminal.flush();
    }

    public void displayPieces(int y, int x, Piece piece) throws IOException {
        terminal.setCursorPosition(x, y);
        terminal.setForegroundColor(piece.getTextColor());
        terminal.putCharacter(piece.displayPiece());
        terminal.flush();
    }

    public void displayPieces(int y, int x, char c) throws IOException {
        terminal.setCursorPosition(x, y);
        terminal.setForegroundColor(new TextColor.RGB(0, 0, 0));
        terminal.putCharacter(c);
        terminal.flush();
    }

    public void movePlayer(KeyType keyInput) throws Exception {
        switch (keyInput) {

            case ArrowUp -> movePlayer(-1, 0);
            case ArrowDown -> movePlayer(1, 0);
            case ArrowRight -> movePlayer(0, 1);
            case ArrowLeft -> movePlayer(0, -1);

        }
    }

    public void createLevel() throws Exception {
        //drawShape(2, 12, 0, 0); //playerposition
        drawShape(3, 2, 5, 0);
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

        Goal goal = new Goal(61, 20);
        board.setPieceAtLocation(61, 20, goal);
        Elixir elixir1 = new Elixir(24, 19);
        Elixir elixir2 = new Elixir(72, 15);
        Elixir elixir3 = new Elixir(76, 10);
        displayPieces(goal);
        board.setPieceAtLocation(24,19, elixir1);
        board.setPieceAtLocation(24,19, elixir2);
        board.setPieceAtLocation(24,19, elixir3);
        displayPieces(elixir1);
        displayPieces(elixir2);
        displayPieces(elixir3);
        displayPieces(board.getPlayer());
        //Goal
        displayLife();
        displayScore();
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

        while (destination == null || destination instanceof Elixir) {

            board.setPieceAtLocation(destX, destY, player);

            erasePlayersLastPosition(currentX, currentY);

            board.setPieceAtLocation(currentX, currentY, null);

            player.setxCoordinate(destX);
            player.setyCoordinate(destY);
            displayPieces(player);

            currentX = destX;
            currentY = destY;
            destY += y;
            destX += x;

            destination = board.getPiece(destY, destX);
            if (y == 0) {
                Thread.sleep(30);
            } else {
                Thread.sleep(60);
            }
        }
        if (board.getPiece(destY, destX) instanceof Goal) {
            winMethod();
        }

        if (board.getPiece(destY, destX) instanceof Elixir) {
            getScore().removeSteps();
//            score.removeSteps();
        }

        if (board.getPiece(destY, destX) instanceof Boundary) {
            life.decreaseLife();

            displayLife();
            if (!life.isAlive()) gameOver();
            //remove icon and object from current location
            erasePlayersLastPosition(currentX, currentY);
            board.setPieceAtLocation(currentX, currentY, null);

            //update player info
            player.setxCoordinate(2);
            player.setyCoordinate(12);

            //set player in the array and display on terminal
            board.setPieceAtLocation(2, 12, player);
            displayPieces(player);
        }
    }

    public void erasePlayersLastPosition(int xOld, int yOld) throws Exception {
        terminal.setCursorPosition(xOld, yOld);
        terminal.setForegroundColor(new TextColor.RGB(100, 100, 100));
        terminal.putCharacter('⠔');
    }

    public void displayScore() throws IOException {
        terminal.setCursorPosition(1, 1);
        terminal.setForegroundColor(new TextColor.RGB(255, 255, 0));
        String string = "Moves: " + score.getScore();
        for (char c : string.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }

    public void gameOver() throws IOException {
        terminal.close();
        System.exit(-1);

        /*terminal.setCursorPosition(12,30);
        String gameOver = "GAME OVER :(";
        for (char c : gameOver.toCharArray()) {
            terminal.putCharacter(c);
        }*/
    }

    public void displayLife() throws Exception {
        for (int i = 0; i < 5; i++) {
            if (i < life.getLifeCount()) {
                displayPieces(life.getyCoordinate(), life.getxCoordinate() + i, life);
            } else {
                displayPieces(life.getyCoordinate(), life.getxCoordinate() + i, ' ');
            }
        }
    }

    public void winMethod() throws IOException {
        terminal.setCursorPosition(12, 30);
        String winMessage = "Woohoo! You made it to the exam!";
        for (char c : winMessage.toCharArray()) {
            terminal.putCharacter(c);
        }
    }
//        public int removeSteps() {
//        return score.getScore() -= 3;
//    }
}

