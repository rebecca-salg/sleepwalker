public class Board {

    protected String [][] pieces;

    public Board(int xWidth, int yHeight) {
        pieces = new String[xWidth][yHeight];
    }

    public String[][] getPieces() {
        return pieces;
    }

    public void setPieces(String[][] pieces) {
        this.pieces = pieces;
    }
}
