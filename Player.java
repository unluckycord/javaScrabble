public enum Player {
    //TOTAL LETTERS = 100
    PLAYERONE("", 0),
    PLAYERTWO("", 0),
    PLAYERTHREE("", 0),
    PLAYERFOUR("", 0);
    
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

    Player(final String username, final int score){
        this.username = username; 
        this.score = score;
    }

}
