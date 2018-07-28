package tester.generics;

import java.util.ArrayList;
import java.util.List;

public class Table<K, V> {
    ArrayList<Entry<K, V>> list = new ArrayList<>();

    public class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key.toString() + ": " + value.toString() + "]";
        }
    }

    public void add(K key, V value)
    {
        list.add(new Entry<>(key, value));
    }

    public void remove(K key)
    {
        list.removeIf(kvEntry -> kvEntry.key == key);
    }

    public List<Entry> get(K key)
    {
        List<Entry> entries = new ArrayList<>();
        list.forEach(kvEntry -> { if (kvEntry.key == key) entries.add(kvEntry); });
        return entries;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        list.forEach(builder::append);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        Table<String, Integer> table = new Table<>();
        String names[] = new String[]{"Olli", "Henri", "Lauri", "Ville", "Theo", "Olli", "Robert", "Markus"};
        for (int i = 0; i<names.length; i++)
            table.add(names[i], i);


        System.out.println(table);
        System.out.println(table.get("Olli"));
        table.remove("Olli");
        System.out.println(table.get("Olli"));
    }
}
