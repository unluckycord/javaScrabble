import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class main{

    //test
    public static boolean run = true;
    private static HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
    private static HashMap<Character, Integer> letterScore = new HashMap<Character, Integer>();
    

    public static void intLetterCountAndScore(){
        // initializes our letter count for our bag
        for(char start = 'A'; start <= 'Z'; start++){
            letterCount.put(start, Letters.valueOf(""+start).getCount());
            letterScore.put(start, Letters.valueOf(""+start).getScore());
        }
        letterCount.put(' ', Letters.BLANK.getCount());
        letterScore.put(' ', Letters.BLANK.getScore());
    }

    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        intLetterCountAndScore();
        //Assets.loadAssets();
        degubbing();
        Scanner userWord = new Scanner(System.in);
        Scanner blankLetterPick = new Scanner(System.in);
        while(run){
            GameLogic.askForWord(userWord, blankLetterPick, letterScore);
        }
    }
    // invoke this method to test code out
    public static void degubbing(){
        System.out.println(letterScore);
    }
}