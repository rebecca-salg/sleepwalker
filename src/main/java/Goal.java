import com.googlecode.lanterna.TextColor;

public class Goal extends Piece {

    private char goalIcon;
    private TextColor textColor;

    public Goal(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.textColor = new TextColor.RGB(0, 200, 0);
        this.goalIcon = '■';
    }

    public TextColor getTextColor() {
        return textColor;
    }

    @Override
    char displayPiece() {
        return goalIcon;
    }
}
