public class Board {
    private static int[][] gameBoard;

    public static int[][] getGameBoard(){
        return gameBoard;
    }
    Board(int[][] gameBoard){
        Board.gameBoard = gameBoard;
    }
}