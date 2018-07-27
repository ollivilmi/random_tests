package tester.interfaces.measure;

import java.util.Random;

public class Employee implements Measurable {
    private String name;
    private double salary;
    private static Random random = new Random();

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name)
    {
        this.name = name;
        this.salary = Math.round(100000.0 * random.nextDouble());
    }

    public String getName()
    {
        return name;
    }

    @Override
    public double getMeasure() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " salary: " + salary;
    }
}
