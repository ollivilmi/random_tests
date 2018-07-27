package tester.interfaces.delay;

import java.util.Scanner;

public class Delay {

    private static Scanner reader = new Scanner(System.in);

    public Delay()
    {
        int delay = reader.nextInt();
        messageWithDelay(delay, "Goodbye, cruel world");
        messageWithDelay(0, "Hello, world");
        taskWithDelay(300, () -> System.out.println("General task with delay"));
        taskWithDelay(700, Delay::printASCII);
    }

    private static void printASCII()
    {
        for (char c = 'A'; c <= 'z'; c++)
            System.out.print(c);
    }

    private void messageWithDelay(int ms, String message)
    {
        new Thread( () -> {
            try {
                Thread.sleep(ms);
                System.out.println(message);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    private void taskWithDelay(int ms, Runnable task)
    {
        new Thread( () -> {
            try {
                Thread.sleep(ms);
                task.run();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new Delay();
    }
}
