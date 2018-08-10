package tester.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Testing {

    public static List<Integer> power(int power, int iterations) {
        return Stream.iterate(1, p -> p * power)
                .peek(System.out::println)
                .limit(iterations)
                .collect(Collectors.toList());
    }

    public static List<?> tailView(List<?> list, int elements) {
        if (elements > list.size())
            return list;
        return list.subList(list.size() - elements, list.size());
    }

    public static <T extends Comparable<T>> Optional<T> max(Collection<T> collection) {
        return collection.stream().max(T::compareTo);
    }

    public static boolean startsWith(Collection<String> collection, String startsWith) {
        return collection.parallelStream().anyMatch(s -> s.startsWith(startsWith));
    }

    public static Map<Integer, Set<String>> wordsByLength(Collection<String> words) {
        return words.parallelStream().collect(Collectors.groupingByConcurrent(String::length, toSet()));
    }

    public static List<String> wordsLessThan(Collection<String> words, int length, int limit) {
        return words.parallelStream()
                .unordered()
                .filter(w -> w.length() < length)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> powerOfTwo = power(2, 8);
        System.out.println(tailView(powerOfTwo, 3));
        max(powerOfTwo).ifPresent(System.out::println);
        List<String> words = new ArrayList<>(Arrays.asList("Olli", "Apina", "Mekaanikko", "Quarter Pounder", "Quarter Pounder"));
        System.out.println(startsWith(words, "Qua"));
        wordsByLength(words).forEach((k,v) -> System.out.println(k + " " + v));
        wordsLessThan(words, 12, 2).forEach(System.out::println);
    }
}
