import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
/*

ADD WAY FOR PLAYERS TO HAVE THIER OWN LETTERS


*/
public class main{
    private static int playerCount = 0;
    //test
    public static boolean run = true;
    private static HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
    private static HashMap<Character, Integer> letterScore = new HashMap<Character, Integer>();
    private static HashSet<Player> players = new HashSet<Player>();

    public static void initLetterCountAndScore(){
        // initializes our letter count for our bag
        for(char start = 'A'; start <= 'Z'; start++){
            letterCount.put(start, Letters.valueOf(""+start).getCount());
            letterScore.put(start, Letters.valueOf(""+start).getScore());
        }
        letterCount.put(' ', Letters.BLANK.getCount());
        letterScore.put(' ', Letters.BLANK.getScore());
    }
    
    public static void initPlayers(Scanner ask){
        String newUsername = "";
        System.out.println("How many players are playing? (must be 1-4)");
        playerCount = ask.nextInt();
        for(int i = 1; i <= playerCount; i++){
            System.out.println("input username for player " + i + ":");
            newUsername = ask.next();
            Player player = new Player(newUsername, 0);
            players.add(new Player(newUsername, 0));   
        }
        
    }

    

    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        initLetterCountAndScore();
        Scanner ask = new Scanner(System.in);
        debugging();
        /*initPlayers(ask);
        //intPlayers();
        for(int i = 0; i < playerCount; i++){
            GameLogic.intitalLetters(letterCount);
        }
        
        //degubbing();
        do{

            GameLogic.askForWord(letterScore, ask);
        }while(run);
        */
    }
    // invoke this method to test code out
    public static void debugging(){
        for(int i =0; i< 100; i++){
            System.out.print(GameLogic.randomLetter(letterCount));
        }
        //System.out.println(letterCount);
    }
}