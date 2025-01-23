import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {

    List<Garden> gardens = new ArrayList<>();

    public void start() {
        read();
        getTotalPrice();
    }

    private void getTotalPrice() {
        int total = gardens.stream()
                .map(Garden::getPrice)
                .mapToInt(i -> i)
                .sum();
        System.out.println(total);
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day12.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //reading input
        List<char[]> temp = new ArrayList<>();
        while (s.hasNextLine()){
            String line = s.nextLine();
            temp.add(line.toCharArray());
        }
        char[][] input = temp.toArray(new char[0][]);

        //creating the list of gardens
        for (char i = 'A'; i <= 'Z'; i++) {
            gardens.add(new Garden(i, input.length, input[0].length));
        }

        //filling the gardens with their corresponding plants
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                gardens.get(input[y][x] - 'A').addPlant(y, x);
            }
        }
    }
}
class Garden {

    char type;
    Character[][] plot;

    public Garden(char type, int height, int width) {
        this.type = type;
        this.plot = new Character[height][width];
        Arrays.stream(plot).forEach(p -> Arrays.fill(p, '.'));
    }

    public void addPlant(int y, int x) {
        plot[y][x] = type;
    }

    public int getPrice(){
        int area = getArea();
        int perimeter = getPerimeter();
        System.out.println(this + " -> area: " + area + ", perimeter: " + perimeter + ", price: " + area * perimeter);
        return area * perimeter;
    }

    public int getArea(){
        int area = 0;
        for (Character[] row : plot){
            area += (int) Arrays.stream(row)
                    .filter(a -> a == type)
                    .count();
        }
        return area;
    }

    public int getPerimeter(){
        int perimeter = 0;
        for (int y = 0; y < plot.length; y++) {
            for (int x = 0; x < plot[0].length; x++) {
                if (plot[y][x] != type) continue;

                for (Direction d : Direction.values()){
                    int nextY = y + d.y;
                    int nextX = x + d.x;

                    if (!isValid(nextY, nextX) || plot[nextY][nextX] == '.') perimeter++;
                }
            }
        }
        return perimeter;
    }

    enum Direction {
        UP(0,-1),
        DOWN(0,1),
        LEFT(-1,0),
        RIGHT(1,0);

        final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private boolean isValid(int y, int x){
        return (0 <= y && 0 <= x && plot.length > y && plot[0].length > x);
    }

    @Override
    public String toString() {
        return "Garden: " + type;
    }
}
