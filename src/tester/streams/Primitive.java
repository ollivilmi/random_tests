package tester.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Primitive {

    private static Random random = new Random();

    public static int[] randomInts(int amount) {
        return random.ints().limit(amount).toArray();
    }

    public static int[] intRange(int amount) {
        return IntStream.rangeClosed(1,amount).toArray();
    }

    public static long[] congruentialLongs(long seed, long a, long c, long m, long amount) {
        return LongStream.iterate(seed, n -> n = ((a*n+c) % m)).skip(1).limit(amount).toArray();
    }

    public static boolean isWord(String word) {
        return word.codePoints().allMatch(Character::isAlphabetic);
    }

    public static List<Integer> letters(String word) {
        return word.chars().boxed().collect(Collectors.toList());
    }

    public static IntSummaryStatistics wordSummary(Collection<String> words) {
        return words.stream().collect(Collectors.summarizingInt(String::length));
    }

    public static String max(Collection<String> words) {
        Optional<String> max = words.stream().max(Comparator.comparing(String::length));
        return max.orElse(null);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        return Stream.of(first, second).reduce((f,s) -> f).get();
    }

    public static <T> List<T> join(Stream<ArrayList<T>> lists) {
        return lists.flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(randomInts(10)));
        System.out.println(Arrays.toString(congruentialLongs(5, 221490917, 11, (long)Math.pow(2, 48), 5)));
        System.out.println(isWord("olli"));
        System.out.println(isWord("0ll1"));
        System.out.println(letters("olli"));

        List<String> words = new ArrayList<>(Arrays.asList("Olli", "Matti", "Vilmi"));
        IntSummaryStatistics stats = wordSummary(words);
        System.out.println(stats.getAverage());

        System.out.println(max(words));
        Stream<Integer> a = Stream.of(1,2,3), b = Stream.of(4,5,6);
        System.out.println(zip(a,b).collect(Collectors.toList()));
        List<ArrayList<Integer>> stressi = new ArrayList<>();
        stressi.add(new ArrayList<>(Arrays.asList(1,2,3,4)));
        stressi.add(new ArrayList<>(Arrays.asList(4,3,2,1)));
        System.out.println(join(stressi.stream()));
    }

}
