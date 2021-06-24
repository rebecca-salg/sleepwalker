public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int addOnePoint(){
        return this.score++;
    }

    public int removeSteps() {
        this.score = score - 3;
        return this.score;

    }
}
