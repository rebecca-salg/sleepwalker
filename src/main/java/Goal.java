public class Goal extends Piece {

    private char goalIcon;

    public Goal(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.goalIcon = 'G';
    }


    @Override
    char displayPiece() {
        return goalIcon;
    }
}
