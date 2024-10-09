public class HintGame implements Hint{
    private String correctlyPlaced = "";
    private String incorrectlyPlaced = "";
    private String notInPuzzle = "";
    private String guess;

    /**
     * @param guess
     * @param secretWord
     */
    public HintGame(String guess, String secretWord) {
        guess = guess.toLowerCase();
        this.guess = guess;
        // Determines correctlyPlaced
        for (int i=0; i < secretWord.length(); i++) {
            char c = guess.charAt(i);
            if (c == secretWord.charAt(i)) {
                correctlyPlaced += secretWord.charAt(i);
            } else {
                correctlyPlaced += "-";
            }
        }
        // Determines incorrectlyPlaced
        for (int i=0; i < secretWord.length(); i++) {
            char c = guess.charAt(i);
            int charsInIncorrect = numberOfChars(incorrectlyPlaced, c);
            int charsInCorrect = numberOfChars(correctlyPlaced, c);
            int charsInSecret = numberOfChars(secretWord, c);
            // Checks for non-redundant characters to add to incorrectlyPlaced
            // All redundant characters are ignored, hence will be added to notInPuzzle
            if (charsInIncorrect+charsInCorrect < charsInSecret && correctlyPlaced.charAt(i) != c) {
                incorrectlyPlaced += guess.charAt(i);
            } else {
                incorrectlyPlaced += "-";
            }
        }
        // Determines notInPuzzle
        for (int i=0; i < secretWord.length(); i++) {
            char c = guess.charAt(i);
            // If this spot is not correctlyPlaced and not incorrectlyPlaced
            if (correctlyPlaced.charAt(i) == '-' && incorrectlyPlaced.charAt(i) == '-') {
                notInPuzzle += c;
            }
        }
    }
    public int numberOfChars(String word, char c) {
        int count = 0;
        for (int i=0; i < word.length(); i++) {
            if (word.charAt(i) == c) count++;
        }
        return count;
    }
    public boolean isWin() {
        // true iff the '-' isn't in the correctlyPlaced String...
        return (correctlyPlaced.indexOf('-') == -1);
    }

    /**
     * Displays hint for guessed word
     */
    public void write() {
        System.out.println("---- Hint (" + guess + ") ----");
        System.out.println("Correctly placed  : " + correctlyPlaced);
        System.out.println("Incorrectly placed: " + incorrectlyPlaced);
        System.out.println("Not in the puzzle : [" + notInPuzzle + "]");
    }
    public String getCorrectlyPlaced() {
        return correctlyPlaced;
    }
    public String getIncorrectlyPlaced() {
        return incorrectlyPlaced;
    }
    public String getNotInPuzzle() {
        return notInPuzzle;
    }
}
