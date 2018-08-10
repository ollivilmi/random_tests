package tester.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFiles {

    public static final Path HELLO_WORLD = Paths.get("helloworld.txt");

    public static void saveHelloWorld() {
        Properties settings = new Properties();
        settings.put("title", "Hello World!");
        try (OutputStream out = Files.newOutputStream(HELLO_WORLD)) {
            settings.store(out, "Program properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printProperties() {
        Properties settings = new Properties();
        try (InputStream in = Files.newInputStream(HELLO_WORLD)) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        settings.forEach((k,v) -> {
            System.out.println(k + ": " + v);
        });
    }

    public static void main(String[] args) {
        saveHelloWorld();
        printProperties();
    }
}
