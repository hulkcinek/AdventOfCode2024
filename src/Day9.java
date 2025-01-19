import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {

    List<Integer> files = new ArrayList<>();

    public void start() {
//        PART 1
        /*read();
        compactFiles();
        checkSum();*/

//        PART 2
        Day9Part2 d = new Day9Part2();
        d.start();
    }

    private void checkSum() {

        long total = 0;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i) == -1) break;
            total += files.get(i) * i;
        }
        System.out.println(total);
    }

    private void compactFiles() {
//        print();
        while(!isFullyCompacted()){
            for (int i = 0; i < files.size(); i++) {
                if (files.get(i) == -1){
                    int lastIndex = findLastIndex();
                    files.set(i, files.get(lastIndex));
                    files.set(lastIndex, -1);
                    break;
                }
            }
//            print();
        }
    }

    private int findLastIndex() {
        for (int i = files.size() - 1; i >= 0; i--) {
            if (files.get(i) != -1) return i;
        }

        return -1;
    }

    private boolean isFullyCompacted() {
        boolean foundEmpty = false;
        for (Integer file : files){
            if (foundEmpty && file != -1) return false;
            if (file == -1) foundEmpty = true;
        }
        return true;
    }

    private void print() {
        for (Integer file : files){
            if (file == -1)
                System.out.print(".");
            else
                System.out.print(file);
        }
        System.out.println();
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day9.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line = s.nextLine();

        int idNumber = 0;
        boolean isGap = false;
        for (char block : line.toCharArray()) {
            int blockLength = Integer.parseInt("" + block);
            for (int i = 0; i < blockLength; i++) {
                if (isGap)
                    files.add(-1);
                else
                    files.add(idNumber);
            }
            if (isGap) idNumber++;
            isGap = !isGap;

        }
    }
}
