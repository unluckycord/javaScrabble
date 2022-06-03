public class Board {
    private String[][] gameBoard;
    private boolean[][] placedTile;

    public String[][] getGameBoard(){
        return gameBoard;
    }

    public boolean setSpace(char letter, int x, int y){
        if(x < 16 && y < 16 && x > 0 && y > 0 && !placedTile[x][y]){
            placedTile[x][y] = true;
            gameBoard[x][y] = AnsiColors.WHITE+ "[ " + Character.toUpperCase(letter) +" ]"; 
            return true;
        }
        return false;
    }
    

    public Board() {
        gameBoard = new String[16][16];
        placedTile = new boolean[16][16];
        for (int i = 0; i < 10; i++) {
            gameBoard[0][i] = AnsiColors.PURPLE.toString()+ i + "  ";
            gameBoard[i][0] = AnsiColors.PURPLE.toString() + i + "    ";
        }
        for (int i = 10; i < 16; i++) {
            gameBoard[0][i] = AnsiColors.PURPLE.toString() + i;
            gameBoard[i][0] = AnsiColors.PURPLE.toString() + i + "   ";
        }
        for (int i = 1; i < 16; i++) {
            for (int f = 1; f < 16; f++) {
                gameBoard[f][i] = AnsiColors.WHITE+"[   ]";
            }
        }
        
        // left value is x right value is y

        // triple word scores
        gameBoard[1][1] = AnsiColors.YELLOW +"[t w]";
        gameBoard[8][1] = AnsiColors.YELLOW +"[t w]";
        gameBoard[15][1] = AnsiColors.YELLOW +"[t w]";
        gameBoard[1][8] = AnsiColors.YELLOW +"[t w]";
        gameBoard[15][8] = AnsiColors.YELLOW +"[t w]";
        gameBoard[1][15] = AnsiColors.YELLOW +"[t w]";
        gameBoard[8][15] = AnsiColors.YELLOW +"[t w]";
        gameBoard[15][15] = AnsiColors.YELLOW +"[t w]";

        // double word score
        gameBoard[2][2] = AnsiColors.RED+"[d w]";
        gameBoard[14][2] = AnsiColors.RED+"[d w]";
        gameBoard[3][3] = AnsiColors.RED+"[d w]";
        gameBoard[13][3] = AnsiColors.RED+"[d w]";
        gameBoard[4][4] = AnsiColors.RED+"[d w]";
        gameBoard[12][4] = AnsiColors.RED+"[d w]";
        gameBoard[5][5] = AnsiColors.RED+"[d w]";
        gameBoard[11][5] = AnsiColors.RED+"[d w]";
        gameBoard[2][14] = AnsiColors.RED+"[d w]";
        gameBoard[14][14] = AnsiColors.RED+"[d w]";
        gameBoard[3][13] = AnsiColors.RED+"[d w]";
        gameBoard[13][13] = AnsiColors.RED+"[d w]";
        gameBoard[4][12] = AnsiColors.RED+"[d w]";
        gameBoard[12][12] = AnsiColors.RED+"[d w]";
        gameBoard[5][11] = AnsiColors.RED+"[d w]";
        gameBoard[11][11] = AnsiColors.RED+"[d w]";

        // triple letter score
        gameBoard[6][2] = AnsiColors.BLUE + "[t l]";
        gameBoard[10][2] = AnsiColors.BLUE + "[t l]";
        gameBoard[2][6] = AnsiColors.BLUE + "[t l]";
        gameBoard[6][6] = AnsiColors.BLUE + "[t l]";
        gameBoard[10][6] = AnsiColors.BLUE + "[t l]";
        gameBoard[14][6] = AnsiColors.BLUE + "[t l]";
        gameBoard[2][10] = AnsiColors.BLUE + "[t l]";
        gameBoard[6][10] = AnsiColors.BLUE + "[t l]";
        gameBoard[10][10] = AnsiColors.BLUE + "[t l]";
        gameBoard[14][10] = AnsiColors.BLUE + "[t l]";
        gameBoard[6][14] = AnsiColors.BLUE + "[t l]";
        gameBoard[10][14] = AnsiColors.BLUE + "[t l]";

        // double letter score
        gameBoard[4][1] = AnsiColors.CYAN + "[d l]";
        gameBoard[12][1] = AnsiColors.CYAN + "[d l]";
        gameBoard[7][3] = AnsiColors.CYAN + "[d l]";
        gameBoard[9][3] = AnsiColors.CYAN + "[d l]";
        gameBoard[1][4] = AnsiColors.CYAN + "[d l]";
        gameBoard[8][4] = AnsiColors.CYAN + "[d l]";
        gameBoard[15][4] = AnsiColors.CYAN + "[d l]";
        gameBoard[3][7] = AnsiColors.CYAN + "[d l]";
        gameBoard[7][7] = AnsiColors.CYAN + "[d l]";
        gameBoard[9][7] = AnsiColors.CYAN + "[d l]";
        gameBoard[13][7] = AnsiColors.CYAN + "[d l]";
        gameBoard[4][8] = AnsiColors.CYAN + "[d l]";
        gameBoard[12][8] = AnsiColors.CYAN + "[d l]";
        gameBoard[3][9] = AnsiColors.CYAN + "[d l]";
        gameBoard[7][9] = AnsiColors.CYAN + "[d l]";
        gameBoard[9][9] = AnsiColors.CYAN + "[d l]";
        gameBoard[13][9] = AnsiColors.CYAN + "[d l]";
        gameBoard[1][12] = AnsiColors.CYAN + "[d l]";
        gameBoard[8][12] = AnsiColors.CYAN + "[d l]";
        gameBoard[15][12] = AnsiColors.CYAN + "[d l]";
        gameBoard[7][13] = AnsiColors.CYAN + "[d l]";
        gameBoard[9][13] = AnsiColors.CYAN + "[d l]";
        gameBoard[4][15] = AnsiColors.CYAN + "[d l]";
        gameBoard[12][15] = AnsiColors.CYAN + "[d l]";

        // star in the middle
        gameBoard[8][8] = AnsiColors.GREEN+" ★ "+ AnsiColors.WHITE;

    }
    @Override
    public String toString(){
        String board = "";
        for(int i = 0; i < 16; i++){
            for(int f = 0; f < 16; f++){
                board += gameBoard[f][i] + " ";
            }
            board += "\n\n\n";
        }

        return board + AnsiColors.RESET;
    }
}