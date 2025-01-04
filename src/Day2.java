import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    List<List<Integer>> reports = new ArrayList<>();

    public void start(){
        read();
        findSafeReports();
    }

    private void findSafeReports() {
        int counter = 0;
        for (List<Integer> report : reports){
            if (isSafe(report)){
                counter++;
            }
        }
        System.out.println(counter);
    }

    private boolean isSafe(List<Integer> report) {
        if (report.get(0) > report.get(1)){
            return isDescending(report);
        }else {
            return isAscending(report);
        }
    }

    private boolean isAscending(List<Integer> report) {
        boolean dampener = true;
        int previous = report.getFirst();
        for (int i = 1; i < report.size(); i++) {
            int current = report.get(i);
            int delta = Math.abs(current - previous);
            if (current > previous && delta >= 1 && delta <= 3){
                previous = current;
                continue;
            }
            if (dampener){
                dampener = false;
            }else {
                return false;
            }
        }
        return true;
    }

    private boolean isDescending(List<Integer> report) {
        boolean dampener = true;
        int previous = report.getFirst();
        for (int i = 1; i < report.size(); i++) {
            int current = report.get(i);
            int delta = Math.abs(current - previous);
            if (current < previous && delta >= 1 && delta <= 3){
                previous = current;
                continue;
            }
            if (dampener){
                dampener = false;
            }else {
                return false;
            }
        }
        return true;
    }

    private void read() {
        Scanner s = null;
        try {
            s = new Scanner(new File("src/inputs/day2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pattern pattern = Pattern.compile("(\\d+)");
        while(s.hasNextLine()){
            String line = s.nextLine();
            Matcher matcher = pattern.matcher(line);
            List<Integer> temp = new ArrayList<>();
            while (matcher.find()){
                temp.add(Integer.parseInt(matcher.group()));
            }
            reports.add(temp);
        }

    }
}
