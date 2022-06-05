public class Ai {
    // this is a player object that has a username, score and letters

    private String username;
    private int score;
    private char[] LettersOwned;
    private String AiDifficulty;

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

    public void setAiDifficulty(String AiDifficulty, Ai user) {
        user.AiDifficulty = AiDifficulty;
    }

    Ai(String username, int score, char[] LettersOwned, String AiDifficulty) {
        this.username = username;
        this.score = score;
        this.LettersOwned = LettersOwned;
        this.AiDifficulty = AiDifficulty;
    }

}

/*
 * public class Ai {
 * String name;
 * public Ai(){
 * name = "";
 * }
 * 
 * public void setName(String newName){
 * name = newName;
 * }
 * 
 * import java.util.Arrays;
 * import java.util.Collections;
 * import java.util.List;
 * 
 * public class AiNames {
 * public static void main(String[] args) {
 * String[] peoples = {"Alphonse","Beatrice","Catherine","Devon"};
 * List<String> names = Arrays.asList(peoples);
 * Collections.shuffle(names);
 * for (String name : names) {
 * System.out.print(name + " ");
 * }
 * }
 * }
 */