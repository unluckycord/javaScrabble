import java.io.IOException;
import java.util.ArrayList;
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
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static String[][] gameBoard = new String[16][16];

    public static void clear(){
        System.out.println("\033[H\033[2J");
    }

    public static void initBoard(){
        Board Board = new Board(gameBoard);
        for(int i = 0; i < 10; i++){
            gameBoard[0][i] = "\u001B[35m"+i+" ";
            gameBoard[i][0] = "\u001B[35m"+i +"   ";
        }
        for(int i = 10; i < 16; i++){
            gameBoard[0][i] = "\u001B[35m"+i;
            gameBoard[i][0] = "\u001B[35m"+i +"  ";
        }
        for(int i = 1; i< 16; i++){
            for(int f = 1; f < 16; f++){
                gameBoard[f][i] = "\u001B[37m[  ]";
            }
        }
        // left value is x right value is y

        //triple word scores
        gameBoard[1][1] = "\u001B[33m[tw]";
        gameBoard[8][1] = "\u001B[33m[tw]";
        gameBoard[15][1] = "\u001B[33m[tw]";
        gameBoard[1][8] = "\u001B[33m[tw]";
        gameBoard[15][8] = "\u001B[33m[tw]";
        gameBoard[1][15] = "\u001B[33m[tw]";
        gameBoard[8][15] = "\u001B[33m[tw]";
        gameBoard[15][15] = "\u001B[33m[tw]";

        //double word score
        gameBoard[2][2] = "\u001B[31m[dw]";
        gameBoard[14][2] = "\u001B[31m[dw]";
        gameBoard[3][3] = "\u001B[31m[dw]";
        gameBoard[13][3] = "\u001B[31m[dw]";
        gameBoard[4][4] = "\u001B[31m[dw]";
        gameBoard[12][4] = "\u001B[31m[dw]";
        gameBoard[5][5] = "\u001B[31m[dw]";
        gameBoard[11][5] = "\u001B[31m[dw]";
        gameBoard[2][14] = "\u001B[31m[dw]";
        gameBoard[14][14] = "\u001B[31m[dw]";
        gameBoard[3][13] = "\u001B[31m[dw]";
        gameBoard[13][13] = "\u001B[31m[dw]";
        gameBoard[4][12] = "\u001B[31m[dw]";
        gameBoard[12][12] = "\u001B[31m[dw]";
        gameBoard[5][11] = "\u001B[31m[dw]";
        gameBoard[11][11] = "\u001B[31m[dw]";

        //triple letter score
        gameBoard[6][2] = "\u001B[34m[tl]";
        gameBoard[10][2] = "\u001B[34m[tl]";
        gameBoard[2][6] = "\u001B[34m[tl]";
        gameBoard[6][6] = "\u001B[34m[tl]";
        gameBoard[10][6] = "\u001B[34m[tl]";
        gameBoard[14][6] = "\u001B[34m[tl]";
        gameBoard[2][10] = "\u001B[34m[tl]";
        gameBoard[6][10] = "\u001B[34m[tl]";
        gameBoard[10][10] = "\u001B[34m[tl]";
        gameBoard[14][10] = "\u001B[34m[tl]";
        gameBoard[6][14] = "\u001B[34m[tl]";
        gameBoard[10][14] = "\u001B[34m[tl]";
        
        //double letter score
        gameBoard[4][1] = "\u001B[36m[dl]";
        gameBoard[12][1] = "\u001B[36m[dl]";
        gameBoard[7][3] = "\u001B[36m[dl]";
        gameBoard[9][3] = "\u001B[36m[dl]";
        gameBoard[1][4] = "\u001B[36m[dl]";
        gameBoard[8][4] = "\u001B[36m[dl]";
        gameBoard[15][4] = "\u001B[36m[dl]";
        gameBoard[3][7] = "\u001B[36m[dl]";
        gameBoard[7][7] = "\u001B[36m[dl]";
        gameBoard[9][7] = "\u001B[36m[dl]";
        gameBoard[13][7] = "\u001B[36m[dl]";
        gameBoard[4][8] = "\u001B[36m[dl]";
        gameBoard[12][8] = "\u001B[36m[dl]";
        gameBoard[3][9] = "\u001B[36m[dl]";
        gameBoard[7][9] = "\u001B[36m[dl]";
        gameBoard[9][9] = "\u001B[36m[dl]";
        gameBoard[13][9] = "\u001B[36m[dl]";
        gameBoard[1][12] = "\u001B[36m[dl]";
        gameBoard[8][12] = "\u001B[36m[dl]";
        gameBoard[15][12] = "\u001B[36m[dl]";
        gameBoard[7][13] = "\u001B[36m[dl]";
        gameBoard[9][13] = "\u001B[36m[dl]";
        gameBoard[4][15] = "\u001B[36m[dl]";
        gameBoard[12][15] = "\u001B[36m[dl]";

        
        //star in the middle
        gameBoard[8][8] = "[\u001B[32m★★\u001B[37m]";        
        
        
    }
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
            players.add(new Player(newUsername, 0, GameLogic.intitalLetters(letterCount)));   
        }
        
    }
    
    public static int initMenu(Scanner ask) throws IOException{
        Assets.loadAssets();
        String input;
        for(int i = 0; i < Assets.logo.size(); i++){
            System.out.println(Assets.logo.get(i));
        }
        System.out.println("Welcome to Scrabble! Press A to start, R to veiw rules, E to exit");
        while(true){
            input = ask.nextLine();
            switch(input.toUpperCase()){
                case "A":
                    return 0;
                case "R":
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
    public static void Startgame(Scanner ask) throws IOException{
        initMenu(ask);
        wordStorage.loadingWords();
        initLetterCountAndScore();
        initPlayers(ask);
        initBoard();
        for(int i = 0; i < playerCount; i++){
            GameLogic.intitalLetters(letterCount);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner ask = new Scanner(System.in);
        clear();
        Startgame(ask);
        do{
            for(int indexOfPlayer = 0; indexOfPlayer < playerCount; indexOfPlayer++){
                GameLogic.paintBoard(gameBoard);
                System.out.println("\u001B[0m");
                GameLogic.askForWord(letterScore, ask, players, indexOfPlayer);   
            }
        }while(run);
    }
    // invoke this method to test code out
    public static void debugging(){
        
    }
}
