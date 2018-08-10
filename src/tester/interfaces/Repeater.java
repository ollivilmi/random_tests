package tester.interfaces;

import java.util.Comparator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Repeater {
    public Repeater()
    {
        // Method references vs lambdas
        repeat(1, this::greeter);
        repeat(1, () -> message("FileSearch world"));
        repeat(2, i -> System.out.println("Countdown: " + (1-i)));
    }

    public void repeat(int times, Runnable r)
    {
        for (int i = 0; i<times; i++)
            r.run();
    }

    public void repeat(int times, IntConsumer c)
    {
        IntStream.range(0, times).forEach(c::accept);
    }

    private void greeter()
    {
        System.out.println("FileSearch world");
    }

    private void message(String msg)
    {
        System.out.println(msg);
    }

    // Lambda uses variable from method parameter
    public static Comparator<String> compareInDirection(int dir) {
        return (x, y) -> dir * x.compareTo(y);
    }
}
