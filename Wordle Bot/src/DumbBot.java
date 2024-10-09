import java.util.ArrayList;

/**
 * DumbBot.  Grab the list of known words, guess one by one.
 */
public class DumbBot implements WordlePlayer {
    ArrayList<String> knownWords;
    int guessNumber;

    public DumbBot() { }

    public void beginGame(Wordle game) {
        knownWords = game.getKnownWords();
        guessNumber = 0;
    }
    public boolean hasNextGuess() {
        return (guessNumber < knownWords.size());
    }
    public String nextGuess() {
        return knownWords.get(guessNumber++);
    }
    public void tell(Hint h) {
        // DumbBot ignores hints!
    }

    public static void main(String[] args) {
        String[] words = {"colby", "bacon"};
        Wordle game = new WordleGame(words);
        game.initGame();

        DumbBot bot = new DumbBot();
        bot.beginGame(game);
        Hint h = null;
        int guess = 0;
        int maxGuesses = 100;
        while(bot.hasNextGuess() && guess < maxGuesses) {
            guess++;
            h = game.guess(bot.nextGuess());
            bot.tell(h);
            if (h.isWin()) {
                break;
            }
        }
        System.out.println("Game over: bot " + (h.isWin()?"won":"lost") + " with " + guess + " guesses");
    }
}
