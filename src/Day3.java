package src;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    private static Pattern multiplicationRegex = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

    private static String getOnlyDos(){
        String rawInput = Utils.readInputFile("src/assets/day3Input");
        return Stream.of(rawInput.split("do\\(\\)")).map(str -> str.split("don't\\(\\)")[0]).collect(Collectors.joining());
    }

    private static Integer multiply(String operation){
        String[] splitted = operation.substring(4, operation.indexOf(')')).split(",");
        Integer operande1 = Integer.parseInt(splitted[0]);
        Integer operande2 = Integer.parseInt(splitted[1]);
        return operande1*operande2;
    }

    private static Integer getAndComputeMultiplications(){
        Matcher matcher = multiplicationRegex.matcher(getOnlyDos());
        return matcher.results().map(MatchResult::group).map(Day3::multiply).reduce(Integer::sum).get();
    }

    public static void main(String ...args){
        System.out.println(getAndComputeMultiplications());
    }
}
