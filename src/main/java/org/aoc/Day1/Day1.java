package org.aoc.Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import lombok.val;

public class Day1 {
    public static void main(String[] args) throws IOException {
        val day1 = new Day1();
        val numbersPair = new NumberPair();

        System.out.println("Part 1: " + day1.findSum(numbersPair));
        System.out.println("Part 2: " + day1.findSimilarityScore(numbersPair));
    }

    public Integer findSum(NumberPair numbers) {
        return IntStream.range(0, Math.min(numbers.leftList.size(), numbers.rightList.size()))
                .map(i -> Math.abs(numbers.leftList.get(i) - numbers.rightList.get(i)))
                .sum();
    }

    public Integer findSimilarityScore(NumberPair numbers) {
        val sum = new AtomicInteger();

        numbers.leftList.forEach(left -> {
            sum.addAndGet(
                    left * (int) numbers.rightList.stream()
                            .filter(r -> r.equals(left))
                            .count());
        });

        return sum.get();
    }

    public static class NumberPair {
        public List<Integer> leftList;
        public List<Integer> rightList;

        public NumberPair() throws IOException {
            val reader = new BufferedReader(new FileReader("src/main/resources/day1.txt"));
            var line = "";

            val left = new ArrayList<Integer>();
            val right = new ArrayList<Integer>();

            while ((line = reader.readLine()) != null) {
                val parts = line.split("\\s+");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
            }

            this.leftList = left.stream().sorted().toList();
            this.rightList = right.stream().sorted().toList();
        }
    }
}
