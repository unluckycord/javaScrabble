public class Ai {
    // this is a player object that has a username, score and letters

    private String username;
    private int score;
    private char[] LettersOwned;

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public char[] getLettersOwned() {
        return LettersOwned;
    }

    public static void setUsername(String newUsername, Ai user) {
        user.username = newUsername;
    }

    public void setScore(int newScore, Ai user) {
        user.score += newScore;
    }

    public void setLettersOwned(char[] newLetters, Ai user) {
        user.LettersOwned = newLetters;
    }

    Ai(String username, int score, char[] LettersOwned) {
        this.username = username;
        this.score = score;
        this.LettersOwned = LettersOwned;
    }

}