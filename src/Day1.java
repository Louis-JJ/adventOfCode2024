package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 {

    private static List<Integer>[] getInputLists(){
        //creating lists
        List<Integer> liste1 = new ArrayList<>(1000);
        List<Integer> liste2 = new ArrayList<>(1000);

        //populating lists
        for(String line : Utils.readInputFile("src/assets/day1Input").split("\n")){
            String[] splitted = line.split(" ");
            liste1.add(Integer.parseInt(splitted[0]));
            liste2.add(Integer.parseInt(splitted[3]));
        }
        return new List[]{liste1, liste2};
    }

    private static List<Integer>[] getSortedInputLists(){
        List<Integer>[] listes = getInputLists();
        listes[0].sort(Integer::compare);
        listes[1].sort(Integer::compare);
        return listes;
    }

    private static Integer calculateTotalDistance(){
        List<Integer>[] sortedInputLists = getSortedInputLists();
        Integer totalDistance = IntStream.range(0, 1000)
                .map(index -> Math.abs(sortedInputLists[0].get(index)-sortedInputLists[1].get(index)))
                .reduce(Integer::sum)
                .getAsInt();
        return totalDistance;
    }

    private static long computeIncrease(Integer toSearch, List<Integer> toBeSearched){
        return toSearch * toBeSearched.stream().filter(value -> value.equals(toSearch)).count();
    }

    private static long calculateSimilarity(){
        List<Integer>[] listes = getInputLists();
        return listes[0].stream().map(value -> computeIncrease(value, listes[1])).reduce(Long::sum).get();
    }

    public static void main(String ...args){
        System.out.println(calculateSimilarity());
    }


}
