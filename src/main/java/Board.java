import java.util.Arrays;

public class Board {
    protected Piece [][] pieces;
    private Player player;

    public Board(int xWidth, int yHeight) {
        pieces = new Piece[yHeight][xWidth];
        player = new Player(3, 12);
        createAndDisplayBoundary();
    }

    public Piece [][] getPieces() {
        return pieces;
    }

    public void setPieceAtLocation(int x, int y, Piece piece) {
        this.pieces[y][x] = piece;
    }


    public Player getPlayer() {
        return player;
    }

    public void createAndDisplayBoundary(){
        int x = 25;
        int y = 81;
        for(int row = 0; row < x; row++ ){
            pieces[row][0] = new Boundary();
            pieces[row][y-1] = new Boundary() ;
            if(row == 0 || row == x-1){
                for(int column =1; column < y; column++){
                    pieces[row][column] = new Boundary();
                }
            }
        }
        /*
        for (Piece[] s:
                pieces) {
            System.out.println(Arrays.toString(s));
        }*/
    }

    public Piece getPiece(int y, int x) {
        return pieces[y][x];
    }
}
