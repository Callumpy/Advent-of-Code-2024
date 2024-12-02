package Day2;

import lombok.val;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        val reader = new BufferedReader(new FileReader("src/main/resources/day2.txt"));

        val rows = reader.lines()
                .map(Row::new)
                .toList();
        val safeRows = rows.stream()
                .filter(row -> row.safety == Row.Safety.SAFE)
                .toList()
                .size();


        System.out.println("Part 1: " + safeRows);
    }


    static class Row {
        List<Integer> numbers;
        Safety safety;

        public Row(String line) {
            this.numbers = Stream.of(line.split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();
            this.safety = isSafe();
        }

        public Safety isSafe() {
            if ((isIncreasing() || isDecreasing()) && isDifferenceNotTooBig())
                return Safety.SAFE;
            return Safety.UNSAFE;
        }

        private boolean isIncreasing() {
            return numbers.stream().sorted().toList().equals(numbers);
        }

        private boolean isDecreasing() {
            return numbers.stream().sorted(Comparator.reverseOrder()).toList().equals(numbers);
        }

        private boolean isDifferenceNotTooBig() {
            for (var i = 0; i < numbers.size() - 1; i++) {
                if (Objects.equals(numbers.get(i), numbers.get(i + 1))) {
                    return false;
                }

                if (numbers.get(i + 1) - numbers.get(i) > 3 || numbers.get(i) - numbers.get(i + 1) > 3) {
                    return false;
                }
            }

            return true;
        }

        enum Safety {
            SAFE, UNSAFE
        }
    }
}