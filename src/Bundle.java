import java.util.ArrayList;
import java.util.List;

public class Bundle {

    int spaceLeft;
    List<Integer> numbers;

    public Bundle(int idNumber, int spaceLeft){
        this.spaceLeft = spaceLeft;
        this.numbers = new ArrayList<>();
        if (idNumber != -1){
            for (int i = 0; i < spaceLeft; i++) {
                numbers.add(idNumber);
            }
            this.spaceLeft = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : numbers)
            sb.append(i);
        return sb.toString();
    }
}
