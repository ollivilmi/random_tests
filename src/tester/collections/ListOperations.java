package tester.collections;

import java.util.*;

public class ListOperations {


    public static List<String> toUpperCase(List<String> list)
    {
/*      ListIterator<String> i = list.listIterator();
        while (i.hasNext()) {
            String s = i.next();
            i.set(s.toUpperCase());
        }*/
        list.replaceAll(String::toUpperCase);
        return list;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>(Arrays.asList("olli", "robert", "henri", "olli"));
        System.out.println(toUpperCase(words));

        Map<String, Set<Integer>> map = new TreeMap<>();
    }
}
