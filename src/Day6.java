import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    char[][] map;
    int guardX = -1;
    int guardY = -1;
    Direction direction = Direction.UP;


    public void start() {
        read();
        findGuardsPath();
    }

    private void findGuardsPath() {
        while(true){
            step();
        }
    }

    private void step() {
        if (!tryToTakeAStep()){
            rotate90degreesClockwise();
            printBoard();
        }
    }

    private boolean tryToTakeAStep() {
        int nextGuardX = guardX;
        int nextGuardY = guardY;

        switch (direction){
            case UP:
                nextGuardY -= 1;
                break;
            case DOWN:
                nextGuardY += 1;
                break;
            case LEFT:
                nextGuardX -= 1;
                break;
            case RIGHT:
                nextGuardX += 1;
                break;
        }

        if (isOutOfBounds(nextGuardY, nextGuardX)){
            countUpTheScore();
        }

        if (map[nextGuardY][nextGuardX] == '#'){
            return false;
        }

        move(nextGuardY, nextGuardX);
        return true;
    }

    private void move(int y, int x) {
        map[guardY][guardX] = 'X';
        char guard = 0;

        switch (direction){
            case UP -> guard = '^';
            case RIGHT -> guard = '>';
            case DOWN -> guard = 'v';
            case LEFT -> guard = '<';
        }
        map[y][x] = guard;

        guardY = y;
        guardX = x;
    }

    private void rotate90degreesClockwise() {
        switch (direction){
            case UP -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
        }
    }

    private boolean isOutOfBounds(int y, int x) {
        return ((0 > y) || (0 > x) || (map.length <= y) || (map[0].length <= x));
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day6.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<char[]> temp = new ArrayList<>();

        int y = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();
            temp.add(line.toCharArray());
            if (line.contains("^")){
                guardX = line.indexOf('^');
                guardY = y;
            }
            y++;
        }
        map = temp.toArray(new char[0][]);
    }

    private void countUpTheScore() {
        int total = 1;
        for (char[] row : map){
            for (char point : row){
                if (point == 'X') total++;
            }
        }
        System.out.println(total);
        System.exit(0);
    }

    private void printBoard() {
        for (char[] row : map){
            for (char a : row){
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("- ".repeat(map[0].length));
        System.out.println();
    }

    enum Direction{
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}

