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
            /*for (int letter = 0; letter < currentWord.length(); letter++) {
                char currentChar = currentWord.charAt(letter);


                r.keyPress(currentChar);
                r.keyRelease(currentChar);
            } */

            writeString(currentWord);

            r.keyPress(KeyEvent.VK_ENTER); // Enter word

            wordCount++;
            i++;

            if (wordCount == maxWords) {
                System.out.println("Keep typing? y/n:");

                String choice = userInput.next();
                //userInput.reset(); // Clears the buffer

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
