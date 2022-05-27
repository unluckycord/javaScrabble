public class Player {
    //TOTAL LETTERS = 100
    
    private String username;
    private int score;

    public String getUsername(){
        return username;
    }
    public int getScore(){
        return score;
    }
    public static void setUsername(String newUsername, Player user){
        user.username = newUsername;
    }
    public void setScore(int newScore, Player user){
        user.score += newScore;
    }

    Player( String username, int score){
        this.username = username; 
        this.score = score;
    }

}
