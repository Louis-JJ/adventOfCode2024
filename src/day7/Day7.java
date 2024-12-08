package src.day7;

import src.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day7 {

    private static List<Enigme> enigmes;

    private static void prepareInput(){
        String[] inputLines = Utils.readInputFile("src/assets/day7Input").split("\n");
        enigmes = new ArrayList<>(inputLines.length);
        for(String line : inputLines){
            enigmes.add(new Enigme(line));
        }
    }

    private static long sumValidResults(){
        return enigmes.stream()
                .map(Enigme::isValid)
                .reduce(Long::sum).get();
    }

    public static void main(String ...args){
        prepareInput();
        System.out.println(sumValidResults());
    }

}
