package src.day6;

public enum Direction {
    UP(-1,0),
    RIGHT(0,1),
    DOWN(1,0),
    LEFT(0,-1);

    public final int i;
    public final int j;

    Direction(int i, int j){
        this.i = i;
        this.j = j;
    }
}
