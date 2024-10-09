import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * The WordleGame class exists only to as a place to store this main method!
 *
 */
public class WordleGame {
    public static void main(String[] args) throws IOException {
        // Limit guesses
        int maxGuesses = 6;
        int nGuesses = 0;
        //String[] shortlist = {"Raster"};
        //Wordle puzzle = new Wordle(shortlist);
        //Starts game loading words from file
        Wordle puzzle = new Wordle("norvig333k.txt", 5, 999999, 0);
        puzzle.initGame();
        //System.out.println("The word is: " + puzzle.getSecretWord());
        // The scanner will read from System.in (i.e., keyboard input)
        System.out.print("Your guess: ");
        Scanner scan = new Scanner(System.in);
        // Prints out guess and hints
        Hint h = null;
        while(scan.hasNext() && nGuesses < maxGuesses) {
            nGuesses += 1;
            String token = scan.next();
            System.out.println("  Got: '" + token + "'");
            try {
                h = puzzle.guess(token);
                h.write();
                System.out.println("");
                if ( h.isWin() ) {
                    System.out.println("You won in " + nGuesses + " moves!");
                    break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("... try again...");
                nGuesses -= 1; // let's not count that guess!
            }
            // print the prompt for the next guess...
            System.out.print("Your guess: ");
        }
        if (!h.isWin()) {
            System.out.println("The word was: " + puzzle.getSecretWord());
            System.out.println("Too many moves!  You lose!");
        }
    }
}
