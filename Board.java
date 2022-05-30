public class Board {
    private static String[][] gameBoard;

    public static String[][] getGameBoard(){
        return gameBoard;
    }
    Board(String[][] gameBoard){
        Board.gameBoard = gameBoard;
    }
}