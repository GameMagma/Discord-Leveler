import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final int SECONDS = 3;
    private static final int MAXIMUM_WORDS = 50;

    public static void main(String[] args) {
        final String PATH = getCurrentDir() + File.separator + "src" + File.separator + "words.txt";

        try {
            Typer typer = new Typer();
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);

            System.out.println("File Exists: " + file.exists() + ". Path: " + file.getPath());

            System.out.println("Success. Typing now.");

            String[] words = getWordsFromFile(scanner);

            typer.typeWords(words, SECONDS, MAXIMUM_WORDS);

        } catch (AWTException e) {
            System.err.println("Failed to create robot. Exiting program.\n");
            e.printStackTrace();
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Could not find file. Exiting program.\n");
            e.printStackTrace();
            System.exit(3);
        }
    }

    private static String[] getWordsFromFile(Scanner scanner) {
        String[] words = new String[500];

        for (int i = 0; i < words.length; i++) {
            words[i] = scanner.nextLine();
        }

        return words;
    }

    /**
     * @return The 'current' or 'default' directory.
     */
    private static String getCurrentDir() {
        return FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    }
}
