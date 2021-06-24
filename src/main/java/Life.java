import com.googlecode.lanterna.TextColor;

public class Life extends Piece{
    private int lifeCount;
    private char lifeIcon;
    private TextColor lifeColor;

    public Life(int y, int x) {
        this.lifeCount = 5;
        this.lifeIcon = '\u2665';
        this.lifeColor = new TextColor.RGB(255, 20, 20);
        super.setxCoordinate(x);
        super.setyCoordinate(y);
    }

    public int getLifeCount() {
        return lifeCount;
    }

    @Override
    char displayPiece() {
        return lifeIcon;
    }


    @Override
    TextColor getTextColor() {
        return lifeColor;
    }

    public void resetLife() {
        this.lifeCount = 3;
    }

    public boolean isAlive() {
        if (lifeCount <= 0) return false;
        return true;
    }

    public void decreaseLife() {
        this.lifeCount--;
    }
}
