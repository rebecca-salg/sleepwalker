import com.googlecode.lanterna.TextColor;

public class Player extends Piece{
    private char playerIcon;
    private TextColor textColor;
    static int playernumer = 0;
/*
    public Player(){
        playernumer++;
    }*/

    public Player(int xPosition, int yPosition) {
        super.setxCoordinate(xPosition);
        super.setyCoordinate(yPosition);
        this.textColor = new TextColor.RGB(255, 255, 255);
        this.playerIcon = '■';
    }

    public TextColor getTextColor() {
        return textColor;
    }

    char displayPiece(){
        return playerIcon;
    }
}
