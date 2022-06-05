import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*

ADD WAY FOR PLAYERS TO HAVE THIER OWN LETTERS


*/
public class main {
    private static int playerCount = 0;
    private static int AiCount = 0;
    // test
    public static boolean run = true;
    private static HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
    private static HashMap<Character, Integer> letterScore = new HashMap<Character, Integer>();
    private static Player[] players;
    private static Ai[] ais;
    public static Board gameBoard;

    public static void clear() {
        System.out.println("\033[H\033[2J");
    }

    public static void initLetterCountAndScore() {
        // initializes our letter count for our bag
        for (char start = 'A'; start <= 'Z'; start++) {
            letterCount.put(start, Letters.valueOf("" + start).getCount());
            letterScore.put(start, Letters.valueOf("" + start).getScore());
        }
        letterCount.put(' ', Letters.BLANK.getCount());
        letterScore.put(' ', Letters.BLANK.getScore());
    }

    public static void initPlayers(Scanner ask) {
        String newUsername = "";
        System.out.println("How many players are playing? (must be 1-4)");
        playerCount = ask.nextInt();
        players = new Player[playerCount];
        for (int i = 1; i <= playerCount; i++) {
            System.out.println("input username for player " + i + ":");
            newUsername = ask.next();
            players[i - 1] = (new Player(newUsername, 0, GameLogic.intitalLetters(letterCount)));
        }
    }

    public static void initAi(Scanner ask) {
        String AiDifficulty = "";
        String newUsername = "";
        Random rand = new Random();
        System.out.println("How many Ai players will be present? (players + Ai must be less than 4)");
        AiCount = ask.nextInt();
        for (int i = 1; i <= AiCount; i++) {
            newUsername = Assets.Ainames.get(rand.nextInt(26));
            System.out.println("How difficult will (the named Ai) be? (Easy, Medium, or Hard)");

            AiDifficulty = ask.next();
            ais[i - 1] = (new Ai(newUsername, 0, GameLogic.intitalLetters(letterCount), AiDifficulty));
        }

        // Make a code to assign each Ai their own difficulty rating
        /*
         * for (int i = 1; i <= AiCount; i++) {
         * newUsername = random username
         * //Select a name from Ainames.txt while removing the name from the list to
         * prevent doubles
         * Ai[i - 1] = (new Player(newUsername, 0,
         * GameLogic.intitalLetters(letterCount)));
         * //new ai (random number between 0 and Ainames.size()-1 , 0 , getletters)
         * }
         */
    }

    public static int initMenu(Scanner ask) throws IOException {
        Assets.loadAssets();
        String input;
        for (int i = 0; i < Assets.logo.size(); i++) {
            System.out.println(Assets.logo.get(i));
        }
        System.out.println("Welcome to Scrabble! Press A to start, R to veiw rules, E to exit");
        while (true) {
            input = ask.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    return 0;
                case "R":
                    // INSERT RULES HERE

                    System.out.println("rules");
                    break;
                case "E":
                    clear();
                    System.exit(0);
                default:
                    System.out.println("invalid input, please try again.");
            }
        }
    }

    public static void Startgame(Scanner ask) throws IOException {
        // initilizes players, words and board
        initMenu(ask);
        Assets.loadAinames();
        wordStorage.loadingWords();
        initLetterCountAndScore();
        initPlayers(ask);
        initAi(ask);
        clear();
        gameBoard = new Board();
    }

    public static void main(String[] args) throws IOException {
        Scanner ask = new Scanner(System.in);
        clear();
        Startgame(ask);
        // main loop for game
        do {
            for (int indexOfPlayer = 0; indexOfPlayer < playerCount; indexOfPlayer++) {
                System.out.println(gameBoard);
                GameLogic.askForWord(letterScore, letterCount, ask, players[indexOfPlayer], gameBoard, ais[0]);
            }
            for (int indexOfAi = 0; indexOfAi < AiCount; indexOfAi++) {
                System.out.println(gameBoard);
                GameLogic.askForWord(letterScore, letterCount, ask, players[0], gameBoard, ais[indexOfAi]);
            }
        } while (run);
    }

    // invoke this method to test code out
    public static void debugging() {

    }
}
