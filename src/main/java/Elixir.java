import com.googlecode.lanterna.TextColor;

public class Elixir extends Piece {

    private char elixirIcon;
    private TextColor textColor;

    public Elixir(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.textColor = new TextColor.RGB(0, 200, 0);
        this.elixirIcon = '\u2615';
    }

    public TextColor getTextColor() {
        return textColor;
    }

    @Override
    char displayPiece() {
        return elixirIcon;
    }
}