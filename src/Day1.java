import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    public void start() {
        read();
        findDiscrepancies();
        calculateSimilarity();
    }

    private void calculateSimilarity() {
        int total = 0;
        for (Integer i : right) {
            if (left.contains(i))
                total += i;
        }
        System.out.println(total);
    }

    private void findDiscrepancies() {
        Collections.sort(left);
        Collections.sort(right);
        int total = 0;
        for (int i = 0; i < left.size(); i++) {
            total += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(total);
    }

    private void read() {
        Scanner s = null;
        try {
            s = new Scanner(new File("src/inputs/day1.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(s.hasNextInt()){
            left.add(s.nextInt());
            right.add(s.nextInt());
        }
    }
}
