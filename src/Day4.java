package src;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    private static String[] rawInputLines;
    private static String[][] rawInputTable;

    private static void prepareInput(){
        rawInputLines = Utils.readInputFile("src/assets/day4Input").split("\\n");
        rawInputTable = new String[140][140];
        for(int i = 0; i < rawInputLines.length; i++){
            rawInputTable[i] = rawInputLines[i].split("");
        }
    }

    private enum Direction{
        UP(-1,0),
        UR(-1,1),
        R(0,1),
        BR(1,1),
        B(1,0),
        BL(1,-1),
        L(0,-1),
        UL(-1,-1);

        public final int i;
        public final int j;

        Direction(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    private static String computeNextTarget(String nextChar){
        return "XMAS".substring("XMAS".indexOf(nextChar)+1, "XMAS".indexOf(nextChar)+2);
    }

    private static boolean isNextCharOk(int i, int j, String nextChar){
        try{
            return nextChar.equals(rawInputTable[i][j]);
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    private static boolean checkNextPosition(int startingI, int startingJ, String currentTarget, Direction direction){
        if(isNextCharOk(startingI+direction.i, startingJ+direction.j, currentTarget)){
            if("S".equals(currentTarget)){
                return true;
            }
            return checkNextPosition(startingI+direction.i, startingJ+direction.j, computeNextTarget(currentTarget), direction);
        }
        return false;
    }

    private static int findXMas(){
        int xmas = 0;
        for(int i = 0; i < rawInputTable.length; i++){
            for(int j = 0; j < rawInputLines[0].length(); j++){
                String currentString = rawInputTable[i][j];
                if("X".equals(currentString)){
                    for(Direction dir : Direction.values()){
                        if(checkNextPosition(i, j, "M", dir)){
                            xmas++;
                        }
                    }
                }
            }
        }
        return xmas;
    }

    private static int findXShapedMas(){
        int xShapedMas = 0;
        for(int i = 0; i < rawInputTable.length; i++){
            for(int j = 0; j < rawInputLines[0].length(); j++){
                String currentString = rawInputTable[i][j];
                if("A".equals(currentString)){
                    try{
                        String ULDiag = rawInputTable[i-1][j-1];
                        String URDiag = rawInputTable[i-1][j+1];
                        String LLDiag = rawInputTable[i+1][j-1];
                        String LRDiag = rawInputTable[i+1][j+1];
                        if("M".equals(ULDiag) && "S".equals(LRDiag)){
                            if(("M".equals(LLDiag) && "S".equals(URDiag)) || ("S".equals(LLDiag) && "M".equals(URDiag))){
                                xShapedMas++;
                            }
                        }
                        else if("S".equals(ULDiag) && "M".equals(LRDiag)){
                            if(("M".equals(LLDiag) && "S".equals(URDiag)) || ("S".equals(LLDiag) && "M".equals(URDiag))){
                                xShapedMas++;
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
//                        do nothing
                    }
                }
            }
        }
        return xShapedMas;
    }

    public static void main(String[] args){
        prepareInput();
        System.out.println(findXMas());
        System.out.println(findXShapedMas());
    }
}
