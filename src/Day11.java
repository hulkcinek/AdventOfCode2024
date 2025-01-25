import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {

    List<Long> stones = new ArrayList<>();


    public void start() {
        read();
        performBlinks();
    }

    private void performBlinks() {
        for (int i = 0; i < 25; i++) {
            List<Long> nextStones = new ArrayList<>();
            for (Long stone : stones) {
                nextStones.addAll(blink(stone));
            }
            stones = nextStones;
        }
        System.out.println(stones.size());
    }
    private List<Long> blink(Long stone){
        List<Long> blinkResult = new ArrayList<>();
        if (stone == 0){
            blinkResult.add(1L);
        }else if (stone.toString().length() % 2 == 0){
            int halfPoint = stone.toString().length()/2;
            blinkResult.add((long) (stone/Math.pow(10, halfPoint)));
            blinkResult.add((long) (stone%Math.pow(10, halfPoint)));
        } else {
            blinkResult.add(stone * 2024);
        }
        return blinkResult;
    }

    private void read() {
        Scanner s;
        try {
            s = new Scanner(new File("src/inputs/day11.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLong()){
            stones.add(s.nextLong());
        }
    }
}
