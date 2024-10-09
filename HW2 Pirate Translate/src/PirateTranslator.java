public class PirateTranslator {
    String[] phrases = {"i", "hello", "his", "hers", "hi", "is", "pardon me", "excuse me",
            "my", "friend", "sir", "madam",
            "stranger", "officer",
            "where", "you", "tell",
            "know", "how far", "old", "happy"};
    String[] piratetalk = {"me", "ahoy", "ye", "ye", "yo-ho-ho", "be", "avast", "arrr",
            "me", "me bucko", "matey", "proud beauty",
            "scurvy dog", "foul blaggart",
            "whar", "ye", "be tellin'",
            "be knowin'", "how many leagues",
            "barnacle-covered", "grog-filled"};
    String[] positiveWords = {"adore", "enjoy", "love"};
    String[] negativeWords = {"hate", "despise", "dislike"};
    String lastTranslation = "";
    public String translate(String input) {
        input = " "+input.toLowerCase()+" ";
        // translate to pirate
        String[] punctArr = {" ", ".", ",", "?", "!"};
        for (int i = 0; i < phrases.length; i++) {
            if (input.contains(phrases[i])) {
                for (String punct:punctArr) {
                    input = input.replace(" "+phrases[i]+punct, " "+piratetalk[i]+punct);
                }
            }
        }
        input = input.replace(" me me bucko", " me bucko");
        input = input.trim();
        // checks if input contains positive or negative words
        boolean isPositive = false;
        boolean isNegative = false;
        for (String word:positiveWords) {
            if (input.contains(word)) {
                isPositive = true;
                break;
            }
        }
        for (String word:negativeWords) {
            if (input.contains(word)) {
                isNegative = true;
                break;
            }
        }
        // adds suffix
        if (isPositive && !isNegative) input += " 'tis like me pirate treasure!";
        else if (isNegative && !isPositive) input += " 'tis like bein' food for the fish!";
        // checks for double translation
        if (lastTranslation.equals(input)) return "I be a translator, not a puppet!";
        lastTranslation = input;
        return input;
    }
}