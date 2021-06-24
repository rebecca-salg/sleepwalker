import com.googlecode.lanterna.TextColor;

public class Wall extends Piece{
    private char icon;
    private TextColor textColor;

    public Wall(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.textColor = new TextColor.RGB(165,42,42);
        this.icon = '█';
    }

    public TextColor getTextColor() {
        return textColor;
    }

    char displayPiece(){
        return icon;
    }

}

