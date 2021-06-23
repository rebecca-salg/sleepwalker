import java.util.Arrays;

public class Board {
    protected Piece [][] pieces;
    private Player player;

    public Board(int xWidth, int yHeight) {
        pieces = new Piece[xWidth][yHeight];
        player = new Player(2, 12);
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public Player getPlayer() {
        return player;
    }

    public void createAndDisplayBoundary(){
        int x = 24;
        int y = 80;
        for(int row = 0; row < x; row++ ){
            pieces[row][0] = new Boundary();
            pieces[row][y-1] = new Boundary() ;
            if(row == 0 || row == x-1){
                for(int column =0; column < y; column++){
                    pieces[row][column] = new Boundary();
                }
            }
        }

        for (Piece[] s:
                pieces) {
            System.out.println(Arrays.toString(s));
        }
    }
}
