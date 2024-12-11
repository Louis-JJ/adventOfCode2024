package src.day10;

import src.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
    private static Map<Integer, List<Coord>> carte = new HashMap<>();

    private static void prepareInput(){
        String[] input = Utils.readInputFile("src/assets/day10Input").split("\\n");
        for(int i = 0; i<input.length; i++){
            String[] splitted = input[i].split("");
            for(int j = 0; j<splitted.length; j++){
                int lvl = Integer.parseInt(splitted[j]);
                carte.computeIfAbsent(lvl, k -> new ArrayList<>());
                carte.get(lvl).add(new Coord(i, j, lvl));
            }
        }
    }

    public static long sumCardinalities(List<Coord> paths){
        return paths.stream()
                .map(Day10::countEnds)
                .reduce(Long::sum)
                .get();
    }

    // PART 1 : DISTINCT ENDS
    private static long countDistinctEnds(Coord start){
        int currentLvl = 1;
        List<Coord> currentLvlCoord = start.getNextLvl(carte);
        while(currentLvl < 9){
            currentLvlCoord = currentLvlCoord.stream()
                    .flatMap(coord -> coord.getNextLvl(carte).stream())
                    .distinct()
                    .toList();
            currentLvl++;
        }
        return currentLvlCoord.stream().distinct().count();
    }

    // PART 2 : DISTINCT PATHS, SO END DUPLICATES ALLOWED
    private static long countEnds(Coord start){
        int currentLvl = 1;
        List<Coord> currentLvlCoord = start.getNextLvl(carte);
        while(currentLvl < 9){
            currentLvlCoord = currentLvlCoord.stream()
                    .flatMap(coord -> coord.getNextLvl(carte).stream())
                    .toList();
            currentLvl++;
        }
        return currentLvlCoord.size();
    }

    public static void main(String ...args){
       prepareInput();
       System.out.println(sumCardinalities(carte.get(0)));
    }
}
