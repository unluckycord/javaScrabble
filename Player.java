public class Player {
    //TOTAL LETTERS = 100
    
    private String username;
    private int score;
    private String LettersOwned;

    public String getUsername(){
        return username;
    }
    public int getScore(){
        return score;
    }
    public String getLettersOwned(){
        return LettersOwned;
    }

    public static void setUsername(String newUsername, Player user){
        user.username = newUsername;
    }
    public void setScore(int newScore, Player user){
        user.score += newScore;
    }
    public void setLettersOwned(String newLetters, Player user){
        user.LettersOwned = newLetters;
    }

    Player( String username, int score, String LettersOwned){
        this.username = username;
        this.score = score;
        this.LettersOwned = LettersOwned;
    }

}
