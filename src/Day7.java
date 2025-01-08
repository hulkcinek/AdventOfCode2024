import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {

    List<Equation> equations = new ArrayList<>();

    public void start() {
        read();
        findPossibleEquations();
    }

    private void findPossibleEquations() {
        long total = 0;
        for (Equation equation : equations){
            if(isPossible(equation))
                total += equation.expectedValue;
        }
        System.out.println(total);
    }

    private boolean isPossible(Equation equation) {
        return evaluate(equation, equation.numbers.getFirst(), 1);
    }

    private boolean evaluate(Equation equation, long currentValue, int index) {
        if (index == equation.numbers.size()){
            return currentValue == equation.expectedValue;
        }


        return evaluate(equation, currentValue + equation.numbers.get(index), index+1) ||
                evaluate(equation, currentValue * equation.numbers.get(index), index+1) ||
                evaluate(equation, Long.parseLong("" + currentValue + equation.numbers.get(index)), index+1);

    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day7.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (s.hasNextLine()){
            String line = s.nextLine();
            String[] parts = line.split("(: )|( )");

            List<Long> numbers = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                numbers.add(Long.parseLong(parts[i]));
            }

            equations.add(new Equation(Long.parseLong(parts[0]), numbers));
        }
    }
}

class Equation {

    long expectedValue;
    List<Long> numbers;

    public Equation(long expectedValue, List<Long> numbers) {
        this.expectedValue = expectedValue;
        this.numbers = numbers;
    }

}
