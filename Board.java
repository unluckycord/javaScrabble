public class Board {
    private String[][] gameBoard;
    private boolean[][] placedTile;
    private boolean[][] tripleWordScore;
    private boolean[][] doubleWordScore;
    private boolean[][] tripleLetterScore;
    private boolean[][] doubleLetterScore;

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
        tripleWordScore = new boolean[16][16];
        doubleWordScore = new boolean[16][16];
        tripleLetterScore = new boolean[16][16];
        doubleLetterScore = new boolean[16][16];
        for (int i = 0; i < 10; i++) {
            gameBoard[0][i] = AnsiColors.PURPLE.toString()+ i + "  ";
            gameBoard[i][0] = AnsiColors.PURPLE.toString() + i + "    ";
        }
        for (int i = 10; i < 16; i++) {
            gameBoard[0][i] = AnsiColors.PURPLE.toString() + i +" ";
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
        tripleWordScore[1][1] = true;
        gameBoard[8][1] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[8][1] = true;
        gameBoard[15][1] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[15][1] = true;
        gameBoard[1][8] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[1][8] = true;
        gameBoard[15][8] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[15][8] = true;
        gameBoard[1][15] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[1][15] = true;
        gameBoard[8][15] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[8][15] = true;
        gameBoard[15][15] = AnsiColors.YELLOW +"[t w]";
        tripleWordScore[15][15] = true;

        // double word score
        gameBoard[2][2] = AnsiColors.RED+"[d w]";
        doubleWordScore[2][2] = true;
        gameBoard[14][2] = AnsiColors.RED+"[d w]";
        doubleWordScore[14][2] = true;
        gameBoard[3][3] = AnsiColors.RED+"[d w]";
        doubleWordScore[3][3] = true;
        gameBoard[13][3] = AnsiColors.RED+"[d w]";
        doubleWordScore[13][3] = true;
        gameBoard[4][4] = AnsiColors.RED+"[d w]";
        doubleWordScore[4][4] = true;
        gameBoard[12][4] = AnsiColors.RED+"[d w]";
        doubleWordScore[12][4] = true;
        gameBoard[5][5] = AnsiColors.RED+"[d w]";
        doubleWordScore[5][5] = true;
        gameBoard[11][5] = AnsiColors.RED+"[d w]";
        doubleWordScore[11][5] = true;
        gameBoard[2][14] = AnsiColors.RED+"[d w]";
        doubleWordScore[2][14] = true;
        gameBoard[14][14] = AnsiColors.RED+"[d w]";
        doubleWordScore[14][14] = true;
        gameBoard[3][13] = AnsiColors.RED+"[d w]";
        doubleWordScore[3][13] = true;
        gameBoard[13][13] = AnsiColors.RED+"[d w]";
        doubleWordScore[13][13] = true;
        gameBoard[4][12] = AnsiColors.RED+"[d w]";
        doubleWordScore[4][12] = true;
        gameBoard[12][12] = AnsiColors.RED+"[d w]";
        doubleWordScore[12][12] = true;
        gameBoard[5][11] = AnsiColors.RED+"[d w]";
        doubleWordScore[5][11] = true;
        gameBoard[11][11] = AnsiColors.RED+"[d w]";
        doubleWordScore[11][11] = true;

        // triple letter score
        gameBoard[6][2] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[6][2] = true;
        gameBoard[10][2] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[10][2] = true;
        gameBoard[2][6] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[2][6] = true;
        gameBoard[6][6] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[6][6] = true;
        gameBoard[10][6] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[10][6] = true;
        gameBoard[14][6] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[14][6] = true;
        gameBoard[2][10] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[2][10] = true;
        gameBoard[6][10] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[6][10] = true;
        gameBoard[10][10] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[10][10] = true;
        gameBoard[14][10] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[14][10] = true;
        gameBoard[6][14] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[6][14] = true;
        gameBoard[10][14] = AnsiColors.BLUE + "[t l]";
        tripleLetterScore[10][14] = true;

        // double letter score
        gameBoard[4][1] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[4][1] = true;
        gameBoard[12][1] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[12][1] = true;
        gameBoard[7][3] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[7][3] = true;
        gameBoard[9][3] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[9][3] = true;
        gameBoard[1][4] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[1][4] = true;
        gameBoard[8][4] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[8][4] = true;
        gameBoard[15][4] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[15][4] = true;
        gameBoard[3][7] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[3][7] = true;
        gameBoard[7][7] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[7][7] = true;
        gameBoard[9][7] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[9][7] = true;
        gameBoard[13][7] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[13][7] = true;
        gameBoard[4][8] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[4][8] = true;
        gameBoard[12][8] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[12][8] = true;
        gameBoard[3][9] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[3][9] = true;
        gameBoard[7][9] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[7][9] = true;
        gameBoard[9][9] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[9][9] = true;
        gameBoard[13][9] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[13][9] = true;
        gameBoard[1][12] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[1][12] = true;
        gameBoard[8][12] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[8][12] = true;
        gameBoard[15][12] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[15][12] = true;
        gameBoard[7][13] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[7][13] = true;
        gameBoard[9][13] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[9][13] = true;
        gameBoard[4][15] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[4][15] = true;
        gameBoard[12][15] = AnsiColors.CYAN + "[d l]";
        doubleLetterScore[12][15] = true;

        // star in the middle
        gameBoard[8][8] = AnsiColors.GREEN+"[ ★ ]"+ AnsiColors.WHITE;

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