public class Board {
    protected Piece [][] pieces;
    private Player player;

    public Board(int xWidth, int yHeight) {
        pieces = new Piece[xWidth][yHeight];
        player = new Player(2, 12);
    }

    public Piece [][] getPieces() {
        return pieces;
    }

    public void setPieceAtLocation(int x, int y, Piece piece) {
        this.pieces[x][y] = piece;
    }


    public Player getPlayer() {
        return player;
    }
}
