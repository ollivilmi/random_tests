package tester.interfaces;

import java.util.*;

public class Sort {

    private static void luckySort(ArrayList<String> strings, Comparator<String> comp)
    {
        long from, to; int counter = 0;
        from = System.currentTimeMillis();

        outer:
        while (true)
        {
            ++counter;
            Collections.shuffle(strings);

            Iterator<String> i = strings.iterator();
            String s1 = "", s2;

            if (i.hasNext())
                s1 = i.next();

            while (i.hasNext())
            {
                s2 = i.next();
                if (comp.compare(s1, s2) > 0)
                    continue outer;
                s1 = s2;
            }
            break;
        }

        to = System.currentTimeMillis();
        System.out.println("Running time (ms): " + (to-from));
        System.out.println("Times shuffled: " + counter);
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList(Arrays.asList(new String[]{"olli", "henri", "lauri", "georgi", "maros", "kristian", "sebastien"}));
        ArrayList<String> words2 = new ArrayList<String>(){ { add("vasara"); add("halli"); add("kahvi"); add("muki"); add("pulla"); } };
/*        words.forEach(System.out::println);

        System.out.println("\nSorting by length of the word:");
        luckySort(words, Comparator.comparingInt(String::length));
        words.forEach(System.out::println);

        System.out.println("\nNow sorting alphabetically: ");
        luckySort(words, String::compareTo);
        words.forEach(System.out::println);*/

        luckySort(words2, String::compareTo);
        words2.forEach(System.out::println);
    }

}
