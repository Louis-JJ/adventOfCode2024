package src.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    public int i;
    public int j;

    public Position(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int[] calculateDelta(Position p){
        return new int[]{this.i - p.i, this.j - p.j};
    }

    public boolean isPositionValid(){
        return this.i >= 0 && this.i < Day8.gridWidth && this.j >= 0 && this.j < Day8.gridHeight;
    }

    public List<Position> calculateAntinodePositions(Position p){
        int[] delta = calculateDelta(p);
        List<Position> antinodes = new ArrayList<>();
        antinodes.add(this);
        antinodes.add(p);
        Position antinode1 = new Position(this.i + delta[0], this.j + delta[1]);
        Position antinode2 = new Position(p.i - delta[0], p.j - delta[1]);
        return List.of(antinode1, antinode2);
    }

    public List<Position> calculateRecursiveAntinodePositions(Position p){
        int[] delta = calculateDelta(p);
        List<Position> antinodes = new ArrayList<>();
        Position currentAntinode = new Position(this.i, this.j);
        while(currentAntinode.isPositionValid()){
            antinodes.add(currentAntinode);
            currentAntinode = new Position(currentAntinode.i + delta[0], currentAntinode.j + delta[1]);
        }
        currentAntinode = new Position(p.i, p.j);
        while(currentAntinode.isPositionValid()){
            antinodes.add(currentAntinode);
            currentAntinode = new Position(currentAntinode.i - delta[0], currentAntinode.j - delta[1]);
        }
        return antinodes;
    }

    public String toString(){
        return this.i+", "+this.j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return i == position.i && j == position.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
