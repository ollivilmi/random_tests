package tester.generics;

public class Pair<E extends Comparable<E>> {
    private E key;
    private E value;

    public Pair(E key, E value) {
        this.key = key;
        this.value = value;
    }

    public E getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }

    public E getMax() {
        if (key.compareTo(value) > 0)
            return key;
        return value;
    }

    public E getMin() {
        if (key.compareTo(value) < 0)
            return key;
        return value;
    }

    @Override
    public String toString() {
        return key.toString() + ";" + value.toString();
    }

    public static void main(String[] args) {
        Pair<Integer> pair = new Pair<>(1,5);
        System.out.println(pair.getMax());
        System.out.println(pair.getMin());
    }
}
