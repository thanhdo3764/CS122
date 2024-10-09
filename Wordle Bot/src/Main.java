import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Setup game
        float guessScore = 0;
        int numberOfTrials = 100;
        int wins = 0;
        int fails = 0;
        String[] words = {"colby", "bacon", "hello", "super"};
    //      Wordle game = new WordleGame(words);
        // Usually 10000000 to 1226734006
        Wordle game = new WordleGame("norvig333k.txt", 5, 1300000, 1226734006);
        for (int i = 0; i < numberOfTrials; i++) {
            // Setup bot
            WordleBot bot = new WordleBot();
            // Setup game
            game.initGame();
            bot.beginGame(game);
            Hint h = null;
            int guess = 0;
            int maxGuesses = 6;
            System.out.println("Guessing from " + game.getKnownWords().size() + " words.");
            // Inputs guess into game and receives hints
            while (bot.hasNextGuess() && guess < maxGuesses) {
                guess++;
                h = game.guess(bot.nextGuess());
                h.write();
                System.out.println();
                bot.tell(h);
                // Game win
                if (h.isWin()) {
                    wins++;
                    break;
                }
            }
            guessScore += guess;

            System.out.println("Left over words: " + bot.knownWords);
            System.out.println("Game over: bot " + (h.isWin() ? "won" : "lost") + " with " + guess + " guesses\n");
        }
        System.out.println("Average score for " + numberOfTrials + " trials: " + guessScore/numberOfTrials);
        System.out.println("Number of wins: " + wins);
        System.out.println("Games failed: " + fails);
        System.out.println("Win rate: " + (float)wins/numberOfTrials*100);
    }
}
