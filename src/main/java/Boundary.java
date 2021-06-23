public class Boundary extends Piece{
    private char boundaryIcon;

    public Boundary() {
        this.boundaryIcon = 'B';
    }

    @Override
    char displayPiece() {
        return boundaryIcon;
    }
}
