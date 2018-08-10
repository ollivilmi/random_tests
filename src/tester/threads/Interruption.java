package tester.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Interruption {

    private static volatile boolean done = false;
    private static Executor executor = Executors.newCachedThreadPool();


    public static void testVolatile() {
        done = false;

        Runnable hellos = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Hello " + i);
            }
            done = true;
        };

        Runnable goodbye = () -> {
            int i = 0;
            while (!done) {
                ++i;
            }
            System.out.println("Goodbye " + i);
        };

        executor.execute(hellos);
        executor.execute(goodbye);
    }

    // Please note that this is just a test, as this is some terrible, terrible code
    public static void testAtomicCounter() {
        AtomicInteger integer = new AtomicInteger(0);

        Runnable counterOne = () -> {
            while (integer.get() < 5000) {
                if (integer.get() % 1000 == 0) {
                    System.out.println("counterOne");
                }
                integer.getAndIncrement();
            }
        };

        Runnable counterTwo = () -> {
            while (integer.get() < 5000) {
                if (integer.get() % 1000 == 0) {
                    System.out.println("counterTwo");
                }
                integer.getAndIncrement();
            }
            System.out.println(integer.get());
        };

        executor.execute(counterOne);
        executor.execute(counterTwo);
    }

    public static void main(String[] args) {
        //testVolatile();
        testAtomicCounter();
    }
}
