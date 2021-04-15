import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class Main {
    private static final int SECONDS = 3;
    private static final int MAXIMUM_WORDS = 50;

    public static void main(String[] args) {
        final String PATH = getCurrentDir() + File.separator + "src" + File.separator + "words.txt";

        Scanner mainUserInput = new Scanner(System.in);

        System.out.println("Short or long run? (Short/Long): ");
        String choice = mainUserInput.nextLine().toLowerCase();

        if (choice.equals("short")) {
            shortRun(PATH);
        } else if (choice.equals("long")) {
            longRun(PATH);
        } else {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Runs through the program just once, asking everytime it reaches the maximum words if you want to stop.
     *
     * @param PATH The path of the text file to get the words from
     */
    private static void shortRun(final String PATH) {
        try {
            Typer typer = new Typer();
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);

            System.out.println("File Exists: " + file.exists() + ". Path: " + file.getPath()); // Log whether the file exists/could be found and the path it was found at

            System.out.println("Success. Typing now.");

            String[] words = getWordsFromFile(scanner); // Get all words from the file, and put it into an array

            typer.typeWords(words, SECONDS, MAXIMUM_WORDS); // Call method to type words repeatedly

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

    /**
     * Runs the code repeatedly, but does not ask for confirmation every so often. Also loops around to the beginning
     * of the file if it runs out of words. Will stop after a preset amount of time, but this time is generally around 8
     * or so hours.
     *
     * @param PATH The path of the text file to get the words from
     */
    private static void longRun(final String PATH) {
        try {
            Typer typer = new Typer();
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);

            System.out.println("File Exists: " + file.exists() + ". Path: " + file.getPath());

            System.out.println("Success. Typing now.");

            String[] words = getWordsFromFile(scanner);

            typer.typeWordsWithoutConfirmation(words, SECONDS);

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
