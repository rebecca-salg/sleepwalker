public abstract class Piece {
    private int xCoordinate;
    private int yCoordinate;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getCoordinate() {
        return yCoordinate;
    }

    public void setCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    abstract char displayPiece();

}
