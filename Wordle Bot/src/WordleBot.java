import java.util.*;

// Thanh Do
//
// Hint Based Elimination
// When given a hint, the tell() method will remove knownWords that aren't consistent with the hint.
// In the tell() method, there is a for loop that loops through each word in copyKnownWords.
// Then, there are 3 different for loops that check if the word is consistent
// with notInPuzzle, inCorrectlyPlaced, then correctlyPlaced.
// If the word fails any consistency test, then it is removed from knownWords,
// and the loops continues to for the next word in copyKnownWords.
//
// Stochastic Ordering
// From a list of words with the highest score, a random word will be chosen.
// In nextGuess(), an array scoreArray stores the scores of words from knownWords where scoreArray[i]
// is the score for the word knownWords[i].
// Then, a maxScore is found, and the words with that score will be stored in a list maxScoreWords.
// Then, a random word is chosen and returned from maxScoreWords.
//
// Scoring Letter Frequency
// Scores a word based on what letters it contains.
// In scoreKnownWords, there's 3 arrays of letters, where tier1 chars occur more frequently than tier2.
// For each word in knownWords, if the word contains tier1 chars, then 3 point is incremented to its score.
// If the word contains tier2 chars, then 2 point is incremented to its score.
// For tier3 chars, 1 point is incremented.
// Other tests also occur but are not listed in this strategy.
// After the total score is added, the score is added to its corresponding index in wordScores.
//
// Scoring Suffixes/StartsWith
// Scores a word if it contains certain suffixes/starting letters.
// In scoreKnownWords, there's an array suffixes and startsWith with an array of frequently occurring suffixes/starting letters.
// For each word in knownWords, if the word ends with a suffix or starts with a starting letter, then 1 point is incremented to its score.
// The strategy before this paragraph also occurs.
// After the total score is added, the score is added to its corresponding index in wordScores.

public class WordleBot implements WordlePlayer {
    ArrayList<String> knownWords;
    int guessNumber;
    int initialSize;
    public WordleBot() {
    }

    public void beginGame(Wordle game) {
        knownWords = game.getKnownWords();
        guessNumber = 0;
        initialSize = knownWords.size();
    }

    public boolean hasNextGuess() {
        return (guessNumber < initialSize);
    }

    public String nextGuess() {
        guessNumber++;
        Random r = new Random();
        // Stores the score of knownWords in an array
        int[] scoreArray = scoreKnownWords();
        ArrayList<String> maxScoreWords = new ArrayList<>();
        // Finds the maxScore
        int maxScore = 0;
        for (int score : scoreArray) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        // Copies words with the maxScore into list maxScoreWords
        for (int i = 0; i < knownWords.size(); i++) {
            if (scoreArray[i] == maxScore) {
                maxScoreWords.add(knownWords.get(i));
            }
        }
        // Returns a random maxScoreWords and removes that word from knownWords
        String chosenWord = maxScoreWords.get(r.nextInt(maxScoreWords.size()));
        knownWords.remove(chosenWord);
        return chosenWord;
    }

    public int[] scoreKnownWords() {
//        // Array of frequently occurring letters in 333k
//        char[] tier1 = {'e','s','a','r'};
//        char[] tier2 = {'o','t','l','i','n','d','c','h','u','p'};
//        char[] tier3 = {'m','g','b','k','y','f','w','v','x','j','q','z'};
//        // Array of frequently occurring starting letters in 333k
//        char[] startsWith = {'s','c','t','b','a'};
//        // Array of frequently occurring suffixes
//        String[] suffixes = {"ing","ed","ly","s"};
        int[] charFreq = new int[26];
        for (String word : knownWords) {
            int i = 0;
            for (char ch : word.toCharArray()) {
                if (word.indexOf(ch) == i++) charFreq[ch-97]++;
            }
        }
        int[] wordScores = new int[knownWords.size()];
        int index = 0;
        // Scores each word in knownWords based on heuristics
        for (String word : knownWords) {
            int scoreCount = 0;
            int j = 0;
            for (char ch : word.toCharArray()) {
                if (word.indexOf(ch) == j++) scoreCount += charFreq[ch-97];
            }
//            // Scores for suffixes
//            for (String s : suffixes) {
//                if (word.endsWith(s)) scoreCount += 0;
//            }
//            // Scores for tier1 chars
//            for (char c : tier1) {
//                if (word.indexOf(c) != -1) scoreCount += 5;
//            }
//            // Scores for tier2 chars
//            for (char c : tier2) {
//                if (word.indexOf(c) != -1) scoreCount += 3;
//            }
//            // Scores for tier3 chars
//            for (char c: tier3) {
//                if (word.indexOf(c) != -1) scoreCount += 2;
//            }
//            // Scores for startsWith
//            for (char c : startsWith) {
//                if (word.charAt(0) == c) scoreCount += 5;
//            }
            wordScores[index] = scoreCount;
            index++;
        }
        return wordScores;
    }
    public void tell(Hint h) {
        ArrayList<String> copyKnownWords = new ArrayList<>(knownWords);
        for (String word : copyKnownWords) {
            boolean removed = false;
            // Compares notInPuzzle
            for (int i = 0; i < h.getNotInPuzzle().length(); i++) {
                char notInPuzzleChar = h.getNotInPuzzle().charAt(i);
                boolean correctContainsNIPC = h.getCorrectlyPlaced().indexOf(notInPuzzleChar) != -1;
                boolean incorrectContainsNIPC = h.getIncorrectlyPlaced().indexOf(notInPuzzleChar) != -1;
                boolean containsNIPC = word.indexOf(notInPuzzleChar) != -1;
                // If a char in notInPuzzle is actually not in the secret word and the word contains notInPuzzleChar
                if (!correctContainsNIPC && !incorrectContainsNIPC && containsNIPC) {
                    knownWords.remove(word);
                    removed = true;
                    break;
                }
            }
            // Compares incorrectlyPlaced
            if (!removed) {
                for (int j = 0; j < h.getIncorrectlyPlaced().length(); j++) {
                    char incorrectChar = h.getIncorrectlyPlaced().charAt(j);
                    boolean containsIncorrectChar = word.indexOf(incorrectChar) != -1;
                    boolean isIncorrectPlace = word.charAt(j) != incorrectChar;
                    // If the word doesn't contain incorrectChar or the incorrectChar is in the right place
                    if ((!containsIncorrectChar || !isIncorrectPlace) && incorrectChar != '-') {
                        knownWords.remove(word);
                        removed = true;
                        break;
                    }
                }
            }
            // Compares correctlyPlaced
            if (!removed) {
                for (int k = 0; k < h.getCorrectlyPlaced().length(); k++) {
                    char correctChar = h.getCorrectlyPlaced().charAt(k);
                    if ((word.indexOf(correctChar) == -1 || correctChar != word.charAt(k)) && correctChar != '-') {
                        knownWords.remove(word);
                        break;
                    }
                }
            }
        }
    }

}
