public class Board {
    private static String[][] gameBoard;

    public static String[][] getGameBoard(){
        return gameBoard;
    }

    public Board() {
        String[][] gameBoard = new String[16][16];
        for (int i = 0; i < 10; i++) {
            gameBoard[0][i] = "\u001B[35m" + i + "  ";
            gameBoard[i][0] = "\u001B[35m" + i + "    ";
        }
        for (int i = 10; i < 16; i++) {
            gameBoard[0][i] = "\u001B[35m" + i;
            gameBoard[i][0] = "\u001B[35m" + i + "   ";
        }
        for (int i = 1; i < 16; i++) {
            for (int f = 1; f < 16; f++) {
                gameBoard[f][i] = "\u001B[37m[   ]";
            }
        }
        // left value is x right value is y

        // triple word scores
        gameBoard[1][1] = "\u001B[33m[t w]";
        gameBoard[8][1] = "\u001B[33m[t w]";
        gameBoard[15][1] = "\u001B[33m[t w]";
        gameBoard[1][8] = "\u001B[33m[t w]";
        gameBoard[15][8] = "\u001B[33m[t w]";
        gameBoard[1][15] = "\u001B[33m[t w]";
        gameBoard[8][15] = "\u001B[33m[t w]";
        gameBoard[15][15] = "\u001B[33m[t w]";

        // double word score
        gameBoard[2][2] = "\u001B[31m[d w]";
        gameBoard[14][2] = "\u001B[31m[d w]";
        gameBoard[3][3] = "\u001B[31m[d w]";
        gameBoard[13][3] = "\u001B[31m[d w]";
        gameBoard[4][4] = "\u001B[31m[d w]";
        gameBoard[12][4] = "\u001B[31m[d w]";
        gameBoard[5][5] = "\u001B[31m[d w]";
        gameBoard[11][5] = "\u001B[31m[d w]";
        gameBoard[2][14] = "\u001B[31m[d w]";
        gameBoard[14][14] = "\u001B[31m[d w]";
        gameBoard[3][13] = "\u001B[31m[d w]";
        gameBoard[13][13] = "\u001B[31m[d w]";
        gameBoard[4][12] = "\u001B[31m[d w]";
        gameBoard[12][12] = "\u001B[31m[d w]";
        gameBoard[5][11] = "\u001B[31m[d w]";
        gameBoard[11][11] = "\u001B[31m[d w]";

        // triple letter score
        gameBoard[6][2] = "\u001B[34m[t l]";
        gameBoard[10][2] = "\u001B[34m[t l]";
        gameBoard[2][6] = "\u001B[34m[t l]";
        gameBoard[6][6] = "\u001B[34m[t l]";
        gameBoard[10][6] = "\u001B[34m[t l]";
        gameBoard[14][6] = "\u001B[34m[t l]";
        gameBoard[2][10] = "\u001B[34m[t l]";
        gameBoard[6][10] = "\u001B[34m[t l]";
        gameBoard[10][10] = "\u001B[34m[t l]";
        gameBoard[14][10] = "\u001B[34m[t l]";
        gameBoard[6][14] = "\u001B[34m[t l]";
        gameBoard[10][14] = "\u001B[34m[t l]";

        // double letter score
        gameBoard[4][1] = "\u001B[36m[d l]";
        gameBoard[12][1] = "\u001B[36m[d l]";
        gameBoard[7][3] = "\u001B[36m[d l]";
        gameBoard[9][3] = "\u001B[36m[d l]";
        gameBoard[1][4] = "\u001B[36m[d l]";
        gameBoard[8][4] = "\u001B[36m[d l]";
        gameBoard[15][4] = "\u001B[36m[d l]";
        gameBoard[3][7] = "\u001B[36m[d l]";
        gameBoard[7][7] = "\u001B[36m[d l]";
        gameBoard[9][7] = "\u001B[36m[d l]";
        gameBoard[13][7] = "\u001B[36m[d l]";
        gameBoard[4][8] = "\u001B[36m[d l]";
        gameBoard[12][8] = "\u001B[36m[d l]";
        gameBoard[3][9] = "\u001B[36m[d l]";
        gameBoard[7][9] = "\u001B[36m[d l]";
        gameBoard[9][9] = "\u001B[36m[d l]";
        gameBoard[13][9] = "\u001B[36m[d l]";
        gameBoard[1][12] = "\u001B[36m[d l]";
        gameBoard[8][12] = "\u001B[36m[d l]";
        gameBoard[15][12] = "\u001B[36m[d l]";
        gameBoard[7][13] = "\u001B[36m[d l]";
        gameBoard[9][13] = "\u001B[36m[d l]";
        gameBoard[4][15] = "\u001B[36m[d l]";
        gameBoard[12][15] = "\u001B[36m[d l]";

        // star in the middle
        gameBoard[8][8] = "[\u001B[32m ★ \u001B[37m]";

    }
}