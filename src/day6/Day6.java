package src.day6;

import src.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    private static final String[] rawInputLines = Utils.readInputFile("src/assets/day6Input").split("\n");
    private static String[][] carte = new String[rawInputLines.length][rawInputLines[0].length()];
    private static List<String> initialPath = new ArrayList<>();
    public static int[] startingPosition = new int[2];
    public static Direction startingDirection = null;
    public static int loop = 0;

    private Day6(){

    }

    private static void prepareInput(){
        for(int i = 0; i< rawInputLines.length; i++){
            carte[i] = rawInputLines[i].split("");
            for(int j = 0; j < carte[i].length; j++){
                switch (carte[i][j]){
                    case "<":
                        startingDirection = Direction.LEFT;
                        startingPosition = new int[]{i,j};
                        initialPath.add(i+","+j);
                        break;
                    case ">":
                        startingDirection = Direction.RIGHT;
                        startingPosition = new int[]{i,j};
                        initialPath.add(i+","+j);
                        break;
                    case "v":
                        startingDirection = Direction.DOWN;
                        startingPosition = new int[]{i,j};
                        initialPath.add(i+","+j);
                        break;
                    case "^":
                        startingDirection = Direction.UP;
                        startingPosition = new int[]{i,j};
                        initialPath.add(i+","+j);
                        break;
                    default:
                }
            }
        }
    }

    public static String[][] copy(String[][] src) {
        if (src == null) {
            return null;
        }

        return Arrays.stream(src).map(String[]::clone).toArray(String[][]::new);
    }

    public static void main(String[] args){
        prepareInput();
        Day6Computation d6 = new Day6Computation(new ArrayList<>(initialPath), copy(carte), null);
        initialPath = d6.computeGuardedPositions();
        initialPath.removeFirst();
        initialPath.parallelStream()
                .map(position -> new Day6Computation(new ArrayList<>(), copy(carte), position))
                .forEach(Day6Computation::computeLoops);
        System.out.println(loop);
    }
}
