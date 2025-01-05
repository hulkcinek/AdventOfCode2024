import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    List<Rule> ruleList = new ArrayList<>();
    List<List<Integer>> updates = new ArrayList<>();

    public void start() {
        read();
        findCorrectUpdates();
    }

    private void findCorrectUpdates() {
        int totalMiddle = 0;
        for (List<Integer> update : updates){
            /* Part1:
            if(isCorrect(update)) {
                totalMiddle += getMiddle(update);
            }*/

            if (isCorrect(update)) continue;

            while(!isCorrect(update)) {
                correctTheUpdate(update);
            }

            totalMiddle += getMiddle(update);
        }
        System.out.println(totalMiddle);
    }

    private void correctTheUpdate(List<Integer> update) {
        for (Rule rule : ruleList){
            if (!rule.isFollowed(update)) {
                int i = update.indexOf(rule.before);
                int j = update.indexOf(rule.after);
                Collections.swap(update, i, j);
            }
        }
    }

    private boolean isCorrect(List<Integer> update) {
        for (Rule rule : ruleList){
            if (!rule.isFollowed(update)) return false;
        }
        return true;
    }

    private int getMiddle(List<Integer> update) {
        int middleIndex = update.size()/2;
        return update.get(middleIndex);
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day5.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(s.hasNextLine()){
            String line = s.nextLine();

            if (line.isEmpty()) break;

            String[] parts = line.split("\\|");
            ruleList.add(new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        while(s.hasNextLine()){
            String line = s.nextLine();

            String[] parts = line.split(",");
            List<Integer> update = new ArrayList<>();
            for (String n : parts){
                update.add(Integer.parseInt(n));
            }
            updates.add(update);
        }
    }
}

class Rule {

    Integer before;
    Integer after;

    public Rule(int before, int after) {
        this.before = before;
        this.after = after;
    }

    public boolean isFollowed(List<Integer> list){
        int indexBefore = list.indexOf(before);
        int indexAfter = list.indexOf(after);

        if (indexBefore == -1 || indexAfter == -1) return true;

        return indexBefore < indexAfter;
    }
}

