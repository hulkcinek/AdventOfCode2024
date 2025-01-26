import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    List<ClawMachine> machines = new ArrayList<>();

    public void start() {
        read();
        findTheTotalCost();
    }

    private void findTheTotalCost() {
        long total = machines.stream()
                .filter(ClawMachine::isSolvable)
                .mapToLong(m -> (3 * m.getSolutionA() + m.getSolutionB()))
                .sum();
        System.out.println(total);
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day13.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        while (s.hasNextLine()){
            sb.append(s.nextLine()).append("\n");
        }

        String regex =  "Button A: X\\+(\\d+), Y\\+(\\d+)\\s+" +
                        "Button B: X\\+(\\d+), Y\\+(\\d+)\\s+" +
                        "Prize: X=(\\d+), Y=(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sb.toString());

        while (matcher.find()){
            long x1 = Long.parseLong(matcher.group(1));
            long y1 = Long.parseLong(matcher.group(2));
            long x2 = Long.parseLong(matcher.group(3));
            long y2 = Long.parseLong(matcher.group(4));
            long c1 = Long.parseLong(matcher.group(5));
            long c2 = Long.parseLong(matcher.group(6));
            machines.add(new ClawMachine(x1, y1, x2, y2, c1, c2));
        }

    }
}

class ClawMachine {
    long x1;
    long y1;
    long x2;
    long y2;
    long c1;
    long c2;

    /*equations
       x1 * a + x2 * b = c1
       y1 * a + y2 * b = c2
    */

    boolean solvable = true;
    long solutionA;
    long solutionB;

    public ClawMachine(long x1, long y1, long x2, long y2, long c1, long c2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c1 = c1 + 10000000000000L;
        this.c2 = c2 + 10000000000000L;

        solve();
    }

    private void solve() { //Cramer's Rule
        long deltaA = c1 * y2 - c2 * x2;
        long deltaB = x1 * c2 - y1 * c1;
        long delta = x1 * y2 - x2 * y1;

        if (delta == 0 || deltaA % delta != 0 || (deltaB % delta) != 0){
            solvable = false;
            return;
        }

        solutionA = (deltaA / delta);
        solutionB = (deltaB / delta);

//        System.out.println(solutionA + " " + solutionB);
    }

    public boolean isSolvable() {
        return solvable;
    }

    public long getSolutionA() {
        return solutionA;
    }

    public long getSolutionB() {
        return solutionB;
    }
}

