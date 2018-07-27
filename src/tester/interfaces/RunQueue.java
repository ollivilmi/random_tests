package tester.interfaces;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;

public class RunQueue {
    private LinkedList<Runnable> runnables;

    public void add(Runnable runnable)
    {
        runnables.addLast(runnable);
    }

    public void runAndEmpty()
    {
        while (!runnables.isEmpty())
            runnables.pop().run();
    }

    public void runAll()
    {
        runnables.forEach(Runnable::run);
    }

    public Runnable getRunnables()
    {
        return  () -> {
            runAll();
        };
    }

    public RunQueue()
    {
        Class<?> cl = this.getClass();
        while (cl != null) {
            for (Method m : cl.getDeclaredMethods()) {
                System.out.println(
                        Modifier.toString(m.getModifiers()) + " " +
                                m.getReturnType().getCanonicalName() + " " +
                                m.getName() +
                                Arrays.toString(m.getParameters()));
            }
            cl = cl.getSuperclass();
        }
    }

    public static void main(String[] args) {
        new RunQueue();
    }
}
