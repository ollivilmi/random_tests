package tester.threads;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Numbers {

    public static int[] accumulation(int[] numbers) {
        Arrays.parallelPrefix(numbers, (x,y) -> x * y);
        return numbers;
    }

    public static List<Integer> fibonacci(int limit) {
        return Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(limit)
                .map(n -> n[0])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(accumulation(new int[]{1, 2, 3, 4})));
        System.out.println(fibonacci(4));
    }
}
