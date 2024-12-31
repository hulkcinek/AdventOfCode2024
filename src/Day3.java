import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public void start() {
        part1();
        part2();
    }

    private void part2() {
        Scanner s = null;
        try {
            s = new Scanner(new File("src/inputs/day3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile("(do\\(\\))|(don't\\(\\))|(mul\\(\\d+,\\d+\\))");

        int total = 0;

        while(s.hasNextLine()){
            String line = s.nextLine();
            Matcher matcher = pattern.matcher(line);

            List<String> instructions = new LinkedList<>();
            while(matcher.find()){
                instructions.add(matcher.group());
            }

            boolean ignoreInstruction = false;
            for (String instruction : instructions){
                switch (instruction){
                    case "do()":
                        ignoreInstruction = false;
                        break;
                    case "don't()":
                        ignoreInstruction = true;
                        break;
                    default: //mul
                        if (ignoreInstruction)
                            break;
                        Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
                        Matcher m = p.matcher(instruction);
                        if (m.find()) {
                            int x = Integer.parseInt(m.group(1));
                            int y = Integer.parseInt(m.group(2));
                            total += x * y;
                        }
                }
            }
        }
        System.out.println(total);
    }

    private void part1() {
        Scanner s = null;
        try {
            s = new Scanner(new File("src/inputs/day3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        int total = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                total += x * y;
            }
        }
        System.out.println(total);
    }
}
