package src.day8;

import src.Utils;

import java.util.*;
import java.util.stream.Collectors;


public class Day8 {
    static int gridWidth;
    static int gridHeight;
    private static Map<String, List<Position>> antennaPosition = new HashMap<>();

    private static void prepareInput(){
        String[] inputLines = Utils.readInputFile("src/assets/day8Input").split("\n");
        gridHeight = inputLines.length;
        gridWidth = inputLines[0].length();
        for(int i = 0; i<inputLines.length; i++){
            String[] splittedLine = inputLines[i].split("");
            for(int j = 0; j< splittedLine.length; j++){
                if(!".".equals(splittedLine[j]) && !"#".equals(splittedLine[j])){
                    if(null != antennaPosition.get(splittedLine[j])){
                        antennaPosition.get(splittedLine[j]).add(new Position(i, j));
                    }else{
                        List<Position> positions = new ArrayList<>();
                        positions.add(new Position(i, j));
                        antennaPosition.put(splittedLine[j], positions);
                    }
                }
            }
        }
    }

    public static Set<Position> crunchAntennaList(List<Position> antennaPositions){
        Set<Position> antinodes = new HashSet<>();
        while(!antennaPositions.isEmpty()){
            Position currentPosition = antennaPositions.removeFirst();
            for(Position p : antennaPositions){
                antinodes.addAll(currentPosition.calculateRecursiveAntinodePositions(p));
            }
        }
        return antinodes;
    }

    public static int crunchGrid(){
        return antennaPosition.values().stream()
                .map(Day8::crunchAntennaList)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();
    }

    public static void main(String ...args){
        prepareInput();
        System.out.println(crunchGrid());
    }
}
