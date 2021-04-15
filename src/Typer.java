import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Typer {
    private Robot r;

    public Typer() throws AWTException {
        r = new Robot();
    }

    /**
     * Types words repeatedly
     *
     * @param words An array of the available words
     * @param seconds How many seconds in between each word
     * @param maxWords How many words to type between each exit attempt
     */
    public void typeWords(String[] words, int seconds, final int maxWords) {
        int delay = seconds * 1000;
        int wordCount = 0; // Counts how many words have been typed. Reset everytime it hits the max
        int i = 0; // Index for the word
        boolean stay = true; // If program should keep typing

        Scanner userInput = new Scanner(System.in); // Scanner for user input

        while (stay) {
            String currentWord = words[i];

            r.delay(delay);

            // Type word
            writeString(currentWord);

            r.keyPress(KeyEvent.VK_ENTER); // Enter word

            wordCount++;
            i++;

            if (wordCount == maxWords) {
                System.out.println("Keep typing? y/n:");

                String choice = userInput.next();

                stay = (!choice.equals("n") && i < 500);

                if (stay) {
                    System.out.println("Continuing.");
                } else {
                    System.out.println("Stopping.");
                }

                wordCount = 0; // Resets the counter
            }
        }
    }

    /**
     * Types words repeatedly
     *
     * @param words An array of the available words
     * @param seconds How many seconds in between each word
     */
    public void typeWordsWithoutConfirmation(String[] words, int seconds) {
        int delay = seconds * 1000;
        int i = 0; // Index for the word
        boolean stay = true; // If program should keep typing

        // Timer variables
        long start = System.currentTimeMillis(); // The current time in milliseconds
        //final long maxTime = 28800000; // 8 hours in milliseconds
        final long maxTime = 3600000; // 1 hour in milliseconds

        Scanner userInput = new Scanner(System.in); // Scanner for user input
        r.delay(5000);
        while (stay) {
            String currentWord = words[i];

            r.delay(delay);

            // Type word
            writeString(currentWord);

            r.keyPress(KeyEvent.VK_ENTER); // Enter word

            i++; // Increment index

            long currentTime = System.currentTimeMillis() - start;

            stay = (currentTime < maxTime);

            System.out.println("Current time: " + currentTime + " | i = " + i + " | Stay = " + stay + " | Word Printed: " + currentWord); // Log info to console for debugging

            if (i >= 500) { i = 0; }
        }
    }

    private void writeString(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                r.keyPress(KeyEvent.VK_SHIFT);
            }
            r.keyPress(Character.toUpperCase(c));
            r.keyRelease(Character.toUpperCase(c));

            if (Character.isUpperCase(c)) {
                r.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
}
