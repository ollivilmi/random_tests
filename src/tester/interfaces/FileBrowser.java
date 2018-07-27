package tester.interfaces;

import java.io.File;
import java.util.Arrays;
import static java.util.Comparator.*;

public class FileBrowser {

    public static final String PATH = "src/tester/interfaces";

    private static File[] getDirectories(String path)
    {
        File file = new File(path);
        File[] directories = file.listFiles(f -> f.isDirectory());
        Arrays.asList(directories).forEach(FileBrowser::printAbsolutePath);
        return directories;
    }

    private static File[] getFiles(String path)
    {
        File file = new File(path);
        File[] files = file.listFiles(f -> f.isFile());
        Arrays.asList(files).forEach(FileBrowser::printAbsolutePath);
        return files;
    }

    private static void sortFiles(File[] files)
    {
        Arrays.sort(files, comparing(File::isFile).thenComparing(File::compareTo));
        Arrays.sort(files, comparing((File f) -> f.isFile()).thenComparing((File f1, File f2) -> f1.compareTo(f2)));
    }

    private static void printAbsolutePath(File f)
    {
        System.out.println(f.getAbsolutePath());
    }

    public static void main(String[] args) {
        getDirectories(PATH);
        System.out.println();

        getFiles(PATH);
        System.out.println();

        File[] allFiles = new File(PATH).listFiles();
        sortFiles(allFiles);
        Arrays.asList(allFiles).forEach(FileBrowser::printAbsolutePath);
    }
}
