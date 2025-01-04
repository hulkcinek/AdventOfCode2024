import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

    char[][] grid;

    public void start() {
        read();
        findAll("XMAS");
        findXMASes();
    }

    private void findXMASes() {
        int total = 0;
        for (int y = 1; y < grid.length-1; y++) {
            for (int x = 1; x < grid[0].length-1; x++) {
                if (grid[y][x] == 'A'){
                    if(lookForMAndS(y, x)){
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }

    private boolean lookForMAndS(int y, int x) {
        if (grid[y+1][x+1] == grid[y-1][x-1]) return false;

        int[] vecY = {1, 1, -1, -1};
        int[] vecX = {1, -1, 1, -1};
        int mCount = 0;
        int sCount = 0;
        for (int i = 0; i < 4; i++) {
            switch (grid[y + vecY[i]][x + vecX[i]]) {
                case 'M' -> mCount++;
                case 'S' -> sCount++;
            }
        }
        return mCount == 2 && sCount == 2;
    }

    private void findAll(String searchedPhrase) {
        int total = 0;

        int[] vecY = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] vecX = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                for (int a = 0; a < vecY.length; a++) {
                    if(findPhrase(searchedPhrase, y, x, vecY[a], vecX[a]))
                        total++;
                }
            }
        }
        System.out.println(total);
    }

    private boolean findPhrase(String searchedPhrase, int y, int x, int dy, int dx) {
        for (int i = 0; i < searchedPhrase.length(); i++) {
            int currentY = y + i * dy;
            int currentX = x + i * dx;

            if (!isValid(currentY, currentX)){
                return false;
            }

            if (grid[currentY][currentX] == searchedPhrase.charAt(i)){
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean isValid(int currentY, int currentX) {
        return (0 <= currentY && 0 <= currentX && grid.length > currentY && grid[0].length > currentX);
    }


    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<char[]> temp = new ArrayList<>();

        while(s.hasNextLine()){
            temp.add(s.nextLine().toCharArray());
        }
        grid = temp.toArray(new char[0][0]);
    }
}
