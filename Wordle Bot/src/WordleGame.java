import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordleGame implements Wordle {
    private ArrayList<String> knownWords = new ArrayList<>();
    private String secretWord;
    private Random rnd = new Random(); // a source of random numbers
    static int RULES_ALLOW_ONLY_WORD_GUESSES = 0;

    /**
     * Create a new Wordle game, loading allowable words from a file.
     *
     * @param file    - the file from which to load 'known words'
     * @param length  - the minimum length word
     * @param minfreq - the minimum allowed frequency
     * @param maxfreq - the maximum allowed frequency
     */
    public WordleGame(String file, int length, long minfreq, long maxfreq) throws IOException {
        // ~12K 5 letter words with min frequency of 100,000
        loadWords(file, length, minfreq, maxfreq);
    }

    /**
     * _Part 1: Implement this constructor._
     * Load known words from the array that is supplied.
     * @param words - an array of words to load
     */
    public WordleGame(String[] words) {
        int l = words[0].length();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == l) knownWords.add(words[i].toLowerCase());
        }
    }
    public String getSecretWord() {
        return secretWord;
    }
    public int numberOfKnownWords() {
        return knownWords.size();
    }

    /**
     * _Part 2: Implement this method._
     * Scans a file with filenm that contains words and their corresponding frequency.
     * If the word is the specified length and frequency range,
     * Then add it to knownWords.
     *
     * @param filenm  - the file name to load from
     * @param length  - the length of words we want to load (e.g., 5 to load 5 character words)
     * @param minfreq - the minimum allowable frequency for a loaded word
     * @param maxfreq - the maximum allowable frequenct for a loaded word; 0 indicates no maximum
     */
    public void loadWords(String filenm, int length, long minfreq, long maxfreq) throws IOException {
        Scanner scan = new Scanner(new File(filenm));
        while (scan.hasNext() == true) {
            String s = scan.next();
            long freq = scan.nextLong();
            if (freq < minfreq) break; // Assuming that frequency in file is ordered
            if (s.length() == length && freq >= minfreq && (freq <= maxfreq || maxfreq == 0)) {
                knownWords.add(s.toLowerCase());
            }
        }
    }

    /**
     * _Part 3: Implement this method._
     * Obtain a list of known words. This method creates a new copy of the known words list.
     *
     * @return a new copy of list of known words.
     */
    public ArrayList<String> getKnownWords() {
        ArrayList<String> copy = new ArrayList<>();
        for (String knownWord : knownWords) {
            copy.add(knownWord);
        }
        return copy;
    }

    /**
     * Prepare the game for playing by choosing a new secret word.
     */
    public void initGame() {
        Random r = new Random();
        secretWord = knownWords.get(r.nextInt(knownWords.size()));;
    }
    public void initGame(String word) {
        secretWord = word;
    }

    /**
     * Supply a guess and get a hint!
     *
     * @param g - the guess (a string which is the same length as the secret word)
     * @return a hint indicating the letters guessed correctly/incorrectly
     * @throws IllegalArgumentException if the guess is not the same length as the secret word
     */
    public HintGame guess(String g) {
        int length = secretWord.length();
        if (length != g.length()) {
            throw new IllegalArgumentException("Wrong length guess!");
        }
        return new HintGame(g, secretWord);
    }
    public int getRules() {
        return 0;
    }
}
