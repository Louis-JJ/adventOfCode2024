package src;

import java.util.*;

public class Day5 {

    private static String[] rawInputRules;
    private static String[] rawInputUpdates;
    private static Map<String, List<String>> rulesMap;

    private static void prepareInput(){
        rawInputRules = Utils.readInputFile("src/assets/day5InputRules").split("\\n");
        rawInputUpdates = Utils.readInputFile("src/assets/day5InputUpdates").split("\\n");
        Map<String, List<String>> rules = new HashMap<>();
        for(String rule : rawInputRules){
            String[] splittedRule = rule.split("\\|");
            if(!rules.containsKey(splittedRule[0])){
                rules.put(splittedRule[0], new ArrayList<>());
            }
            rules.get(splittedRule[0]).add(splittedRule[1]);
        }
        rulesMap = rules;
    }

    private static boolean isUpdateCompliant(String[] arrayToCheck){
        for(int i = 1; i < arrayToCheck.length; i++){
            int currentIndex = i;
            String valueToCheck = arrayToCheck[i];
            String currentValue = arrayToCheck[i-1];
            List<String> currentRule = rulesMap.get(valueToCheck);
            if(null == currentRule){
                continue;
            }
            while(currentIndex >= 0){
                if(currentRule.contains(currentValue)){
                    return false;
                }
                currentIndex--;
            }
        }
        return true;
    }

    private static List<String> getSpecificUpdates(boolean compliant, String[] updateLot){
        return Arrays.stream(updateLot)
                .filter(update -> compliant == isUpdateCompliant(update.split(",")))
                .toList();
    }

    private static int sumUpdatesMiddleValues(boolean compliant){
        return getSpecificUpdates(compliant, rawInputUpdates).stream()
                .map(str -> compliant ? str.split(",") : enforceCompliance(str))
                .map(strArray -> Integer.parseInt(strArray[(strArray.length-1)/2]))
                .reduce(Integer::sum).get();
    }

    private static Map<String,List<String>> getRulesInsideUpdate(String update){
        Map<String,List<String>> compactRules = new HashMap<>();
        List<String> updateToList = Arrays.stream(update.split(",")).toList();
        for(String value : updateToList){
            compactRules.put(value,
                    rulesMap.get(value).stream()
                    .filter(updateToList::contains).toList());
        }
        return compactRules;
    }

    private static String[] enforceCompliance(String nonCompliantUpdate){
        String[] splittedNonCompliantArray = nonCompliantUpdate.split(",");
        Map<String,List<String>> compactRules = getRulesInsideUpdate(nonCompliantUpdate);

        String[] compliantUpdateArray = new String[splittedNonCompliantArray.length];

        for(String value : splittedNonCompliantArray){
            int newValueIndex = (int) compactRules.values().stream().filter(rule -> rule.contains(value)).count();
            while(null != compliantUpdateArray[newValueIndex]){
                newValueIndex++;
            }
            compliantUpdateArray[newValueIndex] = value;
        }
        return compliantUpdateArray;
    }

    public static void main(String[] args){
        prepareInput();
        System.out.println(sumUpdatesMiddleValues(true));
        System.out.println(sumUpdatesMiddleValues(false));
    }
}
