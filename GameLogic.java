public class GameLogic {
    public static boolean letterComparison(){
        if(wordStorage.wordbank.containsKey("thing".toUpperCase())){
            return true;
        }
        return false;
    }
}
