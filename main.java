import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
        System.out.println("[H\033[2J");
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
        ais = new Ai[AiCount];
        for (int i = 0; i < AiCount; i++) {
            newUsername = Assets.Ainames.get(rand.nextInt(26));
            System.out.println("How difficult will (the named Ai) be? (Easy, Medium, or Hard)");

            AiDifficulty = ask.next().toUpperCase();
            ais[i] = (new Ai(newUsername, 0, GameLogic.intitalLetters(letterCount), AiDifficulty));
        }
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
                    // Rules sourced from
                    // https://users.cs.northwestern.edu/~robby/uc-courses/22001-2008-winter/scrabble.html

                    System.out.println(
                            "\n \n \nGames only have two players. \nEach match has two games, one with each player playing first.\n \nPlayers initially draw 7 tiles each and place them on their rack.\n \nThe first player combines two or more of his or her letters to form a word \nand places it on the board to read either across or down with one letter on the center square. \nAfter playing a word, the player receives replacement letters, one for each letter played.\n \nFollowing the first turn, players alternate. Each plays a series of tiles forming a word \n(possibly more than one word, as below) and then draws new tiles. \nAlways keep 7 tiles on each rack, unless there are not enough tiles left.\n \nThe letters placed in a single turn must all be in a single horizontal row or in a single \nvertical column (Diagonal words are not allowed.), and the letters placed \n(plus letters already on the board) must form a single word from the dictionary, with no gaps. \nEach new word must connect to the existing words, in one of the following ways:\n \n---Adding one or more letters to a word or letters already on the board.\n---Placing a word at right angles to a word already on the board. The new word must use one \nof the letters already on the board or must add a letter to one of the words on the board.\n---Placing a complete word parallel to a word already played so that adjacent letters also \nform complete words. \n \nAny new words formed by these connections must also be in the dictionary.\n \nEach of the two blank tiles may be used as any letter. When playing a blank, you must state \nwhich letter it represents. It remains that letter for the rest of the game.\n \nYou may use a turn to exchange all, some, or none of the letters. To do this, place your \ndiscarded letter(s) facedown. Draw the same number of letters from the pool of \nremaining letters, and then mix your discarded letter(s) into the pool. \nThis ends your turn. You may not exchange more tiles from your rack than are in the pool (or \nare in your rack, of course), but there is no other limit on how many tiles you may exchange.\n \nThe game ends when:\n \n---all the letters are in the player's racks or on the board, & one player uses their last letter,\n---or---\n---both players exchange some number of tiles twice in a row (for a total of four exchanges).");
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

    public static void PlayerLoop(Board gameBoard, HashMap<Character, Integer> letterScore,
            HashMap<Character, Integer> letterCount, Scanner ask, Player[] players) {
        for (int indexOfPlayer = 0; indexOfPlayer < playerCount; indexOfPlayer++) {
            System.out.println(gameBoard);
            GameLogic.playerMove(letterScore, letterCount, ask, players[indexOfPlayer], gameBoard);
        }
    }

    public static void AiLoop(Board gameBoard, HashMap<Character, Integer> letterScore,
            HashMap<Character, Integer> letterCount, Ai[] ais) {
        for (int indexOfAi = 0; indexOfAi < AiCount; indexOfAi++) {
            System.out.println(gameBoard);
            GameLogic.AiLogic(letterScore, letterCount, ais[indexOfAi], gameBoard);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner ask = new Scanner(System.in);
        clear();
        Startgame(ask);
        // main loop for game
        do {
            PlayerLoop(gameBoard, letterScore, letterCount, ask, players);
            // checks to see if there are any ais, if there are none it skips over it
            if (ais.length > 0) {
                AiLoop(gameBoard, letterScore, letterCount, ais);
            }
        } while (run);
    }

    // invoke this method to test code out
    public static void debugging() {

    }
}
