package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Day2 {

    private static int nbreSafeReports = 0;

    private static List<Integer[]> getInputReports(){
        List<Integer[]> reports = new ArrayList<>(1000);
        for(String line : Utils.readInputFile("src/assets/day2Input").split("\n")){
            String[] strReport = line.split(" ");
            Integer[] intReport = new Integer[strReport.length];
            for(int i = 0; i < strReport.length; i++){
                intReport[i] = Integer.parseInt(strReport[i]);
            }
            reports.add(intReport);
        }
        return reports;
    }

    private static boolean isReportSafe(Integer[] report){
        return isSimpleReportSafe(report) ||
                anySubReportSafe(report);
    }

    private static boolean isSimpleReportSafe(Integer[] report){
        boolean increasing = report[0] < report[1];
        try{
            Set<Integer> noTwin = Set.of(report);
        }catch(IllegalArgumentException e){
            return false;
        }
        for(int i = 0; i < report.length-1; i++){
            if((increasing != report[i] < report[i+1]) || (Math.abs(report[i]-report[i+1]) > 3)){
                return false;
            }
        }
        return true;
    }

    private static boolean anySubReportSafe(Integer[] report){
        for(int i = 0; i < report.length; i++){
            List<Integer> reportList = new ArrayList<>(List.of(report));
            reportList.remove(i);
            Integer[] subReport = new Integer[reportList.size()];
            subReport = reportList.toArray(subReport);
            if(isSimpleReportSafe(subReport)){
                return true;
            }
        }
        return false;
    }

    private static void sumSafeReports(){
        for(Integer[] report : getInputReports()){
            if(isReportSafe(report)){
                nbreSafeReports++;
            };
        }
    }

    public static void main(String ...args){
        sumSafeReports();
        System.out.println(nbreSafeReports);

    }


}
