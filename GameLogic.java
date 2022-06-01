import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    public static int total = 100;
    private static char[] tempStorage;
    private static String newLetters;
    // will compare user input to words in wordbank
    public static boolean wordComparison(String word){
        return wordStorage.wordbank.containsKey(word.toUpperCase());
    }
    
    public static char[] intitalLetters(HashMap<Character, Integer>letterCount){
        // gives initial letters for players
        String playersLetters = "";
        for(int i = 0; i < 7; i++){
            playersLetters += GameLogic.randomLetter(letterCount);
        }
        System.out.println(" ");
        return playersLetters.toCharArray();
    }
    /*
    public static int CheckPlayersLetters(String wordInput, Player playersLetters){
        int amountOfTrue = 0;
        char[] wordInputStorage = wordInput.toCharArray();
        char[] playersLettersStorage = playersLetters.getLettersOwned().toCharArray();
        for(int i = 0; i < playersLettersStorage.length; i++){
            for(int f = 0; f < wordInputStorage.length; f++){
                if(wordInputStorage[f] == playersLettersStorage[i]){
                    amountOfTrue++;
                }
            }
        }
        return amountOfTrue;
    }
    

    NEEDS TO BE FIXED


    public static void removeLettersPlayed(String wordInput, Player player, HashMap<Character, Integer> letterCount){
        int lettersRemoved = 0;
        newLetters = "";
        char[] wordInputStorage = wordInput.toCharArray();
        char[] playersLettersStorage = player.getLettersOwned().toCharArray();
        for(int i = 0; i < playersLettersStorage.length; i++){
            for(int f = 0; f < wordInputStorage.length; f++){
                if(wordInputStorage[f] == playersLettersStorage[i]){
                    playersLettersStorage[i] = ' ';
                    lettersRemoved++;
                }
                newLetters += playersLettersStorage[i];
            }
        }
        for(int i = 0; i < lettersRemoved; i++){
            newLetters +=randomLetter(letterCount);
        }
        player.setLettersOwned(newLetters, player);
    }
    */

    public static String playersLetters(Player player){
        String letters = "";
        char[] storage = player.getLettersOwned();
        for(int i = 0; i < player.getLettersOwned().length; i++){
            letters += storage[i] ;
        }
        return letters;
    }

    public static void askForWord(HashMap<Character, Integer> letterScore, Scanner ask, Player player, HashMap<Character, Integer> letterCount){
        int score = 0;
        System.out.println(player.getUsername() +": "+ playersLetters(player));
        System.out.println("input your word\n");
        String word = ask.next().toUpperCase();
        tempStorage = word.toCharArray();
        for(int i = 0; i< tempStorage.length; i++){
            if(tempStorage[i] == '*'){
                System.out.println("please pick a blank letter");
                tempStorage[i] = ask.next().toUpperCase().charAt(0);
            }
        }
        word = String.valueOf(tempStorage).toUpperCase();
        if(word != "stopgame"){
            //if(CheckPlayersLetters(word, player) == word.toCharArray().length){
                if(GameLogic.wordComparison(word)){  
                    for(int i = 0; i < tempStorage.length; i++){
                        score += letterScore.get(tempStorage[i]);
                    }
                    player.setScore(score, player);
                    //removeLettersPlayed(word, player, letterCount);
                    System.out.println(player.getUsername() + ": " + player.getScore());
                }else{
                    System.out.println("try again, not a valid word");
                }
            /*}else{
                System.out.println("not enough letters or your letter played isn't in your letter bank, try again");
            }*/
        }else{
            main.run = false;
        }
    }

    // random letter bag
    public static char randomLetter(HashMap<Character, Integer> bag){
        char random = '|';
        if(bag.isEmpty()){
            return random;
        }
        Set<Character> bagSet = bag.keySet();
        int r = (int)(Math.random()*bagSet.size());
        int i = 0;
        for(char c : bagSet){
            if(i == r){
                random=c;
            }
            i++;
        }
        // 91 = [ or Z+1
        if(random == 91){
            random = ' ';
        }
        //recursion
        if(bag.get(random) == 0){
            bag.remove(random);
            return randomLetter(bag);
        }
        //decrimetns letter count by 1
        bag.put(random, bag.get(random)-1);
        return random;
    }
    public static void paintBoard(String[][] board){
        for(int i = 0; i < 16; i++){
            for(int f = 0; f < 16; f++){
                System.out.print(board[f][i] + "  ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}
