import java.io.IOException;
import java.util.HashMap;
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
    private static HashMap<String, Integer> players = new HashMap<String, Integer>();

    public static void intLetterCountAndScore(){
        // initializes our letter count for our bag
        for(char start = 'A'; start <= 'Z'; start++){
            letterCount.put(start, Letters.valueOf(""+start).getCount());
            letterScore.put(start, Letters.valueOf(""+start).getScore());
        }
        letterCount.put(' ', Letters.BLANK.getCount());
        letterScore.put(' ', Letters.BLANK.getScore());
    }
    
    public static void intPlayers(){
        Scanner askForPlayerUsername = new Scanner(System.in);
        String newUsername = "";
        Scanner askForPlayerCount = new Scanner(System.in);
        System.out.println("How many players are playing? (must be 1-4)");
        playerCount = askForPlayerCount.nextInt();
        switch(playerCount){
            case 1:
                System.out.println("input username for player 1:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERONE);
                players.put(Player.PLAYERONE.getUsername(), Player.PLAYERONE.getScore());
                
                askForPlayerCount.close();
                askForPlayerUsername.close();
                break;
            
            case 2:
                System.out.println("input username for player 1:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERONE);
                players.put(Player.PLAYERONE.getUsername(), Player.PLAYERONE.getScore());
                
                System.out.println("input username for player 2:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERTWO);
                players.put(Player.PLAYERTWO.getUsername(), Player.PLAYERTWO.getScore());
                
                askForPlayerCount.close();
                askForPlayerUsername.close();
                break;
            case 3:
                System.out.println("input username for player 1:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERONE);
                players.put(Player.PLAYERONE.getUsername(), Player.PLAYERONE.getScore());

                System.out.println("input username for player 2:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERTWO);
                players.put(Player.PLAYERTWO.getUsername(), Player.PLAYERTWO.getScore());

                System.out.println("input username for player 3:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERTHREE);
                players.put(Player.PLAYERTHREE.getUsername(), Player.PLAYERTHREE.getScore());
                
                askForPlayerCount.close();
                askForPlayerUsername.close();
                break;
            case 4:
                System.out.println("input username for player 1:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERONE);
                players.put(Player.PLAYERONE.getUsername(), Player.PLAYERONE.getScore());

                System.out.println("input username for player 2:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERTWO);
                players.put(Player.PLAYERTWO.getUsername(), Player.PLAYERTWO.getScore());
                
                System.out.println("input username for player 3:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERTHREE);
                players.put(Player.PLAYERTHREE.getUsername(), Player.PLAYERTHREE.getScore());

                System.out.println("input username for player 4:");
                newUsername = askForPlayerCount.next();
                Player.setUsername(newUsername, Player.PLAYERFOUR);
                players.put(Player.PLAYERFOUR.getUsername(), Player.PLAYERFOUR.getScore());
                
                askForPlayerCount.close();
                askForPlayerUsername.close();
                break;
                
            default:
                System.out.println("wrong number, please try again.");
        }
    }

    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        intLetterCountAndScore();
        //intPlayers();
        //for(int i = 0; i < playerCount; i++){
            GameLogic.intitalLetters(letterCount);
        //}

        Scanner userWord = new Scanner(System.in);
        Scanner blankLetterPick = new Scanner(System.in);
        
        //degubbing();
        while(run){
            GameLogic.askForWord(userWord,blankLetterPick,letterScore);
        }
    }
    // invoke this method to test code out
    public static void degubbing(){
    }
}