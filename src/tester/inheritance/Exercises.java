package tester.inheritance;

public class Exercises<T> {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cl = Class.forName("tester.inheritance.Exercises");
        System.out.println(cl.getClass());
        Exercises<Integer> ex = new Exercises<>();
        System.out.println();
    }

    @Override
    public String toString() {
        return this.getClass() + "diiba daaba";
    }
}