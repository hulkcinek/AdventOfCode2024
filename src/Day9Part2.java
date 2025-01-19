import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day9Part2 {

    List<Bundle> files = new ArrayList<>();

    public void start() {
        read();
        print();
        compactFiles();
    }

    private void compactFiles() {
        print();
        while(!isFullyCompacted()){
            for (int i = 0; i < files.size(); i++) {
                Bundle destination = files.get(i);
                if (destination.spaceLeft > 0){
                    int lastAvailable = getLastAvaliable(destination.spaceLeft);
                    move(files.get(lastAvailable), destination);
                    print();//TODO ??
                    break;
                }
            }
        }
    }

    private void move(Bundle lastAvailable, Bundle destination) {
        while (!lastAvailable.numbers.isEmpty()){
            destination.numbers.add(lastAvailable.numbers.removeLast());
            destination.spaceLeft--;
            lastAvailable.spaceLeft++;

        }
    }

    private int getLastAvaliable(int spaceLeft) {
        for (int i = files.size() - 1; i >= 0; i--) {
            if (files.get(i).numbers.size() <= spaceLeft)
                return i;
        }
        return -1;
    }

    private boolean isFullyCompacted() {
        boolean foundEmpty = false;
        for (Bundle file : files){
            if (foundEmpty && file.numbers.isEmpty()) return false;
            if (file.numbers.isEmpty()) foundEmpty = true;
        }
        return true;
    }

    private void print() {
        for (Bundle file : files){
            if (file.numbers.isEmpty())
                System.out.print(".".repeat(file.spaceLeft));
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

            if (isGap)
                files.add(new Bundle(-1, blockLength));
            else
                files.add(new Bundle(idNumber, blockLength));

            if (isGap) idNumber++;
            isGap = !isGap;
        }
    }

}
