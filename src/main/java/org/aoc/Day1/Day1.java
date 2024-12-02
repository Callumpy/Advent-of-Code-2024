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
        val numbers = day1.readNumbersFromFile();

        System.out.println("Part 1: " + day1.findSum(numbers.leftList, numbers.rightList));
        System.out.println("Part 2: " + day1.findSimilarityScore(numbers.leftList, numbers.rightList));
    }

    public Integer findSum(List<Integer> leftList, List<Integer> rightList) {
        return IntStream.range(0, Math.min(leftList.size(), rightList.size()))
                .map(i -> Math.abs(leftList.get(i) - rightList.get(i)))
                .sum();
    }

    public Integer findSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        val sum = new AtomicInteger();

        leftList.forEach(left -> {
            sum.addAndGet(
                    left * (int) rightList.stream()
                            .filter(r -> r.equals(left))
                            .count());
        });

        return sum.get();
    }

    private NumberPair readNumbersFromFile() throws IOException {
        val reader = new BufferedReader(new FileReader("src/main/resources/day1.txt"));
        var line = "";

        val left = new ArrayList<Integer>();
        val right = new ArrayList<Integer>();

        while ((line = reader.readLine()) != null) {
            val parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }

        return new NumberPair(
                left.stream().sorted().toList(),
                right.stream().sorted().toList());
    }


    static class NumberPair {
        public List<Integer> leftList;
        public List<Integer> rightList;

        public NumberPair(List<Integer> leftList, List<Integer> rightList) {
            this.leftList = leftList;
            this.rightList = rightList;
        }
    }
}