package tester.files;

import tester.threads.FileSearch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utilities {

    public static final Path WORDCOUNT_FILE = Paths.get("wordCounts.txt");
    public static final Path COPY_FILE = Paths.get("copy.txt");

    public static void inToOut(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[512];
        while (in.read(buffer) > 0) {
            out.write(buffer);
        }
    }

    public static void alphabeticalList(Path from, Path to) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(to);

        Map<String, Long> wordCounter = Files.lines(from)
                .flatMap(l -> Arrays.stream(l.split("\\PL+")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordCounter.forEach((k, v) -> {
            try {
                writer.write(k + ": " + v +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        if (!Files.exists(FileSearch.FILE_PATH)) {
            FileSearch.loadFile(new URL(FileSearch.BOOK_URL));
        }
        alphabeticalList(FileSearch.FILE_PATH, WORDCOUNT_FILE);
        inToOut(Files.newInputStream(FileSearch.FILE_PATH), Files.newOutputStream(COPY_FILE));
        System.out.println(Files.isReadable(COPY_FILE));
        Files.delete(COPY_FILE);
    }
}
