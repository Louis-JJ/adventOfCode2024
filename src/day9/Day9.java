package src.day9;

import src.Utils;

import java.util.*;

public class Day9 {

    private static final String input = Utils.readInputFile("src/assets/day9Input");
    private static Map<String, List<Integer>> positions= new LinkedHashMap<>();
    private static int currentIndex = 0;

    private static void populatePostions(){
        positions.put(".", new ArrayList<>());
        for(int i = 0; i<input.length(); i++){
            int nbreRepeat = Integer.parseInt(String.valueOf(input.charAt(i)));
            if(i%2 == 0) {
                String key = String.valueOf(i/2);
                List<Integer> value = new ArrayList<>(nbreRepeat);
                for(int j = 0; j<nbreRepeat; j++) {
                    value.add(currentIndex+j);
                }
                positions.put(key, value);
            } else {
                List<Integer> value = positions.get(".");
                for(int j = 0; j<nbreRepeat; j++) {
                    value.add(currentIndex+j);
                }
            }
            currentIndex += nbreRepeat;
        }
    }

//    FOR PART 1
    private static void exchangePosition(String digit){
        List<Integer> points = positions.get(".");
        List<Integer> digits = positions.get(digit);
        int pointPosition = points.getFirst();
        int digitPosition = digits.getLast();
        while(digitPosition > pointPosition){
            points.removeFirst();
            points.add(digitPosition);
            digits.removeLast();
            digits.addFirst(pointPosition);
            pointPosition = points.getFirst();
            digitPosition = digits.getLast();
        }
    }

    private static long compactionPart1(){
        positions.keySet().stream().filter(k -> !".".equals(k)).toList().reversed().forEach(Day9::exchangePosition);
        return positions.entrySet().stream()
                .filter(entry -> !".".equals(entry.getKey()))
                .map(entry -> entry.getValue().stream()
                        .map(v -> v*Long.parseLong(entry.getKey()))
                        .reduce(Long::sum).get())
                .reduce(Long::sum)
                .get();
    }

//    FOR PART 2
    private static int getFreeRange(int range){
        List<Integer> points = positions.get(".");
        int currentSize = 1;
        int startIndex = 0;
        for(int i = 0; i<points.size()-1; i++){
            if(currentSize == range){
                return points.get(startIndex);
            }else if(points.get(i+1)-points.get(i) == 1){
                currentSize++;
            }else{
                startIndex = i+1;
                currentSize = 1;
            }
        }
        return -1;
    }

    private static void exchangeFilePosition(String digit){
        List<Integer> points = positions.get(".");
        List<Integer> digits = positions.get(digit);
        int fileSize = digits.size();
        int startIndex = getFreeRange(fileSize);
        if(-1 != startIndex && startIndex < digits.getFirst()){
            for(int i = 0; i < digits.size(); i++){
                points.add(digits.get(i));
                points.remove((Integer) startIndex);
                digits.remove(i);
                digits.addFirst(startIndex);
                startIndex++;
            }
        }
    }

    private static long compactionPart2(){
        positions.keySet().stream().filter(k -> !".".equals(k)).toList().reversed().forEach(Day9::exchangeFilePosition);
        return positions.entrySet().stream()
                .filter(entry -> !".".equals(entry.getKey()))
                .map(entry -> entry.getValue().stream()
                        .map(v -> v*Long.parseLong(entry.getKey()))
                        .reduce(Long::sum).get())
                .reduce(Long::sum)
                .get();
    }

    public static void main(String ...args){
        populatePostions();
        System.out.print(compactionPart2());
    }

}
