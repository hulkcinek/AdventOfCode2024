import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day8 {

    char[][] map;
    char[][] antinodes;

    public void start() {
        read();
        fillAllAntinodes();
        countAntinodes();
    }

    private void fillAllAntinodes() {
        for (int y = 0; y < map.length; y++) {
            char[] row = map[y];
            for (int x = 0; x < row.length; x++) {
                char point = row[x];
                if (point != '.') {
//                    drawAntinodesPART1(point, y, x);
                    findTheSecondAntenna(point, y, x);
                }
            }
        }
    }

    private void findTheSecondAntenna(char type, int y1, int x1) {
        for (int y2 = 0; y2 < map.length; y2++) {
            char[] row = map[y2];
            for (int x2 = 0; x2 < row.length; x2++) {
                char point = row[x2];

                if (type != point) continue;
                if (y1 == y2 && x1 == x2) continue;

                drawALine(y1, x1, y2, x2);
            }
        }
    }

    private void drawALine(int y1, int x1, int y2, int x2) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if ((y-y1)*(x2-x1) == (y2-y1)*(x-x1)){
                    antinodes[y][x] = '#';
                }
            }
        }
    }

    private void drawAntinodesPART1(char type, int y1, int x1) {
        for (int y2 = 0; y2 < map.length; y2++) {
            char[] row = map[y2];
            for (int x2 = 0; x2 < row.length; x2++) {
                char point = row[x2];

                if (type != point) continue;
                if (y1 == y2 && x1 == x2) continue;

                int nextY = y1 + (y1 - y2);
                int nextX = x1 + (x1 - x2);
                if (isSafe(nextY, nextX))
                    antinodes[nextY][nextX] = '#';

                nextY = y2 + (y2 - y1);
                nextX = x2 + (x2 - x1);
                if (isSafe(nextY, nextX))
                    antinodes[nextY][nextX] = '#';
            }
        }
    }

    private void countAntinodes() {
        int total = 0;
        for (int i = 0; i < antinodes.length; i++) {
            for (int j = 0; j < antinodes[0].length; j++) {
                if (antinodes[i][j] == '#')
                    total++;
            }
        }
        System.out.println(total);
    }

    private boolean isSafe(int y, int x) {
        return (0 <= y && 0 <= x && map.length > y && map[0].length > x);
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day8.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<char[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            String line = s.nextLine();
            temp.add(line.toCharArray());
        }
        map = temp.toArray(new char[0][]);
        antinodes = new char[map.length][map[0].length];
        for (char[] antinode : antinodes) {
            Arrays.fill(antinode, '.');
        }
    }
}
