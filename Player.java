public class Player {
    //TOTAL LETTERS = 100
    
    private static String username;
    private static int score;
    private static String LettersOwned;

    public static String getUsername(){
        return username;
    }
    public static int getScore(){
        return score;
    }
    public String getLettersOwned(){
        return LettersOwned;
    }

    public static void setUsername(String newUsername, Player user){
        Player.username = newUsername;
    }
    public static void setScore(int newScore, Player user){
        Player.score += newScore;
    }
    public void setLettersOwned(String newLetters, Player user){
        Player.LettersOwned = newLetters;
    }

    Player( String username, int score, String LettersOwned){
        Player.username = username; 
        Player.score = score;
        Player.LettersOwned = LettersOwned;
    }

}
