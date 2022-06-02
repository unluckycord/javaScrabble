public class Player {
    // this is a player object that has a username, score and letters


    private String username;
    private int score;
    private char[] LettersOwned;

    public String getUsername(){
        return username;
    }
    public int getScore(){
        return score;
    }
    public char[] getLettersOwned(){
        return LettersOwned;
    }
    public static void setUsername(String newUsername, Player user){
        user.username = newUsername;
    }
    public void setScore(int newScore, Player user){
        user.score += newScore;
    }
    public void setLettersOwned(char[] newLetters, Player user){
        user.LettersOwned = newLetters;
    }

    Player( String username, int score, char[] LettersOwned){
        this.username = username;
        this.score = score;
        this.LettersOwned = LettersOwned;
    }

}
