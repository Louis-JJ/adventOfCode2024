package src.day6;

import java.util.List;
import java.util.stream.Collectors;

public class Day6Computation{
    public boolean loop;
    private boolean runningOver;
    private List<String> guardedPositions;
    private String[][] carte;
    private int[] newPosition;
    private Direction newDirection;
    public String position;

    public Day6Computation(List<String> guardedPositions, String[][] carte, String position){
        this.guardedPositions = guardedPositions;
        this.carte = carte;
        this.position = position;
    }

    public void placeOrRemoveObstacle(String position, boolean place){
        String[] positionArray = position.split(",");
        int i = Integer.parseInt(positionArray[0]);
        int j = Integer.parseInt(positionArray[1]);
        carte[i][j] = place ? "#" : ".";
    }

    public Direction computeNextDirection(Direction currentDirection){
        return switch (currentDirection){
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case RIGHT -> Direction.DOWN;
            case LEFT -> Direction.UP;
        };
    }

    public void moveGuard(int[] currentPosition, Direction currentDirection, List<String> pathway){
        int nextI = currentPosition[0]+currentDirection.i;
        int nextJ = currentPosition[1]+currentDirection.j;
        try{
            if(carte[nextI][nextJ].equals("#")){
                newDirection = computeNextDirection(currentDirection);
                newPosition = currentPosition;
            } else {
                newPosition = new int[]{nextI,nextJ};
                loop = guardedPositions.stream().filter(str -> str.equals(nextI+","+nextJ)).count() >= 3;
                pathway.add(nextI+","+nextJ);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            runningOver = true;
        }
    }

    public List<String> computeGuardedPositions(){
        while(!runningOver && !loop){
            if(null == newPosition){
                if(newDirection == null){
                    newDirection = Day6.startingDirection;
                    moveGuard(Day6.startingPosition, Day6.startingDirection, guardedPositions);
                } else {
                    moveGuard(Day6.startingPosition, newDirection, guardedPositions);
                }
            }else{
                moveGuard(newPosition, newDirection, guardedPositions);
            }
        }
        return this.guardedPositions.stream().distinct().collect(Collectors.toList());
    }

    public void computeLoops(){
        placeOrRemoveObstacle(position, true);
        computeGuardedPositions();
        if(loop){
            Day6.loop++;
            System.out.println(Day6.loop);
        }
        placeOrRemoveObstacle(position, false);
    }
}
