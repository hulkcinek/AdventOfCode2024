import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {
    public void start() {
        read();
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day11.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
