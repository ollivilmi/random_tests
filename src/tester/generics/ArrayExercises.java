package tester.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayExercises {

    public static <E extends Comparable<E>> Pair<E> firstLast(ArrayList<E> a)
    {
        return new Pair<>(a.get(0), a.get(a.size()-1));
    }

    public static <E> E findElement(E[] array, Comparator<? super E> comparison)
    {
        if (array.length < 1)
            return null;
        E toFind = array[0];

        for (E elem : array)
            if (comparison.compare(elem, toFind) > 0)
                toFind = elem;
        return toFind;
    }

    public static <E> E findElement(List<? super E> list, Comparator<? super E> comp)
    {
        // List needs to be cast to E[] due to erasure (toArray returns Object[])
        return findElement((E[])list.toArray(), comp);
    }

    public static <E extends Comparable<E>> E max(E[] array)
    {
        return findElement(array, Comparator.naturalOrder());
    }

    public static <E extends Comparable<E>> E min(E[] array)
    {
        return findElement(array, Comparator.reverseOrder());
    }

    public static <E extends Comparable<E>> Pair<E> getMinMax(E[] array)
    {
        return new Pair<>(max(array), min(array));
    }

    public static <T> void minmax(List<T> elements,Comparator<? super T> comp, List<? super T> result)
    {
        result.add(findElement(elements, comp));
        result.add(findElement(elements, comp.reversed()));
    }

    public static <E extends Comparable<E>> E[] sort(E[] array)
    {
        Arrays.sort(array, Comparator.naturalOrder());
        return array;
    }

    public static void main(String[] args) {
        // Get max/min from array as a Pair<K,V>
        Integer[] numbers = new Integer[]{6, 2, 10, 4, 5, 6, 7, 8 ,1, 8};
        System.out.println(getMinMax(numbers));

        // Get max/min from array to list
        List<Integer> numberList = new ArrayList<>();
        minmax(Arrays.asList(numbers), Integer::compareTo, numberList);
        System.out.println(numberList);

        // The array order hasn't been altered
        System.out.println(Arrays.toString(numbers));

        // Let's sort it
        System.out.println(Arrays.toString(sort(numbers)));
    }
}
