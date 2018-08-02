package tester.collections;

import java.util.*;

public class SetOperations {

    public static <E extends Comparable<E>> Set<? extends E> union(Set<? extends E> set1, Set<? extends E> set2)
    {
        Set<E> setUnion = new HashSet<>(set1);
        setUnion.addAll(set2);
        return setUnion;
    }

    public static <E extends Comparable<E>> Set<? extends E> intersection(Set<? extends E> set1, Set<? extends E> set2)
    {
        Set<E> setIntersection = new HashSet<>(set1);

        setIntersection.retainAll(set2);
        return setIntersection;
    }

    public static <E extends Comparable<E>> Set<? extends E> difference(Set<? extends E> set1, Set<? extends E> set2)
    {
        Set<E> setDifference = new HashSet<>(set1);
        setDifference.removeAll(set2);
        return setDifference;
    }

    public static void main(String[] args) {
        Set<String> wordSet = new HashSet<>(Arrays.asList("olli", "markus", "ville", "henri"));
        Set<String> wordSet2 = new HashSet<>(Arrays.asList("olli", "henri"));

        System.out.println(union(wordSet, wordSet2));
        System.out.println(intersection(wordSet, wordSet2));
        System.out.println(difference(wordSet, wordSet2));
    }
}
