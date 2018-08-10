package tester.threads;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FileSearch {

    private static Random random = new Random();
    public static final String BOOK_URL = "https://www.gutenberg.org/files/57649/57649-0.txt";
    public static final Path FILE_PATH = Paths.get("book.txt");

    public static List<Path> searchFiles(Path dir, String toFind) throws IOException {
        if (!Files.isDirectory(dir)) {
            return null;
        }
        List<Path> files;
        files = Files.list(dir).parallel()
                .filter(file -> file.toString().contains(toFind))
                .collect(Collectors.toList());
        return files;
    }

    public static Optional<Path> searchAnyFile(Path dir, String toFind) throws IOException {
        if (!Files.isDirectory(dir)) {
            return Optional.empty();
        }
        return Files.list(dir).parallel()
                .filter(file -> file.toString().contains(toFind))
                .findAny();
    }

    public static Optional<Path> searchAnyFile2(Path dir, String toFind) throws IOException {
        if (!Files.isDirectory(dir)) {
            return Optional.empty();
        }
        List<Path> files = Files.list(dir).collect(Collectors.toList());
        List<Callable<Path>> checkWord = new ArrayList<>();
        for (Path file : files) {
            checkWord.add(() -> {
                if (file.toString().contains(toFind)) {
                    return file;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted: " + Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
                return null;
            });
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            return Optional.of(executor.invokeAny(checkWord));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static long runTime(Runnable runnable) {
        long from, to;
        from = System.nanoTime();
        runnable.run();
        to = System.nanoTime();
        return to - from;
    }

    public static double[] randomNumbers(int amount) {
        double[] randomNumbers = new double[amount];
        for (int i = 0; i<amount; i++) {
            randomNumbers[i] = random.nextDouble() * amount;
        }
        return randomNumbers;
    }

    public static void loadFile(URL url) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(FILE_PATH);
        new BufferedReader(new InputStreamReader(url.openStream())).lines()
        .forEach(l -> {
            try {
                writer.write(l + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    public static boolean containsWord(Path file, String word) throws IOException {
        return Files.newBufferedReader(file).lines()
                .map(l -> l.split(" "))
                .anyMatch(words -> {
                    for(String w : words) {
                        if (w.equals(word)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    public static boolean compareSortMethods(int amount) {
        long sortTime, sortTimeParallel;
        double[] rand = randomNumbers(amount);
        double[] n = new double[amount];

        System.arraycopy(rand, 0, n, 0, rand.length);
        sortTime = runTime(() -> Arrays.sort(n) );

        System.arraycopy(rand, 0, n, 0, rand.length);
        sortTimeParallel = runTime(() ->  Arrays.parallelSort(n) );
        return sortTime < sortTimeParallel;
    }

    public static void main(String[] args) throws IOException {
/*        Path dir = Paths.get(System.getProperty("user.dir") + "/src/tester/interfaces");
        searchFiles(dir, "java").forEach(System.out::println);

        searchAnyFile(dir, "java").ifPresent(System.out::println);

        searchAnyFile2(dir, "Sort").ifPresent(System.out::println);

        System.out.println(compareSortMethods(20000));*/

        if (!Files.exists(FILE_PATH)) {
            loadFile(new URL(BOOK_URL));
        }

        System.out.println(containsWord(FILE_PATH, "Project"));
    }
}
