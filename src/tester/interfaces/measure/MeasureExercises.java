package tester.interfaces.measure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Comparator.*;

public class MeasureExercises {
    public MeasureExercises()
    {
        Employee[] employees = new Employee[] { new Employee("Jack", 50000), new Employee( "Olli", 25200) };
        System.out.println("Average: " + average(employees));
        System.out.println(largest(employees));
        System.out.println(smallest(employees));

        System.out.println("Adding employees\n");
        List<String> names = new ArrayList(Arrays.asList(new String[]{"Anastacia", "Matti", "Teppo", "Henri", "Lauri"}));
        List<Employee> employeeList = names.stream().map(Employee::new).collect(Collectors.toList());

        employeeList.addAll(Arrays.asList(employees));
        sortEmployeesByName(employeeList.toArray(employees));
        System.out.println();
        sortEmployeesBySalary(employeeList.toArray(employees));
    }

    private void printEmployee(Object e)
    {
        if (e instanceof Employee)
            System.out.println(e.toString());
    }

    private double average(Measurable[] objects) {
        double sum = 0;
        for (Measurable o : objects)
            sum += o.getMeasure();
        return sum / objects.length;
    }

    private Measurable largest(Measurable[] objects) {
        Arrays.sort(objects, (o1, o2) -> (int)(o2.getMeasure() - o1.getMeasure()));
        return objects[0];
    }

    private Measurable smallest(Measurable[] objects) {
        Arrays.sort(objects, MeasureExercises::compareMeasurables);
        return objects[0];
    }

    private static int compareMeasurables(Measurable m1, Measurable m2)
    {
        return (int)(m1.getMeasure()-m2.getMeasure());
    }

    private static void sortEmployeesBySalary(Employee[] employees)
    {
        Arrays.sort(employees, comparing(Employee::getMeasure,reverseOrder()).thenComparing(Employee::getName));
        for (Employee e  :employees)
            System.out.println(e);
    }

    private static void sortEmployeesByName(Employee[] employees)
    {
        Arrays.sort(employees, comparing(Employee::getName).thenComparing(Employee::getMeasure));
        for (Employee e  :employees)
            System.out.println(e);
    }

    public static void main(String[] args) {
        new MeasureExercises();
    }

}
