import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {

    int[][] map;
    List<Point> trailHeads = new ArrayList<>();

    public void start() {
        read();
        findTrailHeads();
        countTheTotalScore();
    }

    private void countTheTotalScore() {
        int total = 0;
        for (Point point : trailHeads){
//            System.out.println(evaluateScore(point.y, point.x));
            total += evaluateScore(point.y, point.x);
        }
        System.out.println(total);
    }

    private void countTheTotalScoreFunctional() {
        int total = trailHeads.stream() //
                .map(point -> evaluateScore(point.y, point.x)) //
                .mapToInt(i -> i) //
                .sum(); // .reduce(0, Integer::sum)

        System.out.println(total);
    }

    private int evaluateScore(int y, int x) {
        List<Point> endings = getUniqueEndings(y, x, new ArrayList<>());
        return endings.size();
    }

    private List<Point> getUniqueEndings(int y, int x, List<Point> endings) {
        if (map[y][x] == 9) {
//            endings = uniqueAdd(endings, Collections.singletonList(new Point(y, x))); PART 1
            endings.add(new Point(y, x));
            return endings;
        }
        int[] vecY = {-1, 0, 1, 0};
        int[] vecX = {0, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            int nextY = y + vecY[i];
            int nextX = x + vecX[i];
            if (isValid(nextY, nextX) && map[nextY][nextX] - map[y][x] == 1) {
                endings = uniqueAdd(endings, getUniqueEndings(nextY, nextX, endings));
            }
        }
        return endings;
    }

    private List<Point> getUniqueEndingsWithEnum(int y, int x, List<Point> endings) {
        if (map[y][x] == 9) {
            uniqueAddFunctional(endings, Collections.singletonList(new Point(y, x)));
            return endings;
        }

        // z uzyciem Direction bez streamow:
        /*for(Direction direction : Direction.values()) {
            int nextY = y + direction.getY();
            int nextX = x + direction.getX();
            if (isValid(nextY, nextX) && map[nextY][nextX] - map[y][x] == 1) {
                uniqueAddFunctional(endings, getUniqueEndingsWithEnum(nextY, nextX, endings));
            }
        }*/


        Arrays.stream(Direction.values()) //
                .map(direction -> new Point(y+direction.getY(), x+direction.getX())) //
                .filter(p -> {
                    int pX = p.getX();
                    int pY = p.getY();
                    return isValid(pY, pX) && map[pY][pX] - map[y][x] == 1;
                }) //
                .forEach(p -> uniqueAddFunctional(endings, getUniqueEndingsWithEnum(p.getY(), p.getX(), endings)));



        return endings;
    }

    enum Direction {
        UP(0,-1),
        DOWN(0,1),
        LEFT(-1,0),
        RIGHT(1,0);

        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


    private List<Point> uniqueAdd(List<Point> list, List<Point> added) {
        for (Point point : added){
            if (!list.contains(point))
                list.add(point);
        }
        return list;
    }

    private void uniqueAddFunctional(List<Point> list, List<Point> added) {

        added.stream()
            .filter(point -> !list.contains(point))
            .forEach(list::add);
    }


    private boolean isValid(int y, int x) {
        return (0 <= y && 0 <= x && map.length > y && map[0].length > x);
    }

    private void findTrailHeads() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == 0)
                    trailHeads.add(new Point(y, x));
            }
        }
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day10.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<int[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            char[] line = s.nextLine().toCharArray();
            int[] temp2 = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                char c = line[i];
                temp2[i] = c - '0';
            }
            temp.add(temp2);
        }
        map = temp.toArray(new int[0][]);
    }
}

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return y == point.y && x == point.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}