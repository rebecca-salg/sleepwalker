public class Player extends Piece{
    private char playerIcon;
    static int playernumer = 0;
/*
    public Player(){
        playernumer++;
    }*/

    public Player(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.playerIcon = 'P';
    }

    char displayPiece(){
        return playerIcon;
    }
}
