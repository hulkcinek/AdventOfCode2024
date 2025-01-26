import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    List<Robot> robots = new ArrayList<>();
    boolean[][] board;
    PrintWriter writer;

    int frame = 0;

    public void start() {
        read();
        letTimePass();
//        getSecurityScore();
    }

    private void getSecurityScore() {
        long securityScore = 1;
        for (Robot.Quadrant q : Robot.Quadrant.values()){
            if (q.equals(Robot.Quadrant.MIDDLE)) continue;

            securityScore *=
                    robots.stream()
                    .map(Robot::getQuadrant)
                    .filter(a -> a.equals(q))
                    .count();
        }
        System.out.println(securityScore);
    }

    private void letTimePass() {
        for (int i = 0; i < 200; i++) {
            robots.forEach(Robot::move);
            frame++;
            print();
        }
        writer.close();
    }

    private void print() {
        emptyBoard();
        fillBoard();

        writer.println(frame + "# ".repeat(robots.getFirst().mapWidth));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                writer.print(board[i][j] ? "X " : ". ");
            }
            writer.println();
        }
    }

    private void fillBoard() {
        for (Robot robot : robots){
            board[robot.getY()][robot.getX()] = true;
        }
    }

    private void emptyBoard() {
        for (boolean[] row : board) {
            Arrays.fill(row, false);
        }
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day14.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        while (s.hasNextLine()){
            sb.append(s.nextLine());
        }

        String regex = "p=(\\d+),(\\d+) v=(-*\\d+),(-*\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sb.toString());

        while(matcher.find()){
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int vecX = Integer.parseInt(matcher.group(3));
            int vecY = Integer.parseInt(matcher.group(4));

            robots.add(new Robot(x, y, vecX, vecY));
        }
        board = new boolean[robots.getFirst().mapHeight][robots.getFirst().mapWidth];

        try {
            writer = new PrintWriter("Day14EasterEggHunt.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
