package tester.generics;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayExercises {

    public static <E extends Comparable<E>> Pair<E> firstLast(ArrayList<E> a)
    {
        return new Pair<>(a.get(0), a.get(a.size()-1));
    }

    public static <E> E findElement(E[] array, Comparator<E> comparison)
    {
        if (array.length < 1)
            return null;
        E toFind = array[0];

        for (E elem : array)
            if (comparison.compare(elem, toFind) > 0)
                toFind = elem;
        return toFind;
    }

    public static <E extends Comparable<E>> E max(E[] array)
    {
        return findElement(array, E::compareTo);
    }

    public static <E extends Comparable<E>> E min(E[] array)
    {
        return findElement(array, (e1, e2) -> e1.compareTo(e2) * (-1));
    }

    public static <E extends Comparable<E>> Pair<E> getMinMax(E[] array)
    {
        return new Pair<>(max(array), min(array));
    }

    public static void main(String[] args) {
        Integer[] numbers = new Integer[]{6, 2, 10, 4, 5, 6, 7, 8 ,1, 88};
        System.out.println(getMinMax(numbers));
    }
}
