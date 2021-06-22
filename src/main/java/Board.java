public class Board {
    protected String [][] pieces;
    private Player player;

    public Board(int xWidth, int yHeight) {
        pieces = new String[xWidth][yHeight];
        player = new Player(2, 12);
    }

    public String[][] getPieces() {
        return pieces;
    }

    public void setPieces(String[][] pieces) {
        this.pieces = pieces;
    }

    public Player getPlayer() {
        return player;
    }
}
