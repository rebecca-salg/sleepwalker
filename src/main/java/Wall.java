public class Wall extends Piece{
    private char icon;

    public Wall(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.icon = '█';
    }

    char displayPiece(){
        return icon;
    }
}

