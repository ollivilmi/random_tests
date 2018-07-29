package tester.generics;

import tester.interfaces.measure.Employee;
import tester.interfaces.measure.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAppender {

    // Two different but identical implementations to deal with use-site variance

    // List2 is a subclass of list1
    public static <E> List<E> append(List<E> list1, List<? extends E> list2)
    {
        list1.addAll(list2);
        return list1;
    }

    // List1 is a superclass of list2
    public static <E> List<? super E> append2(List<? super E> list1, List<E> list2)
    {
        list1.addAll(list2);
        return list1;
    }


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>(Arrays.asList(new Employee("Robert Reijonen"), new Employee("Ville Holmstedt")));
        List<Manager> managers = new ArrayList<Manager>(Arrays.asList(new Manager("Olli Vilmi", 5000)));
        System.out.println(append(employees, managers));
        System.out.println(append2(employees, managers));
    }
}
