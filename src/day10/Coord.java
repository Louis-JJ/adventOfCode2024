package src.day10;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Coord {
    public int i;
    public int j;
    public int lvl;

    public Coord(int i, int j, int lvl){
        this.i = i;
        this.j = j;
        this.lvl = lvl;
    }

    public boolean isNextTo(Coord coord){
        return (this.i == coord.i+1 && this.j == coord.j) ||
                (this.i == coord.i-1 && this.j == coord.j) ||
                (this.j == coord.j+1 && this.i == coord.i) ||
                (this.j == coord.j-1 && this.i == coord.i);
    }

    public List<Coord> getNextLvl(Map<Integer, List<Coord>> carte){
        return this.lvl != 9 ?
                carte.get(this.lvl+1).stream().filter(this::isNextTo).toList() :
                Collections.emptyList();
    }
}
